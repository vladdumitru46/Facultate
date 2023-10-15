using System;
using System.Net.Sockets;
using System.Threading;
using Google.Protobuf;
using model.domains;
using Proto;
using service;

namespace networkingProtobuf
{
    public class ProtobufWorker: IServiceObserver
    {
        private IService _server;
        private TcpClient _connection;
        private NetworkStream _stream;
        private volatile bool _connected;

        public ProtobufWorker(IService server, TcpClient connection)
        {
            _server = server;
            _connection = connection;
            try
            {
                _stream = connection.GetStream();
                _connected = true;
            }
            catch(Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void Run()
        {
            while (_connected)
            {
                try
                {
                    Request request = Request.Parser.ParseDelimitedFrom(_stream);
                    Response response = HandleRequest(request);
                    if (response != null)
                    {
                        SendResponse(response);
                    }
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
                Console.WriteLine("Error "+e);
            }
        }

        private void SendResponse(Response response)
        {
            lock (_stream)
            {
                response.WriteDelimitedTo(_stream);
                _stream.Flush();
            }
        }

        private Response HandleRequest(Request request)
        {
            Response response = null;
            Request.Types.Type requiredType = request.Type;
            switch (requiredType)
            {
                case Request.Types.Type.LogIn:
                {
                    model.domains.EmployeeAtOffice employeeAtOffice = ProtoUtils.GetEmployee(request);
                    try
                    {
                        lock (_server)
                        {
                            _server.LogIn(employeeAtOffice.Email, employeeAtOffice.Password, this);
                        }

                        return ProtoUtils.CreateOkResponse();
                    }
                    catch (Exception e)
                    {
                        _connected = false;
                        return ProtoUtils.CreateErrorResponse(e.Message);
                    }
                }
                case Request.Types.Type.LogOut:
                {
                    model.domains.EmployeeAtOffice employeeAtOffice = ProtoUtils.GetEmployee(request);
                    try
                    {
                        lock (_server)
                        {
                            _server.LogOut(employeeAtOffice, this);
                        }

                        _connected = false;
                        return ProtoUtils.CreateOkResponse();
                    }
                    catch (Exception e)
                    {
                        return ProtoUtils.CreateErrorResponse(e.Message);
                    }
                } case Request.Types.Type.SellTicketsRequest:
                {
                    model.domains.Buyer buyer = ProtoUtils.GetBuyer(request);
                    try
                    {
                        lock (_server)
                        {
                            _server.SellTickets(buyer.ShowName1, buyer.BuyerName1, buyer.NoOfTicketsBought1);
                        }
                        return ProtoUtils.CreateOkResponse();
                    }
                    catch (Exception e)
                    {
                        return ProtoUtils.CreateErrorResponse(e.Message);
                    }
                }
            }

            return response;
        }

        public void TicketsSold(Buyer buyers)
        {
            try
            {
                SendResponse(ProtoUtils.CreateNewSellTicketResponse(buyers));
            }
            catch (Exception e)
            {
                throw new Exception("Sending error " + e);
            }
        }
    }
}