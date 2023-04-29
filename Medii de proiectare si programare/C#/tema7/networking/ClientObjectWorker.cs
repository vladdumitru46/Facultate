using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using model.domains;
using service;

namespace networking
{
    public sealed class ClientObjectWorker : IServiceObserver
    {
        private readonly TcpClient _connection;
        private readonly IFormatter _formatter;

        private readonly IService _server;
        private readonly NetworkStream _stream;
        private volatile bool _connected;

        public ClientObjectWorker(IService server, TcpClient connection)
        {
            _server = server;
            _connection = connection;
            try
            {
                _stream = connection.GetStream();
                _formatter = new BinaryFormatter();
                _connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public void TicketsSold(Buyer buyers)
        {
            try
            {
                SendResponse(new NewSellTicketsResponse(buyers));
            }
            catch (Exception e)
            {
                throw new Exception("Sending error: " + e);
            }
        }

        public void Run()
        {
            while (_connected)
            {
                try
                {
                    var request = _formatter.Deserialize(_stream);
                    var response = HandleRequest((Request)request);
                    if (response != null) SendResponse(response);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }

            try
            {
                _stream.Close();
                _connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e);
            }
        }

        private void SendResponse(Response response)
        {
            lock (_stream)
            {
                _formatter.Serialize(_stream, response);
                _stream.Flush();
            }
        }

        private Response HandleRequest(Request request)
        {
            Response response = null;
            if (request is LogInRequest)
            {
                var logInRequest = (LogInRequest)request;
                var employeeAtOffice = logInRequest._employeeAtOffice;
                try
                {
                    lock (_server)
                    {
                        _server.LogIn(employeeAtOffice.Email, employeeAtOffice.Password, this);
                    }

                    return new OkResponse();
                }
                catch (Exception e)
                {
                    _connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is LogOutRequest)
            {
                var logOutRequest = (LogOutRequest)request;
                var employeeAtOffice = logOutRequest.EmployeeAtOffice;
                try
                {
                    lock (_server)
                    {
                        _server.LogOut(employeeAtOffice, this);
                    }

                    _connected = false;
                    return new OkResponse();
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is SellTicketsRequest)
            {
                var sellTicketsRequest = (SellTicketsRequest)request;
                var buyer = sellTicketsRequest.Buyer;
                try
                {
                    lock (_server)
                    {
                        _server.SellTickets(buyer.ShowName1, buyer.BuyerName1, buyer.NoOfTicketsBought1);
                    }

                    return new OkResponse();
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetAllShowRequest)
            {
                var getAllShowRequest = (GetAllShowRequest)request;
                var employeeAtOffice = getAllShowRequest.EmployeeAtOffice;
                try
                {
                    List<Show> list;
                    lock (_server)
                    {
                        list = _server.GetAllShows(employeeAtOffice);
                    }

                    return new GetAllShowsResponse(list);
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            return response;
        }
    }
}