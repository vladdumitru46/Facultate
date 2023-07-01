using System.Collections.Generic;
using log4net;
using Npgsql;
using tripModel;
using Proiect.repository;

namespace Proiect.database
{
    public class TravelAgencyDataBaseRepository : ITravelAgencyRepository
    {
        
        private string _url;

        private ILog _log;

        public TravelAgencyDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating TravelAgencyDataBaseRepository");
        }
        
        public TravelAgency<int> Save(TravelAgency<int> travelAgency)
        {
            this._log.Info("Executing save travelAgency");
            string sql = "INSERT INTO travelagency(name) VALUES (@Name)";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", travelAgency.Name);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return travelAgency;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public TravelAgency<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete travelAgency");
            string sql = "DELETE FROM travelagency WHERE idtrip = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new TravelAgency<int>(idEntity, "");
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public TravelAgency<int> Update(TravelAgency<int> travelAgency)
        {
            this._log.Info("Executing update travelAgency");
            string sql = "UPDATE travelagency SET name = @Name WHERE idtravelagency = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", travelAgency.Name);
                command.Parameters.AddWithValue("@Id", travelAgency.Id);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return travelAgency;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public TravelAgency<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne travelAgency");
            string sql = "SELECT * FROM travelAgency WHERE idtravelagency = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idTravelAgency = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    TravelAgency<int> travelAgency = new TravelAgency<int>(idTravelAgency, name);
                    return travelAgency;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public IEnumerable<TravelAgency<int>> FindAll()
        {
            this._log.Info("Executing findAll travelAgency");
            string sql = "SELECT * FROM travelAgency";
            List<TravelAgency<int>> travelAgencies = new List<TravelAgency<int>>();
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idTravelAgency = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    TravelAgency<int> travelAgency = new TravelAgency<int>(idTravelAgency, name);
                    travelAgencies.Add(travelAgency);
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return travelAgencies;
        }
    }
}