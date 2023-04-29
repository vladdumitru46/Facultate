using System.Windows.Forms;
using networking;
using server;
using service;
using temaCSharp.repositoryes;

namespace client
{
    internal class StartClient
    {
        public static void Main(string[] args)
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            IService server = new ClientProxy("127.0.0.1", 55556);
            var serviceNormal =
                new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());
            var logIn = new LogIn(server, serviceNormal);
            var mainPage = new MainPage(serviceNormal, logIn);
            logIn.setClient(mainPage);
            Application.Run(logIn);
        }
    }
}