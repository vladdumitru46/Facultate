using System;
using System.Collections;
using System.Collections.Generic;
using Proiect.repository;
using tripModel;
using tripPersistence;

namespace Proiect.service
{
    public class Service
    {
        private IBookingRepository _bookingRepository;

        private ICustomerRepository _customerRepository;

        private ILandMarkRepository _landMarkRepository;

        private IPersonRepository _personRepository;

        private ITransportationCompanyRepository _transportationCompanyRepository;

        private ITravelAgencyRepository _travelAgencyRepository;

        private ITripRepository _tripRepository;

        public Service(IBookingRepository bookingRepository, ICustomerRepository customerRepository,
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
        }

        public void SaveBooking(Booking<int> booking)
        {
            _bookingRepository.Save(booking);
        }

        public IEnumerable<Booking<int>> FindAllBookings()
        {
            return _bookingRepository.FindAll();
        }

        public void SaveCustomer(Customer<int> customer)
        {
            _customerRepository.Save(customer);
        }

        public void DeleteCustomer(int idCustomer)
        {
            _customerRepository.Delete(idCustomer);
        }

        public void UpdateCustomer(Customer<int> customer)
        {
            _customerRepository.Update(customer);
        }

        public Customer<int> findOneCustomer(int idCustomer)
        {
            return _customerRepository.FindOne(idCustomer);
        }

        public IEnumerable<Customer<int>> FindAllCustomers()
        {
            return _customerRepository.FindAll();
        }

        public void SaveLandMark(LandMark<int> landMark)
        {
            _landMarkRepository.Save(landMark);
        }

        public void DeleteLandMark(int idLandMark)
        {
            _landMarkRepository.Delete(idLandMark);
        }

        public void UpdateLandMark(LandMark<int> landMark)
        {
            _landMarkRepository.Update(landMark);
        }

        public LandMark<int> FindOneLandMark(int idLandMark)
        {
            return _landMarkRepository.FindOne(idLandMark);
        }

        public IEnumerable<LandMark<int>> FindAllLandMark()
        {
            return _landMarkRepository.FindAll();
        }

        public void SaveTransportationCompany(TransportationCompany<int> transportationCompany)
        {
            _transportationCompanyRepository.Save(transportationCompany);
        }

        public void DeleteTransportationCompany(int idTransportationCompany)
        {
            _transportationCompanyRepository.Delete(idTransportationCompany);
        }

        public void UpdateTransportationCompany(TransportationCompany<int> transportationCompany)
        {
            _transportationCompanyRepository.Update(transportationCompany);
        }

        public TransportationCompany<int> FindOneTransportationCompany(int idTransportationCompany)
        {
            return _transportationCompanyRepository.FindOne(idTransportationCompany);
        }

        public IEnumerable<TransportationCompany<int>> FindAllTransportationCompanies()
        {
            return _transportationCompanyRepository.FindAll();
        }

        public IEnumerable<TravelAgency<int>> FindAllTravelAgencies()
        {
            return _travelAgencyRepository.FindAll();
        }

        public void SavePerson(Person<int> person)
        {
            _personRepository.Save(person);
        }

        public void DeletePerson(int idPerson)
        {
            _personRepository.Delete(idPerson);
        }

        public void UpdatePerson(Person<int> person)
        {
            _personRepository.Update(person);
        }

        public bool CheckEmailExistenceInPersons(String email)
        {
            return _personRepository.CheckEmailExistenceInPersons(email);
        }

        public int CheckPersonAccountExistence(String email, String password)
        {
            return _personRepository.CheckPersonAccountExistence(email, password);
        }

        public Person<int> FindPersonByEmail(String emailAddress)
        {
            return _personRepository.FindPersonByEmail(emailAddress);
        }

        public Person<int> FindOnePerson(int idPerson)
        {
            return _personRepository.FindOne(idPerson);
        }

        public IEnumerable<Person<int>> FindAllPersons()
        {
            return _personRepository.FindAll();
        }

        public void SaveTrip(Trip<int> trip)
        {
            _tripRepository.Save(trip);
        }

        public void DeleteTrip(int idTrip)
        {
            _tripRepository.Delete(idTrip);
        }

        public void UpdateTrip(Trip<int> trip)
        {
            _tripRepository.Update(trip);
        }

        public void UpdateNumberOfSeatsAvailable(int numberOfSeats, int idTrip)
        {
            _tripRepository.UpdateNumberOfSeatsAvailable(numberOfSeats, idTrip);
        }

        public Trip<int> FindOneTrip(int idTrip)
        {
            return _tripRepository.FindOne(idTrip);
        }

        public IEnumerable<Trip<int>> FindAllTrips()
        {
            return _tripRepository.FindAll();
        }
    }
}