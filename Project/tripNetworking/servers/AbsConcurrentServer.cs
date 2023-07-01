using System;
using System.Net.Sockets;
using System.Threading;

public abstract class AbsConcurrentServer:AbstractServer
{
    protected AbsConcurrentServer(string host,int port) : base(host,port)
    {
        Console.WriteLine("Concurrent AbstractServer");
    }

    protected override void processRequest(TcpClient client)
    {
        Thread tw = createWorker(client);
        tw.Start();
    }

    protected abstract Thread createWorker(TcpClient client);
}