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
    internal class ArtistRepository : IRepository<String, Artist>
    {
        private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");
        public Artist delete(string id)
        {
            log.InfoFormat("Entering delete with value {0}",id);
            String query = "DELETE FROM artists where name=@id";
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
            log.InfoFormat("Exiting delete with value {0}", id);
            return findOne(id);
        }

        public IEnumerable<Artist> findAll()
        {
            log.InfoFormat("Entering findAll with value {0}");
            IList<Artist> artists = new List<Artist>();
            String query = "select * from artists";
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        String name = dataR.GetString(0);
                        String stageName = dataR.GetString(1);
                        int age = dataR.GetInt32(2);
                        Artist artist = new Artist(name, stageName, age);
                        artists.Add(artist);
                    }
                }

            }
            log.InfoFormat("Exiting findAll with value {0}", null);
            return artists;
        }

        public Artist findOne(string id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists where \"stageName\"=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String name = dataR.GetString(0);
                        String stageName = dataR.GetString(1);
                        int age = dataR.GetInt32(2);
                        Artist artist = new Artist(name, stageName, age);
                        return artist;
                    }
                }
            }
            log.InfoFormat("Entering findOne with value {0}", id);
            return null;
        }

        public Artist save(Artist entity)
        {
            log.InfoFormat("Entering save with value {0}", entity);
            var con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Insert into artists values(@name, @stageName, @age)";
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@name";
                paramName.Value = entity.Name;
                comm.Parameters.Add(paramName); 

                var paramStageName = comm.CreateParameter();
                paramStageName.ParameterName = "@stageName";
                paramStageName.Value = entity.stageName;
                comm.Parameters.Add(paramStageName);

                var paramAge = comm.CreateParameter();
                paramAge.ParameterName = "@age";
                paramAge.Value = entity.age;
                comm.Parameters.Add(paramAge);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No artist added !");
            }
            log.InfoFormat("Entering save with value {0}", entity);
            return entity;
        }

        public Artist update(Artist entity)
        {
            throw new NotImplementedException();
        }
    }
}
