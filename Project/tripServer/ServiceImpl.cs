using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Threading.Tasks;
using Proiect.database;
using Proiect.repository;
using tripModel;
using tripPersistence;

namespace tripServer
{
    public class ServiceImpl:IServices
    {
        private IBookingRepository _bookingRepository;

        private ICustomerRepository _customerRepository;

        private ILandMarkRepository _landMarkRepository;

        private IPersonRepository _personRepository;

        private ITransportationCompanyRepository _transportationCompanyRepository;

        private ITravelAgencyRepository _travelAgencyRepository;

        private ITripRepository _tripRepository;

        private ConcurrentDictionary<string, IObserver> _loggedClients;

        public ServiceImpl(IBookingRepository bookingRepository, ICustomerRepository customerRepository,
            ILandMarkRepository landMarkRepository, IPersonRepository personRepository,
            ITransportationCompanyRepository transportationCompanyRepository,
            ITravelAgencyRepository travelAgencyRepository, ITripRepository tripRepository)
        {
            _bookingRepository = bookingRepository;
            _customerRepository = customerRepository;
            _landMarkRepository = landMarkRepository;
            _personRepository = personRepository;
            _transportationCompanyRepository = transportationCompanyRepository;
            _travelAgencyRepository = travelAgencyRepository;
            _tripRepository = tripRepository;
            _loggedClients = new ConcurrentDictionary<string, IObserver>();
        }
        
        public void Login(Person<int> person, IObserver client)
        {
            int id = _personRepository.CheckPersonAccountExistence(person.Email, person.Password);
            if (id != 0)
            {
                if (_loggedClients.ContainsKey(person.Email))
                {
                    throw new TripException("Person already logged in");
                }
                _loggedClients[person.Email] = client;
            }
            else
            {
                throw new TripException("Authentication failed!");
            }
        }

        public List<TripDTO<int>> FindAllTrips()
        {
            List<Trip<int>> trips = (List<Trip<int>>)_tripRepository.FindAll();
            List<TripDTO<int>> tripsDTO = new List<TripDTO<int>>();
            if (trips == null)
            {
                Console.WriteLine("null");
            }
            foreach (Trip<int> trip in trips)
            {
                string nameLandMark = _landMarkRepository.FindOne(trip.IdLandMark).Name;
                string nameTransportationCompany =
                    _transportationCompanyRepository.FindOne(trip.IdTransportationCompany).Name;
                TripDTO<int> tripDTO = new TripDTO<int>(trip.Id, nameLandMark, nameTransportationCompany,
                    trip.DepartureTime, trip.Price, trip.NumberOfSeatsAvailable);
                tripsDTO.Add(tripDTO);
            }
            
            return tripsDTO;
        }

        public void BookTrip(TripDTO<int> tripDto)
        {
            _tripRepository.UpdateNumberOfSeatsAvailable(tripDto.NumberOfSeatsAvailable, tripDto.Id);
            foreach (var client in _loggedClients)
            {
                IObserver observer = client.Value;
                Console.WriteLine(client.Key);
                Task.Run(() => observer.newBooking(tripDto));
            }
        }

        public void Logout(Person<int> person, IObserver obs)
        {
            IObserver localClient;
            _loggedClients.TryRemove(person.Email, out localClient);
            if (localClient == null)
                throw new TripException("User " + person.Email + " is not logged in.");
        }
    }
}