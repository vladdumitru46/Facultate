using networking;
using service;
using temaCSharp.repositoryes;

namespace server
{
    internal class StartServer
    {
        public static void Main(string[] args)
        {
            IService service = new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());
            var server = new ConcurrentServer("127.0.0.1", 55556, service);
            server.Start();
        }
    }
}