using System;
using System.IO;
using System.Collections.Generic;
using System.Windows.Forms;
using log4net;
using Microsoft.Extensions.Configuration;
using Proiect;
using Proiect.database;
using Proiect.repository;
using tripPersistence;
using Proiect.service;


namespace ConcursInot.ClientFX
{
    public class StartRpcClientFX
    {
        private static int defaultPort = 55559;
        private static string defaultServer = "127.0.0.1";

        static void Main()
        {
            Console.WriteLine("In client start");
            Dictionary<string, string> clientProps = new Dictionary<string, string>();
            try
            {
                string[] lines = File.ReadAllLines(@"C:\Users\Andrei Erhan\RiderProjects\mpp-proiect-csharp-Andreliano\Project\tripClient\appclient.properties");
                foreach (string line in lines)
                {
                    int idx = line.IndexOf('=');
                    if (idx >= 0)
                    {
                        string key = line.Substring(0, idx).Trim();
                        string value = line.Substring(idx + 1).Trim();
                        clientProps[key] = value;
                    }
                }
                Console.WriteLine("Client props set.");
                foreach (var kvp in clientProps)
                {
                    Console.WriteLine(kvp.Key + "=" + kvp.Value);
                }
            }
            catch (IOException e)
            {
                Console.Error.WriteLine("CANNOT FIND appclient.properties" + e);
                return;
            }
            string serverIP;
            int serverPort;
            
            try
            {
                serverIP = clientProps["server.host"];
                serverPort = Int32.Parse(clientProps["server.port"]);
            }catch (FormatException ex){
                Console.Error.WriteLine("Conversion not possible");
                serverIP = defaultServer;
                serverPort = defaultPort;
            }
            // MessageBox.Show("Using server IP " + serverIP+" "+serverPort);
            
            log4net.Config.XmlConfigurator.Configure();
            
            var builder = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("C:\\Users\\Andrei Erhan\\RiderProjects\\mpp-proiect-csharp-Andreliano\\Project\\tripClient\\config.json", optional: true, reloadOnChange: true);
            IConfigurationRoot configuration = builder.Build();
            string connectionString=configuration.GetConnectionString("MyConnectionString");
            
            
            ILog loggerPerson = LogManager.GetLogger("repo_person");
            IPersonRepository personRepository =
                new PersonDataBaseRepository(connectionString, loggerPerson);

            ILog loggerTravelAgency = LogManager.GetLogger("repo_travelAgency");
            ITravelAgencyRepository travelAgencyRepository =
                new TravelAgencyDataBaseRepository(connectionString, loggerTravelAgency);

            ILog loggerTrip = LogManager.GetLogger("repo_trip");
            ITripRepository tripRepository = new TripDataBaseRepository(connectionString, loggerTrip);

            ILog loggerLandMark = LogManager.GetLogger("repo_landMark");
            ILandMarkRepository landMarkRepository = new LandMarkDataBaseRepository(connectionString, loggerLandMark);

            ILog loggerTransportationCompany = LogManager.GetLogger("repo_transportationCompany");
            ITransportationCompanyRepository transportationCompanyRepository = new TransportationCompanyDataBaseRepository(connectionString, loggerTransportationCompany);
            
            ILog loggerCustomer = LogManager.GetLogger("repo_customer");
            ICustomerRepository customerRepository = new CustomerDataBaseRepository(connectionString, loggerCustomer);

            ILog loggerBooking = LogManager.GetLogger("repo_booking");
            IBookingRepository bookingRepository = new BookingDataBaseRepository(connectionString, loggerBooking);

            Service service = new Service(bookingRepository, customerRepository, landMarkRepository, personRepository,
                transportationCompanyRepository, travelAgencyRepository, tripRepository);
            
            IServices server = new ServicesRpcProxy(serverIP, serverPort);
            Application.Run(new logInForm(server, service));
        }
    }
}