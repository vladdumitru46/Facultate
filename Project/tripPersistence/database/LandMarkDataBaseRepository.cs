using System.Collections.Generic;
using tripModel;
using Proiect.repository;
using Npgsql;
using log4net;

namespace Proiect.database
{
    public class LandMarkDataBaseRepository : ILandMarkRepository
    {

        private string _url;
        
        private ILog _log;
        public LandMarkDataBaseRepository(string url, ILog logger)
        {
            this._url = url;
            this._log = logger;
            this._log.Info("Creating LandMarkDataBaseRepository");
        }
        
        public LandMark<int> Save(LandMark<int> landMark)
        {
            this._log.Info("Executing save landmark");
            string sql = "INSERT INTO landmarks (name, city) VALUES (@Name, @City)";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", landMark.Name);
                command.Parameters.AddWithValue("@City", landMark.City);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return landMark;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public LandMark<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete landmark");
            string sql = "DELETE FROM landmarks WHERE idlandmark = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new LandMark<int>(idEntity, "", "");
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public LandMark<int> Update(LandMark<int> landmark)
        {
            this._log.Info("Executing update landmark");
            string sql = "UPDATE landmarks SET name = @Name, city = @City WHERE idlandmark = @Id";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Name", landmark.Name);
                command.Parameters.AddWithValue("@City", landmark.City);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return landmark;
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return null;
        }

        public LandMark<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne landmark");
            string sql = "SELECT * FROM landmarks WHERE idlandmark = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idlandmark = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    string city = reader.GetString(2);
                    LandMark<int> landMark = new LandMark<int>(idlandmark, name, city);
                    return landMark;
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

        public IEnumerable<LandMark<int>> FindAll()
        {
            this._log.Info("Executing findAll landmark");
            string sql = "SELECT * FROM landmarks";
            List<LandMark<int>> landMarks = new List<LandMark<int>>();
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idlandmark = reader.GetInt32(0);
                    string name = reader.GetString(1);
                    string city = reader.GetString(2);
                    LandMark<int> landMark = new LandMark<int>(idlandmark, name, city);
                    landMarks.Add(landMark);
                }
            }
            catch (NpgsqlException ex)
            {
                return null;
            }

            return landMarks;
        }
    }
}