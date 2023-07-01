using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Remoting.Messaging;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Security.AccessControl;
using System.Threading;
using tripModel;


public class ClientRpcWorker:IObserver
{
    private IServices server;
    private TcpClient connection;

    private NetworkStream stream;
    private IFormatter formatter;
    private volatile bool connected;
    public ClientRpcWorker(IServices server, TcpClient connection)
    {
        this.server = server;
        this.connection = connection;
        try
        {
            stream=connection.GetStream();
            formatter = new BinaryFormatter();
            connected=true;
        }
        catch (Exception e)
        {
            Console.WriteLine(e.StackTrace);
        }
    }

    public virtual void run()
    {
        while(connected)
        {
            try
            {
                Object request=formatter.Deserialize(stream);
                Response response=handleRequest((Request)request);
                if (response!=null)
                {
                    sendResponse(response);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
				
            try
            {
                Thread.Sleep(1000);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }
        try
        {
            stream.Close();
            connection.Close();
        }
        catch (Exception e)
        {
            Console.WriteLine("Error "+e);
        }
    }

    
    private Response handleRequest(Request request)
    {
        Response response = null;
        switch (request.Type)
        {
            case RequestType.LOGIN:
                response=solveLogin(request);
                break;
            case RequestType.LOGOUT:
                response=solveLogout(request);
                break;
            case RequestType.FIND_ALL_TRIPS:
                response = solveFindAllTrips(request);
                break;
            case RequestType.BOOK_TRIP:
                response = solveBookTrip(request);
                break;
        }
        return response;
    }

    
    
    private Response solveLogin(Request request)
    {
        Person<int> person = (Person<int>) request.Data;
        try
        {
            lock (server)
            {
                server.Login(person, this);
            }
            return new Response.Builder().type(ResponseType.OK).data(true).build();
        }
        catch (TripException e)
        {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.Message).build();
        }
    }

    private Response solveFindAllTrips(Request request)
    {
        try
        {
            List<TripDTO<int>> trips;
            lock (server)
            {
                trips = server.FindAllTrips();
            }
            return new Response.Builder().type(ResponseType.OK).data(trips).build();
        }
        catch (TripException ex)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(ex.Message).build();
        }
    }

    private Response solveBookTrip(Request request)
    {
        TripDTO<int> tripDTO = (TripDTO<int>) request.Data;
        try
        {
            lock (server)
            {
                Console.WriteLine("ClientRpcWorker" + tripDTO.ToString());
                server.BookTrip(tripDTO);
            }
            return new Response.Builder().type(ResponseType.OK).data(request.Data).build();
        }
        catch (TripException e)
        {
            return new Response.Builder().type(ResponseType.ERROR).data(e.Message).build();
        }
    }
    
    
    private Response solveLogout(Request request)
    {
        Person<int> person = (Person<int>) request.Data;
        try {
            lock (server)
            {
                server.Logout(person, this);
            }
            connected = false;
            return new Response.Builder().type(ResponseType.OK).data(true).build();
        } catch (TripException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.Message).build();
        }
    }
    
    
    
    private void sendResponse(Response response)
    {
        Console.WriteLine("sending response "+response + " from ClientRpcWorker");
        lock (stream)
        {
            formatter.Serialize(stream, response);
            stream.Flush();
        }
    }
    
    public void newBooking(TripDTO<int> tripDTO)
    {
        Response response = new Response.Builder().type(ResponseType.UPDATE).data(tripDTO).build();
        try
        {
            sendResponse(response);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.StackTrace);
        }
    }
}