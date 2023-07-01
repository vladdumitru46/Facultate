using System;
using System.Collections.Generic;
using log4net;
using model.domains;
using persistence.repositoryes;
using temaCSharp.repositoryes.interfaces;

namespace temaCSharp.repositoryes
{
    public class ShowRepo : IRepository<string, Show>, RepoShow
    {
        private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");

        public Show Delete(string id)
        {
            log.InfoFormat("Entering delete with value {0}", id);
            var query = "DELETE FROM shows where \"showName\"=@id";
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
                    throw new Exception("NO shows deleted");
            }

            log.InfoFormat("Exiting delete with value {0}", id);
            return findOne(id);
        }

        public IEnumerable<Show> FindAll()
        {
            IList<Show> list = new List<Show>();
            log.InfoFormat("Entering findAll with value {0}");
            IList<Artist> artists = new List<Artist>();
            var query = "select * from shows";
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        var showName = dataR.GetString(0);
                        var artistName = dataR.GetString(1);
                        var date = dataR.GetDateTime(2);
                        var placeOfShow = dataR.GetString(3);
                        var noTicketsAvailable = dataR.GetInt32(4);
                        var noTicketsSold = dataR.GetInt32(5);
                        list.Add(new Show(showName, artistName, date, placeOfShow, noTicketsAvailable, noTicketsSold));
                    }
                }
            }

            log.InfoFormat("Exiting findAll with value {0}", null);
            return list;
        }

        public Show findOne(string id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from shows where \"showName\"=@id";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        var showName = dataR.GetString(0);
                        var artistName = dataR.GetString(1);
                        var date = dataR.GetDateTime(2);
                        var placeOfShow = dataR.GetString(3);
                        var noTicketsAvailabale = dataR.GetInt32(4);
                        var noTicketsSold = dataR.GetInt32(5);
                        return new Show(showName, artistName, date, placeOfShow, noTicketsAvailabale, noTicketsSold);
                    }
                }
            }

            log.InfoFormat("Entering findOne with value {0}", id);
            return null;
        }

        public Show Save(Show entity)
        {
            log.InfoFormat("Entering save with value {0}", entity);
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText =
                    "Insert into shows values(@showName, @artistName, @date, @placeOfShow, @noTicktesAvailable, @noTicketsSold)";
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@showName";
                paramName.Value = entity.ShowName;
                comm.Parameters.Add(paramName);

                var paramStageName = comm.CreateParameter();
                paramStageName.ParameterName = "@artistName";
                paramStageName.Value = entity.Artist;
                comm.Parameters.Add(paramStageName);

                var paramAge = comm.CreateParameter();
                paramAge.ParameterName = "@date";
                paramAge.Value = entity.Date;
                comm.Parameters.Add(paramAge);


                var paramPlace = comm.CreateParameter();
                paramPlace.ParameterName = "@placeOfShow";
                paramPlace.Value = entity.PlaceOfShow;
                comm.Parameters.Add(paramPlace);


                var paramTicketsA = comm.CreateParameter();
                paramTicketsA.ParameterName = "@noTicktesAvailable";
                paramTicketsA.Value = entity.NoTicketsAvailable;
                comm.Parameters.Add(paramTicketsA);


                var paramATicktesS = comm.CreateParameter();
                paramATicktesS.ParameterName = "@noTicketsSold";
                paramATicktesS.Value = entity.NoTicketsSold;
                comm.Parameters.Add(paramATicktesS);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No show added !");
            }

            log.InfoFormat("Entering save with value {0}", entity);
            return entity;
        }

        public Show Update(Show entity)
        {
            var con = DbcUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText =
                    "Update shows set \"noTicketsAvailable\"=@nA, \"noTicketsSold\"=@nS where \"showName\"=@sn";
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@nA";
                paramName.Value = entity.NoTicketsAvailable;
                comm.Parameters.Add(paramName);

                var paramNameS = comm.CreateParameter();
                paramNameS.ParameterName = "@nS";
                paramNameS.Value = entity.NoTicketsSold;
                comm.Parameters.Add(paramNameS);

                var paramNameD = comm.CreateParameter();
                paramNameD.ParameterName = "@sn";
                paramNameD.Value = entity.ShowName;
                comm.Parameters.Add(paramNameD);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No show added !");
            }

            con.Close();
            return entity;
        }

        public IEnumerable<Show> SearchArtist(DateTime date)
        {
            IList<Show> artists = new List<Show>();
            log.InfoFormat("Entering searchArtists with value {0}", date);
            var con = DbcUtils.GetConnection();
            con.Open();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from shows where date=@d";

                var paramATicktesS = comm.CreateParameter();
                paramATicktesS.ParameterName = "@d";
                paramATicktesS.Value = date;
                comm.Parameters.Add(paramATicktesS);
                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        var showName = dataR.GetString(0);
                        var artistName = dataR.GetString(1);
                        var placeOfShow = dataR.GetString(3);
                        var noTicketsA = dataR.GetInt32(4);
                        var noTicketsS = dataR.GetInt32(5);
                        var show = new Show(showName, artistName, date, placeOfShow, noTicketsA, noTicketsS);
                        artists.Add(show);
                    }
                }
            }

            log.InfoFormat("Exiting search artist with value {0}", null);
            return artists;
        }
    }
}