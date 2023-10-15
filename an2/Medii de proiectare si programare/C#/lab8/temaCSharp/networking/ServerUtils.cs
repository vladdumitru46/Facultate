using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using service;

namespace networking
{
    public abstract class AbstractServer
    {
        private readonly string host;
        private readonly int port;
        private TcpListener server;

        public AbstractServer(string host, int port)
        {
            this.host = host;
            this.port = port;
        }

        public void Start()
        {
            var adr = IPAddress.Parse(host);
            var ep = new IPEndPoint(adr, port);
            server = new TcpListener(ep);
            server.Start();
            while (true)
            {
                Console.WriteLine("Waiting for clients ...");
                var client = server.AcceptTcpClient();
                Console.WriteLine("Client connected ...");
                processRequest(client);
            }
        }

        public abstract void processRequest(TcpClient client);
    }


    public class ConcurrentServer : AbstractServer
    {
        private readonly IService _server;

        public ConcurrentServer(string host, int port, IService server) : base(host, port)
        {
            _server = server;
        }

        public override void processRequest(TcpClient client)
        {
            var t = CreateWorker(client);
            t.Start();
        }

        private Thread CreateWorker(TcpClient client)
        {
            var clientObjectWorker = new ClientObjectWorker(_server, client);
            Console.WriteLine("create Wrorker in Concurent");
            return new Thread(clientObjectWorker.Run);
        }
    }
}