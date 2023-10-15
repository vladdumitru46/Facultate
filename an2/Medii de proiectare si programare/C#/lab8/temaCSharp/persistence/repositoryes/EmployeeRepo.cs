using System;
using System.Collections.Generic;
using model.domains;
using persistence.repositoryes;
using temaCSharp.repositoryes.interfaces;

namespace temaCSharp.repositoryes
{
    public class EmployeeRepo : IRepository<int, EmployeeAtOffice>, IRepoEmployee
    {
        public EmployeeAtOffice findOneByEmailAndPassword(string email, string password)
        {
            var con = DbcUtils.GetConnection();
            con.Open();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from employee where email=@email and password=@password";
                var paramEmail = comm.CreateParameter();
                paramEmail.ParameterName = "@email";
                paramEmail.Value = email;
                comm.Parameters.Add(paramEmail);
                var paramPassword = comm.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = password;
                comm.Parameters.Add(paramPassword);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        var idv = dataR.GetInt32(0);
                        var name = dataR.GetString(1);
                        return new EmployeeAtOffice(idv, name, email, password);
                    }
                }
            }

            return null;
        }

        public bool Login(string email, string password)
        {
            return findOneByEmailAndPassword(email, password) != null;
        }

        public EmployeeAtOffice Logout()
        {
            throw new NotImplementedException();
        }

        public EmployeeAtOffice Delete(int id)
        {
            var query = "DELETE FROM employee where id=@id";
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new Exception("NO task deleted");
            }

            return findOne(id);
        }

        public IEnumerable<EmployeeAtOffice> FindAll()
        {
            IList<EmployeeAtOffice> emplyees = new List<EmployeeAtOffice>();
            var query = "select * from employee";
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var idv = dataR.GetInt32(0);
                        var name = dataR.GetString(1);
                        var email = dataR.GetString(2);
                        var password = dataR.GetString(3);
                        var employeeAtOffice = new EmployeeAtOffice(idv, name, email, password);
                        emplyees.Add(employeeAtOffice);
                    }
                }
            }

            return emplyees;
        }

        public EmployeeAtOffice findOne(int id)
        {
            var con = DbcUtils.GetConnection();
            con.Open();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from employee where id=@id";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        var idv = dataR.GetInt32(0);
                        var name = dataR.GetString(1);
                        var email = dataR.GetString(2);
                        var password = dataR.GetString(3);
                        return new EmployeeAtOffice(id, name, email, password);
                    }
                }
            }

            return null;
        }

        public EmployeeAtOffice Save(EmployeeAtOffice entity)
        {
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Insert into employee values(@id, @name, @email, @password)";
                var paramID = comm.CreateParameter();
                paramID.ParameterName = "@id";
                paramID.Value = entity.IdEmployee;
                comm.Parameters.Add(paramID);
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@name";
                paramName.Value = entity.Name;
                comm.Parameters.Add(paramName);
                var paramEmail = comm.CreateParameter();
                paramEmail.ParameterName = "@email";
                paramEmail.Value = entity.Email;
                comm.Parameters.Add(paramEmail);
                var paramPassword = comm.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = entity.Password;
                comm.Parameters.Add(paramPassword);
                var result = comm.ExecuteNonQuery();
                if (result == 0) throw new Exception("NO employee");
            }

            return entity;
        }

        public EmployeeAtOffice Update(EmployeeAtOffice entity)
        {
            throw new NotImplementedException();
        }
    }
}