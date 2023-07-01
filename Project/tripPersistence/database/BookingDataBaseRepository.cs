using System;
using System.Collections.Generic;
using log4net;
using Npgsql;
using Proiect.repository;
using tripModel;
using tripPersistence;

namespace Proiect.database
{
    public class BookingDataBaseRepository : IBookingRepository
    {
        
        private string _url;

        private ILog _log;

        public BookingDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating BookingDataBaseRepository");
        }
        
        
        public Booking<int> Save(Booking<int> booking)
        {
            this._log.Info("Executing save booking");
            string sql = "INSERT INTO bookings(idperson, idcustomer, idtrip) VALUES (@IdPerson, @IdCustomer, @IdTrip)";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@IdPerson", booking.Person.Id);
                command.Parameters.AddWithValue("@IdCustomer", booking.Customer.Id);
                command.Parameters.AddWithValue("@IdTrip", booking.Trip.Id);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return booking;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }
            finally
            {
                connection.Close();
            }

            return null;
        }

        public Booking<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete booking");
            string sql = "DELETE FROM bookings WHERE idbooking = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new Booking<int>(idEntity, new Person<int>(1, "", "", "", "", 0),
                        new Customer<int>(2, "", "", "", 0), new Trip<int>(1, 1, 1, DateTime.Now, 0, 0));
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }
            finally
            {
                connection.Close();
            }

            return null;
        }

        public Booking<int> Update(Booking<int> booking)
        {
            this._log.Info("Executing update booking");
            string sql = "UPDATE bookings SET idperson = @IdPerson, idcustomer = @IdCustomer, idtrip = @IdTrip WHERE idbooking = @IdBooking";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@IdPerson", booking.Person);
                command.Parameters.AddWithValue("@IdCustomer", booking.Customer);
                command.Parameters.AddWithValue("@IdTrip", booking.Trip);
                command.Parameters.AddWithValue("@IdBooking", booking.Trip);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return booking;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }
            finally
            {
                connection.Close();
            }

            return null;
        }

        public Booking<int> FindOne(int idEntity)
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<Booking<int>> FindAll()
        {
            this._log.Info("Executing findAll booking");
            string sql = "SELECT B.idbooking, P.idperson, P.firstname, P.lastname, P.email, P.password, P.idtravelagency, C.idcustomer, C.firstname, C.lastname, C.phonenumber, C.numberoftickets, T.idtrip, T.idlandmark, T.idtransportationcompany, T.departuretime, T.price, T.numberofseatsavailable FROM bookings B INNER JOIN persons P on B.idperson = P.idperson INNER JOIN customers C on B.idcustomer = C.idcustomer INNER JOIN trips T on B.idtrip = T.idtrip";
            List<Booking<int>> bookings = new List<Booking<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idBookings = reader.GetInt32(0);

                    int idPerson = reader.GetInt32(1);
                    string firstName = reader.GetString(2);
                    string lastName = reader.GetString(3);
                    string email = reader.GetString(4);
                    string password = reader.GetString(5);
                    int idTravelAgency = reader.GetInt32(6);
                    Person<int> person =
                        new Person<int>(idPerson, firstName, lastName, email, password, idTravelAgency);

                    int idCustomer = reader.GetInt32(7);
                    string customerFirstName = reader.GetString(8);
                    string customerLastName = reader.GetString(9);
                    string phoneNumber = reader.GetString(10);
                    int numberOfTickets = reader.GetInt32(11);
                    Customer<int> customer = new Customer<int>(idCustomer, customerFirstName, customerLastName,
                        phoneNumber, numberOfTickets);

                    int idTrip = reader.GetInt32(12);
                    int idLandMark = reader.GetInt32(13);
                    int idTransportationCompany = reader.GetInt32(14);
                    DateTime departureTime = reader.GetDateTime(15);
                    double price = reader.GetDouble(16);
                    int numberOfSeatsAvailable = reader.GetInt32(17);

                    Trip<int> trip = new Trip<int>(idTrip, idLandMark, idTransportationCompany, departureTime, price,
                        numberOfSeatsAvailable);
                    Booking<int> booking = new Booking<int>(idBookings, person, customer, trip);
                    bookings.Add(booking);

                }
            }
            catch (NpgsqlException ex)
            {
                _log.Info("Error");
                return null;
            }
            finally
            {
                connection.Close();
            }

            return bookings;
        }
    }
}