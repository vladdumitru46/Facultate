using System;
using System.Net.Sockets;
using System.Threading;

public class RpcConcurrentServer : AbsConcurrentServer
{
    private IServices server;
    
    public RpcConcurrentServer(string host,int port,IServices s) : base(host,port)
    {
        server=s;
        Console.WriteLine("RpcConcurrentServer");
    }

    protected override Thread createWorker(TcpClient client)
    {
        ClientRpcWorker worker = new ClientRpcWorker(server, client);
        return new Thread(new ThreadStart(worker.run));
    }
}