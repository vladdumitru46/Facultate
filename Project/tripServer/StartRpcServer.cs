using System;
using System.IO;
using log4net;
using Microsoft.Extensions.Configuration;
using Proiect.database;
using Proiect.repository;
using tripPersistence;
using tripServer;

public class StartRpcServer
{
    private static int defaultPort = 55559;
    private static string defaultHost = "127.0.0.1";

    static void Main(string[] args)
    {
        //Application.EnableVisualStyles();
        //Application.SetCompatibleTextRenderingDefault(false);

        var builder = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("C:\\Users\\Andrei Erhan\\RiderProjects\\mpp-proiect-csharp-Andreliano\\Project\\tripClient\\config.json", optional: true, reloadOnChange: true);
        IConfigurationRoot configuration = builder.Build();
        string connectionString=configuration.GetConnectionString("MyConnectionString");
        
        //log4net.Config.XmlConfigurator.Configure();
        
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
        
        IServices ServerImpl = new ServiceImpl(bookingRepository, customerRepository, landMarkRepository, personRepository,
            transportationCompanyRepository, travelAgencyRepository, tripRepository);
        AbstractServer server = new RpcConcurrentServer(defaultHost,defaultPort, ServerImpl);
        try
        {
            server.Start();
            Console.WriteLine("Server started...");
        }
        catch (ServerException e)
        {
            Console.Error.WriteLine("Error starting the server" + e.Message);
        }
    }
}

