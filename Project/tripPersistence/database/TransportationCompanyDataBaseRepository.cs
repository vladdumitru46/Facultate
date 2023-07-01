using System;
using System.Collections.Generic;
using log4net;
using Npgsql;
using tripModel;
using Proiect.repository;

namespace Proiect.database
{
    public class TransportationCompanyDataBaseRepository : ITransportationCompanyRepository
    {
        private string _url;

        private ILog _log;

        public TransportationCompanyDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating TransportationCompanyDataBaseRepository");
        }
        
        public TransportationCompany<int> Save(TransportationCompany<int> transportationCompany)
        {
            this._log.Info("Executing save transportationCompany");
            string sql = "INSERT INTO transportationcompany(name) VALUES (@Name)";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", transportationCompany.Name);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return transportationCompany;
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

        public TransportationCompany<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete transportationCompany");
            string sql = "DELETE FROM transporationcompany WHERE idtransportationcompany = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new TransportationCompany<int>(idEntity, "");
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

        public TransportationCompany<int> Update(TransportationCompany<int> transportationCompany)
        {
            this._log.Info("Executing update transportationCompany");
            string sql = "UPDATE transportationCompany SET name = @Name WHERE idtransportationcompany = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", transportationCompany.Name);
                command.Parameters.AddWithValue("@Id", transportationCompany.Id);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return transportationCompany;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }finally
            {
                connection.Close();
            }

            return null;
        }

        public TransportationCompany<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne transportationCompany");
            string sql = "SELECT * FROM transportationcompany WHERE idtransportationcompany = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idTransportationCompany = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    TransportationCompany<int> travelAgency =
                        new TransportationCompany<int>(idTransportationCompany, name);
                    return travelAgency;
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

        public IEnumerable<TransportationCompany<int>> FindAll()
        {
            this._log.Info("Executing findAll transportationcompany");
            string sql = "SELECT * FROM transportationcompany";
            List<TransportationCompany<int>> transportationCompanies = new List<TransportationCompany<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idTransportationCompany = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    TransportationCompany<int> transportationCompany =
                        new TransportationCompany<int>(idTransportationCompany, name);
                    transportationCompanies.Add(transportationCompany);
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

            return transportationCompanies;
        }
    }
}