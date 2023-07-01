using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Security.AccessControl;
using System.Threading;
using tripModel;

public class ServicesRpcProxy:IServices
{
    private string host;
    private int port;
    private Queue<Response> responses;
    private EventWaitHandle _waitHandle;
    
    private IFormatter formatter;
    private TcpClient connection;
    private NetworkStream stream;
    
    private volatile bool finished;
    private IObserver client;

    public ServicesRpcProxy(string host, int port)
    {
        this.host = host;
        this.port = port;
        responses=new Queue<Response>();
    }
    
    
    
    public void Login(Person<int> person, IObserver client)
    {
        initializeConnection();
        Request request = new Request.Builder().type(RequestType.LOGIN).data(person).build();
        sendRequest(request);
        Response response = readResponse();

        Boolean loggedIn;

        if (response.Type == ResponseType.OK) {
            loggedIn = (Boolean) response.Data;
            if (loggedIn) {
                this.client = client;
            }
        } else if (response.Type == ResponseType.ERROR)
        {
            string err = response.Data.ToString();
            closeConnection();
            throw new TripException(err);
        }
    }

    public List<TripDTO<int>> FindAllTrips()
    {
        Request request = new Request.Builder().type(RequestType.FIND_ALL_TRIPS).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.Type == ResponseType.ERROR)
        {
            throw new TripException(response.Data.ToString());
        }

        List<TripDTO<int>> trips = (List<TripDTO<int>>)response.Data;
        return trips;
    }

    public void BookTrip(TripDTO<int> tripDTO)
    {
        Request request = new Request.Builder().type(RequestType.BOOK_TRIP).data(tripDTO).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.Type == ResponseType.ERROR)
        {
            throw new TripException(response.Data.ToString());
        }
    }

    public void Logout(Person<int> person, IObserver obs)
    {
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(person).build();
        sendRequest(req);
        Response response = readResponse();
        closeConnection();
        client = null;
        if (response.Type == ResponseType.ERROR)
        {
            String err = response.Data.ToString();
            throw new TripException(err);
        }
    }
    
    private void closeConnection()
    {
        finished = true;
        try
        {
            stream.Close();
            connection.Close();
            client = null;
        }
        catch (Exception e)
        {
            Console.WriteLine(e.StackTrace);
        }

    }

        
    private void sendRequest(Request request)
    {
        try
        {
            formatter.Serialize(stream, request);
            stream.Flush();
        }
        catch (Exception e)
        {
            throw new TripException("Error sending object " + e);
        }

    }

    
    
    private Response readResponse()
    {
        Response response = null;
        try
        {
            Console.WriteLine("readResponse from ServicesRpcProxy");
            _waitHandle.WaitOne();
            lock (responses)
            {
                response = responses.Dequeue();
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e.StackTrace);
        }
        return response;
    }

    
    private void initializeConnection()
    {
        try
        {
            connection = new TcpClient(host, port);
            stream = connection.GetStream();
            formatter = new BinaryFormatter();
            finished = false;
            _waitHandle = new AutoResetEvent(false);
            startReader();
        }
        catch (Exception e)
        {
            Console.WriteLine(e.StackTrace);
        }
    }

    
    private void startReader()
    {
        Thread tw = new Thread(run);
        tw.Start();
    }

    
    
    public void run()
    {
        while (!finished)
        {
            try
            {
                Response response = (Response)formatter.Deserialize(stream);
                Console.WriteLine("response received " + response);
                if (response.Type == ResponseType.UPDATE)
                {
                    client.newBooking((TripDTO<int>) response.Data);
                }
                else
                {
                    lock (responses)
                    {
                        responses.Enqueue((Response)response);
                    }
                    _waitHandle.Set();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Reading error " + e);
            }

        }
    }
}