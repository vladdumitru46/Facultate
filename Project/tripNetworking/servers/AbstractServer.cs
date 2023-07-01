using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Remoting;

public abstract class AbstractServer
{
    private string host;
    private int port;
    private TcpListener server = null;

    public AbstractServer(string host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void Start()
    {
        IPAddress adr = IPAddress.Parse(host);
        IPEndPoint ep = new IPEndPoint(adr, port);
        server = new TcpListener(ep);
        server.Start();
        while (true)
        {
            Console.WriteLine("Waiting for clients...");
            TcpClient client = server.AcceptTcpClient();
            Console.WriteLine("Client connected...");
            processRequest(client);
        }
    }

    protected abstract void processRequest(TcpClient client);
    
    // public void stop()
    // {
    //     try
    //     {
    //         server.Stop();
    //     }
    //     catch (Exception e)
    //     {
    //         throw new ServerException("Closing server error ", e);
    //     }
    // }
}