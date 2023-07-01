using System;
using System.Collections.Generic;
using log4net;
using Npgsql;
using tripModel;
using Proiect.repository;

namespace Proiect.database
{
    public class TripDataBaseRepository : ITripRepository
    {
        private string _url;

        private ILog _log;

        public TripDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating TripDataBaseRepository");
        }
        
        
        public Trip<int> Save(Trip<int> trip)
        {
            this._log.Info("Executing save trip");
            string sql = "INSERT INTO trips(idlandmark, idtransportationcompany, departuretime, price, numberofseatsavailable) VALUES (@IdLandMark, @IdTransportationCompany, @DepartureTime, @Price, @NumberOfSeatsAvailable)";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@IdLandMark", trip.IdLandMark);
                command.Parameters.AddWithValue("@IdTransportationCompany", trip.IdTransportationCompany);
                command.Parameters.AddWithValue("@DepartureTime", trip.DepartureTime);
                command.Parameters.AddWithValue("@Price", trip.Price);
                command.Parameters.AddWithValue("@NumberOfSeatsAvailable", trip.NumberOfSeatsAvailable);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return trip;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public Trip<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete trip");
            string sql = "DELETE FROM trips WHERE idtrip = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new Trip<int>(idEntity, 0, 0, DateTime.Now,  0, 0);
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public Trip<int> Update(Trip<int> trip)
        {
            this._log.Info("Executing update trip");
            string sql = "UPDATE trips SET idlandmark = @IdLandMark, idtransportationcompany = @IdTransportationCompany, departuretime = @DepartureTime, price = @Price, numberofseatsavailable = @NumberOfSeatsAvailable WHERE idtrip = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@IdLandMark", trip.IdLandMark);
                command.Parameters.AddWithValue("@IdTransportationCompany", trip.IdTransportationCompany);
                command.Parameters.AddWithValue("@DepartureTime", trip.DepartureTime);
                command.Parameters.AddWithValue("@Price", trip.Price);
                command.Parameters.AddWithValue("@NumberOfSeatsAvailable", trip.NumberOfSeatsAvailable);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return trip;
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

        public Trip<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne trip");
            string sql = "SELECT * FROM trips WHERE idtrip = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idTrip = reader.GetInt32(0);
                    int idLandMark = reader.GetInt32(1);
                    int idTransportationCompany = reader.GetInt32(2);
                    DateTime departureTime = reader.GetDateTime(3);
                    double price = reader.GetDouble(4);
                    int numberOfSeatsAvailable = reader.GetInt32(5);
                    Trip<int> trip = new Trip<int>(idTrip, idLandMark, idTransportationCompany, departureTime, price,
                        numberOfSeatsAvailable);
                    return trip;
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

        public IEnumerable<Trip<int>> FindAll()
        {
            this._log.Info("Executing findAll trip");
            string sql = "SELECT * FROM trips";
            List<Trip<int>> trips = new List<Trip<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idTrip = reader.GetInt32(0);
                    int idLandMark = reader.GetInt32(1);
                    int idTransportationCompany = reader.GetInt32(2);
                    DateTime departureTime = reader.GetDateTime(3);
                    double price = reader.GetDouble(4);
                    int numberOfSeatsAvailable = reader.GetInt32(5);
                    Trip<int> trip = new Trip<int>(idTrip, idLandMark, idTransportationCompany, departureTime, price,
                        numberOfSeatsAvailable);
                    trips.Add(trip);
                }
            }
            catch (NpgsqlException ex)
            {
                Console.WriteLine(ex.StackTrace);
                return null;
            }
            finally
            {
                connection.Close();
            }

            return trips;
        }
        
        public List<Trip<int>> FindAllTripsByLandMark(int idLandMark)
        {
            this._log.Info("Executing findAllTripsByLandMark trip");
            string sql = "SELECT * FROM trips WHERE idlandmark = @IdLandMark";
            List<Trip<int>> trips = new List<Trip<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@IdLandMark", idLandMark);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idTrip = reader.GetInt32(0);
                    int idLMark = reader.GetInt32(1);
                    int idTransportationCompany = reader.GetInt32(2);
                    DateTime departureTime = reader.GetDateTime(3);
                    double price = reader.GetFloat(4);
                    int numberOfSeatsAvailable = reader.GetInt32(5);
                    Trip<int> trip = new Trip<int>(idTrip, idLMark, idTransportationCompany, departureTime, price,
                        numberOfSeatsAvailable);
                    trips.Add(trip);
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

            return trips;
        }

        public int UpdateNumberOfSeatsAvailable(int numberOfSeats, int idTrip)
        {
            this._log.Info("Executing update trip");
            string sql = "UPDATE trips SET numberofseatsavailable = numberofseatsavailable - @NumberOfSeatsAvailable WHERE idtrip = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@NumberOfSeatsAvailable", numberOfSeats);
                command.Parameters.AddWithValue("@Id", idTrip);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return numberOfSeats;
                }
            }
            catch (NpgsqlException ex)
            {
                return 0;
            }
            finally
            {
                connection.Close();
            }

            return 0;
        }
        
    }
}