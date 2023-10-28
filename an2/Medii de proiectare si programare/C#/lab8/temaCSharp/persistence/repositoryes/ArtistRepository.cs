using System;
using System.Collections.Generic;
using model.domains;
using persistence.repositoryes;
using temaCSharp.repositoryes.interfaces;

namespace temaCSharp.repositoryes
{
    public class ArtistRepository : IRepository<string, Artist>
    {
        public Artist Delete(string id)
        {
            var query = "DELETE FROM artists where name=@id";
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

        public IEnumerable<Artist> FindAll()
        {
            IList<Artist> artists = new List<Artist>();
            var query = "select * from artists";
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var name = dataR.GetString(0);
                        var stageName = dataR.GetString(1);
                        var age = dataR.GetInt32(2);
                        var artist = new Artist(name, stageName, age);
                        artists.Add(artist);
                    }
                }
            }

            return artists;
        }

        public Artist findOne(string id)
        {
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from artists where \"stageName\"=@id";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        var name = dataR.GetString(0);
                        var stageName = dataR.GetString(1);
                        var age = dataR.GetInt32(2);
                        var artist = new Artist(name, stageName, age);
                        return artist;
                    }
                }
            }

            return null;
        }

        public Artist Save(Artist entity)
        {
            var con = DbcUtils.GetConnection();
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
                paramStageName.Value = entity.StageName;
                comm.Parameters.Add(paramStageName);

                var paramAge = comm.CreateParameter();
                paramAge.ParameterName = "@age";
                paramAge.Value = entity.Age;
                comm.Parameters.Add(paramAge);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No artist added !");
            }

            return entity;
        }

        public Artist Update(Artist entity)
        {
            throw new NotImplementedException();
        }
    }
}