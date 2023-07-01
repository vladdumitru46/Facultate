using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using model.domains;
using service;
using temaCSharp.repositoryes;

namespace networking
{
    public sealed class ClientProxy : IService
    {
        private readonly string _host;
        private readonly int _port;
        private readonly Queue<Response> _responses;
        private IServiceObserver _client;
        private TcpClient _connection;
        private volatile bool _finished;
        private IFormatter _formatter;
        private NetworkStream _stream;
        private EventWaitHandle _waitHandle;

        public ClientProxy(string host, int port)
        {
            _host = host;
            _port = port;
            _responses = new Queue<Response>();
        }

        public void LogIn(string email, string password, IServiceObserver client)
        {
            InitializeConnection();
            var employeeRepo = new EmployeeRepo();
            var employeeAtOffice = employeeRepo.findOneByEmailAndPassword(email, password);
            SendRequest(new LogInRequest(employeeAtOffice));
            var response = ReadResponse();
            if (response is OkResponse)
            {
                _client = client;
                return;
            }

            if (response is ErrorResponse)
            {
                var err = (ErrorResponse)response;
                CloseConnection();
                throw new Exception(err.Message);
            }
        }

        public void LogOut(EmployeeAtOffice employeeAtOffice, IServiceObserver client)
        {
            SendRequest(new LogOutRequest(employeeAtOffice));
            var response = ReadResponse();
            CloseConnection();
            if (response is ErrorResponse)
            {
                var err = (ErrorResponse)response;
                throw new Exception(err.Message);
            }
        }

        public List<Show> SearchArtistByDate(DateTime date)
        {
            throw new NotImplementedException();
        }

        public void SellTickets(string showName, string buyerName, int noOfTickets)
        {
            var buyer = new Buyer(buyerName, noOfTickets, showName);
            SendRequest(new SellTicketsRequest(buyer));
            var response = ReadResponse();
            if (response is ErrorResponse)
            {
                var errorResponse = (ErrorResponse)response;
                throw new Exception(errorResponse.Message);
            }
        }

        public Show findOneShow(string showName)
        {
            throw new NotImplementedException();
        }

        public Show UpdateShow(Show show)
        {
            throw new NotImplementedException();
        }

        public EmployeeAtOffice FindEmployeeByEmailAndPassword(string email, string password)
        {
            throw new NotImplementedException();
        }

        public List<Show> GetAllShows(EmployeeAtOffice employeeAtOffice)
        {
            SendRequest(new GetAllShowRequest(employeeAtOffice));
            var response = ReadResponse();
            if (response is ErrorResponse)
            {
                var errorResponse = (ErrorResponse)response;
                throw new Exception(errorResponse.Message);
            }

            var getAllShowsResponse = (GetAllShowsResponse)response;
            var list = getAllShowsResponse.List;
            return list;
        }

        private void CloseConnection()
        {
            _finished = true;
            try
            {
                _stream.Close();
                _connection.Close();
                _waitHandle.Close();
                _client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private Response ReadResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (_responses)
                {
                    response = _responses.Dequeue();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            return response;
        }

        private void SendRequest(Request request)
        {
            try
            {
                _formatter.Serialize(_stream, request);
                _stream.Flush();
            }
            catch (Exception e)
            {
                throw new Exception("Error sending object: " + e);
            }
        }

        private void InitializeConnection()
        {
            try
            {
                _connection = new TcpClient(_host, _port);
                _stream = _connection.GetStream();
                _formatter = new BinaryFormatter();
                _finished = false;
                _waitHandle = new AutoResetEvent(false);
                StartReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void StartReader()
        {
            var tw = new Thread(Run);
            tw.Start();
        }

        private void HandleUpdate(UpdateResponse updateResponse)
        {
            if (updateResponse is NewSellTicketsResponse)
            {
                var newSellTicketsResponse = (NewSellTicketsResponse)updateResponse;
                var buyer = newSellTicketsResponse.Buyer;
                try
                {
                    _client.TicketsSold(buyer);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
        }

        public void Run()
        {
            while (!_finished)
                try
                {
                    var response = _formatter.Deserialize(_stream);
                    if (response is UpdateResponse)
                    {
                        HandleUpdate((UpdateResponse)response);
                    }
                    else
                    {
                        lock (_responses)
                        {
                            _responses.Enqueue((Response)response);
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