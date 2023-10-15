using log4net;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaCSharp.domains;
using temaCSharp.repositoryes;

namespace temaCSharp.repositoryes
{
    internal class EmployeeRepo : IRepository<int, EmplyeeAtOffice>, RepoEmployee
    {
        private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");
        public EmplyeeAtOffice delete(int id)
        {
            log.InfoFormat("Entering delete with value {0}");
            String query = "DELETE FROM employee where id=@id";
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);
                var dataR = comm.ExecuteNonQuery();
                if (dataR == 0)
                    throw new Exception("NO task deleted");
            }
            log.InfoFormat("Exiting delete with value {0}", null);
            return findOne(id);
        }

        public IEnumerable<EmplyeeAtOffice> findAll()
        {
            log.InfoFormat("Entering findAll with value {0}");
            IList<EmplyeeAtOffice> emplyees = new List<EmplyeeAtOffice>();
            String query = "select * from employee";
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;   
                using(var dataR = comm.ExecuteReader())
                {
                    while(dataR.Read())
                    {
                        int idv = dataR.GetInt32(0);
                        String name = dataR.GetString(1);
                        String email = dataR.GetString(2);
                        String password = dataR.GetString(3);
                        EmplyeeAtOffice emplyeeAtOffice = new EmplyeeAtOffice(idv, name, email, password);
                        emplyees.Add(emplyeeAtOffice);
                    }
                }
                
            }
            log.InfoFormat("Exiting findAll with value {0}", null);
            return emplyees;
        }

        public EmplyeeAtOffice findOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from employee where id=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idv = dataR.GetInt32(0);
                        String name = dataR.GetString(1);
                        String email = dataR.GetString(2);
                        String password = dataR.GetString(3);
                        return new EmplyeeAtOffice(id, name, email, password);
                    }
                }
            }
            log.InfoFormat("Exiting findOne with value {0}", null);
            return null;
        }

        public EmplyeeAtOffice findOneByEmailAndPassword(string email, string password)
        {
            log.InfoFormat("Entering findOneByEmailAndPassword with value {0}", email);
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from employee where email=@email and password=@password";
                IDbDataParameter paramEmail = comm.CreateParameter();
                paramEmail.ParameterName = "@email";
                paramEmail.Value = email;
                comm.Parameters.Add(paramEmail);
                IDbDataParameter paramPassword = comm.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = password;
                comm.Parameters.Add(paramPassword);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idv = dataR.GetInt32(0);
                        String name = dataR.GetString(1);
                        return new EmplyeeAtOffice(idv, name, email, password);
                    }
                }
            }
            log.InfoFormat("Exiting findOneByEmailAndPassword with value {0}", null);
            return null;
        }

        public bool login(string email, string password)
        {
            log.InfoFormat("Entering logIn with value {0}");
            return findOneByEmailAndPassword(email, password) != null;
        }

        public EmplyeeAtOffice logout()
        {
            throw new NotImplementedException();
        }

        public EmplyeeAtOffice save(EmplyeeAtOffice entity)
        {
            var con = DBCUtils.GetConnection();
            con.Open();
            log.InfoFormat("Entering save with value {0}", entity);
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Insert into employee values(@id, @name, @email, @password)";
                var paramID = comm.CreateParameter();
                paramID.ParameterName = "@id";
                paramID.Value = entity.id;
                comm.Parameters.Add(paramID); 
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@name";
                paramName.Value = entity.name;
                comm.Parameters.Add(paramName);
                IDbDataParameter paramEmail = comm.CreateParameter();
                paramEmail.ParameterName = "@email";
                paramEmail.Value = entity.email;
                comm.Parameters.Add(paramEmail);
                IDbDataParameter paramPassword = comm.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = entity.password;
                comm.Parameters.Add(paramPassword);
                var result = comm.ExecuteNonQuery();
                if (result == 0)
                {
                    throw new Exception("NO employee");
                }
            }
            log.InfoFormat("Exiting save with value {0}", entity);
            return entity;
        }

        public EmplyeeAtOffice update(EmplyeeAtOffice entity)
        {
            throw new NotImplementedException();
        }
    }
}
