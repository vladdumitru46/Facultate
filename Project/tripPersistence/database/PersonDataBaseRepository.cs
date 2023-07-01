using System.Collections.Generic;
using System.Web;
using log4net;
using Npgsql;
using tripModel;
using Proiect.repository;

namespace Proiect.database
{
    public class PersonDataBaseRepository : IPersonRepository
    {
        private string _url;

        private ILog _log;

        public PersonDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating PersonDataBaseRepository");
        }

        public Person<int> Save(Person<int> person)
        {
            this._log.Info("Executing save person");
            string sql = "INSERT INTO persons(firstname, lastname, email, password, idtravelagency) VALUES (@FirstName, @LastName, @Email, @Password, @IdTravelAgency)";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@FirstName", person.FirstName);
                command.Parameters.AddWithValue("@LastName", person.LastName);
                command.Parameters.AddWithValue("@Email", person.Email);
                command.Parameters.AddWithValue("@Password", person.Password);
                command.Parameters.AddWithValue("@IdTravelAgency", person.IdTravelAgency);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return person;
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


        public Person<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete person");
            string sql = "DELETE FROM persons WHERE idperson = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new Person<int>(idEntity, "", "", "", "", 0);
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


        public Person<int> Update(Person<int> person)
        {
            this._log.Info("Executing update person");
            string sql = "UPDATE persons SET firstname = @FirstName, lastname = @LastName, email = @Email, password = @Password, idtravelagency = @IdTravelAgency WHERE idperson = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@FirstName", person.FirstName);
                command.Parameters.AddWithValue("@LastName", person.LastName);
                command.Parameters.AddWithValue("@Email", person.Email);
                command.Parameters.AddWithValue("@Password", person.Password);
                command.Parameters.AddWithValue("@IdTravelAgency", person.IdTravelAgency);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return person;
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

        public Person<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne person");
            string sql = "SELECT * FROM persons WHERE idlandmark = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idPerson = reader.GetInt32(0);
                    string firstName = reader.GetString(1);
                    string lastName = reader.GetString(2);
                    string email = reader.GetString(3);
                    string password = reader.GetString(4);
                    int idTravelAgency = reader.GetInt32(5);
                    Person<int> landMark =
                        new Person<int>(idPerson, firstName, lastName, email, password, idTravelAgency);
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

        public IEnumerable<Person<int>> FindAll()
        {
            this._log.Info("Executing findAll person");
            string sql = "SELECT * FROM persons";
            List<Person<int>> persons = new List<Person<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idPerson = reader.GetInt32(0);
                    string firstName = reader.GetString(1);
                    string lastName = reader.GetString(2);
                    string email = reader.GetString(3);
                    string password = reader.GetString(4);
                    int idTravelAgency = reader.GetInt32(5);
                    Person<int> person =
                        new Person<int>(idPerson, firstName, lastName, email, password, idTravelAgency);
                    persons.Add(person);
                }
            }
            catch (NpgsqlException ex)
            {
                this._log.Info("Error");
                return null;
            }
            finally
            {
                connection.Close();
            }
            this._log.Info(persons);
            return persons;
        }

        public bool CheckEmailExistenceInPersons(string email)
        {
            this._log.Info("Executing checkEmailExistenceInPersons person");
            string sql = "SELECT email FROM persons WHERE email = @Email";
            try
            {
                var connection = new NpgsqlConnection(_url);
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Email", email);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    return true;
                }
            }
            catch (NpgsqlException ex)
            {
                return false;
            }

            return true;

        }

        public int CheckPersonAccountExistence(string email, string password)
        {
            this._log.Info("Executing checkPersonAccountExistence person");
            string sql = "SELECT idperson FROM persons WHERE email = @Email AND password = @Password";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Email", email);
                command.Parameters.AddWithValue("@Password", password);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    this._log.Info(true);
                    return reader.GetInt32(0);
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
        
        

        public Person<int> FindPersonByEmail(string emailAddress)
        {
            this._log.Info("Executing findPersonByEmail person");
            string sql = "SELECT * FROM persons WHERE email = @Email";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Email", emailAddress);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idPerson = reader.GetInt32(0);
                    string firstName = reader.GetString(1);
                    string lastName = reader.GetString(2);
                    string email = reader.GetString(3);
                    string password = reader.GetString(4);
                    int idTravelAgency = reader.GetInt32(5);
                    Person<int> person =
                        new Person<int>(idPerson, firstName, lastName, email, password, idTravelAgency);
                    return person;
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
        
    }
}