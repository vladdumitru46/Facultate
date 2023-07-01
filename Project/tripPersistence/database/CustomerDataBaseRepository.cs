using System.Collections.Generic;
using log4net;
using Npgsql;
using tripModel;
using Proiect.repository;

namespace Proiect.database
{
    public class CustomerDataBaseRepository : ICustomerRepository
    {
        private string _url;

        private ILog _log;

        public CustomerDataBaseRepository(string url, ILog log)
        {
            this._url = url;
            this._log = log;
            this._log.Info("Creating CustomerDataBaseRepository");
        }
        
        
        public Customer<int> Save(Customer<int> customer)
        {
            this._log.Info("Executing save customer");
            string sql = "INSERT INTO customers(firstname, lastname, phonenumber, numberoftickets) VALUES (@FirstName, @LastName, @PhoneNumber, @NumberOfTickets)";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@FirstName", customer.FirstName);
                command.Parameters.AddWithValue("@LastName", customer.LastName);
                command.Parameters.AddWithValue("@PhoneNumber", customer.PhoneNumber);
                command.Parameters.AddWithValue("@NumberOfTickets", customer.NumberOfTickets);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return customer;
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

        public Customer<int> Delete(int idEntity)
        {
            this._log.Info("Executing delete customer");
            string sql = "DELETE FROM customers WHERE idcustomer = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                var rowsNumber = command.ExecuteNonQuery();
                if (rowsNumber > 0)
                {
                    return new Customer<int>(idEntity, "", "", "", 0);
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

        public Customer<int> Update(Customer<int> customer)
        {
            this._log.Info("Executing update customer");
            string sql = "UPDATE customers SET firstname = @FirstName, lastname = @LastName, phonenumber = @PhoneNumber, numberoftickets = @NumberOfTickets WHERE idcustomer = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@FirstName", customer.FirstName);
                command.Parameters.AddWithValue("@LastName", customer.LastName);
                command.Parameters.AddWithValue("@PhoneNumber", customer.PhoneNumber);
                command.Parameters.AddWithValue("@NumberOfTickets", customer.NumberOfTickets);
                connection.Open();
                var result = command.ExecuteNonQuery();
                if (result > 0)
                {
                    return customer;
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

        public Customer<int> FindOne(int idEntity)
        {
            this._log.Info("Executing findOne customer");
            string sql = "SELECT * FROM customers WHERE idcustomer = @Id";
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                command.Parameters.AddWithValue("@Id", idEntity);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int idCustomer = reader.GetInt32(0);
                    string firstName = reader.GetString(1);
                    string lastName = reader.GetString(2);
                    string phoneNumber = reader.GetString(3);
                    int numberOfTickets = reader.GetInt32(4);
                    Customer<int> customer =
                        new Customer<int>(idCustomer, firstName, lastName, phoneNumber, numberOfTickets);
                    return customer;
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

        public IEnumerable<Customer<int>> FindAll()
        {
            this._log.Info("Executing findOne customer");
            string sql = "SELECT * FROM customers";
            List<Customer<int>> customers = new List<Customer<int>>();
            var connection = new NpgsqlConnection(_url);
            try
            {
                var command = new NpgsqlCommand(sql, connection);
                connection.Open();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    int idCustomer = reader.GetInt32(0);
                    string firstName = reader.GetString(1);
                    string lastName = reader.GetString(2);
                    string phoneNumber = reader.GetString(3);
                    int numberOfTickets = reader.GetInt32(4);
                    Customer<int> customer =
                        new Customer<int>(idCustomer, firstName, lastName, phoneNumber, numberOfTickets);
                    customers.Add(customer);
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

            return customers;
        }
    }
}