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
    internal class ShowRepo : IRepository<String, Show>, RepoShow
    {

        private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");

        public Show delete(string id)
        {
            log.InfoFormat("Entering delete with value {0}", id);
            String query = "DELETE FROM shows where \"showName\"=@id";
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
                    throw new Exception("NO shows deleted");
            }
            log.InfoFormat("Exiting delete with value {0}", id);
            return findOne(id);
        }

        public IEnumerable<Show> findAll()
        {
            IList<Show> list = new List<Show>();
            log.InfoFormat("Entering findAll with value {0}");
            IList<Artist> artists = new List<Artist>();
            String query = "select * from shows";
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = query;
                using (var dataR = comm.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        String showName = dataR.GetString(0);
                        String artistName = dataR.GetString(1);
                        DateTime date = dataR.GetDateTime(2);
                        String placeOfShow = dataR.GetString(3);
                        int noTicketsAvailabale = dataR.GetInt32(4);
                        int noTicketsSold = dataR.GetInt32(5);
                        list.Add(new Show(showName, artistName, noTicketsAvailabale, noTicketsSold, date, placeOfShow));

                    }
                }

            }
            log.InfoFormat("Exiting findAll with value {0}", null);
            return list;
        }

        public Show findOne(string id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select * from shows where \"showName\"=@id";
                IDbDataParameter paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                comm.Parameters.Add(paramId);

                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        String showName = dataR.GetString(0);
                        String artistName = dataR.GetString(1);
                        DateTime date = dataR.GetDateTime(2);
                        String placeOfShow = dataR.GetString(3);
                        int noTicketsAvailabale = dataR.GetInt32(4);
                        int noTicketsSold = dataR.GetInt32(5);
                        return new Show(showName, artistName, noTicketsAvailabale, noTicketsSold, date, placeOfShow);

                    }
                }
            }
            log.InfoFormat("Entering findOne with value {0}", id);
            return null;
        }

        public Show save(Show entity)
        {
            log.InfoFormat("Entering save with value {0}", entity);
            var con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Insert into shows values(@showName, @artistName, @date, @placeOfShow, @noTicktesAvailable, @noTicketsSold)";
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
                paramAge.Value = entity.date;
                comm.Parameters.Add(paramAge);


                var paramPlace = comm.CreateParameter();
                paramPlace.ParameterName = "@placeOfShow";
                paramPlace.Value = entity.placeOfShow;
                comm.Parameters.Add(paramPlace);


                var paramTicketsA = comm.CreateParameter();
                paramTicketsA.ParameterName = "@noTicktesAvailable";
                paramTicketsA.Value = entity.noTicketsAvailabe;
                comm.Parameters.Add(paramTicketsA);


                var paramATicktesS = comm.CreateParameter();
                paramATicktesS.ParameterName = "@noTicketsSold";
                paramATicktesS.Value = entity.noTicketsSold;
                comm.Parameters.Add(paramATicktesS);

                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No show added !");
            }
            log.InfoFormat("Entering save with value {0}", entity);
            return entity;
        }

        public IEnumerable<Show> searchArtist(DateTime date)
        {
            IList<Show> artists = new List<Show>();
            log.InfoFormat("Entering searchArtists with value {0}", date);
            IDbConnection con = DBCUtils.GetConnection();
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
                        string showName = dataR.GetString(0);
                        string artistName = dataR.GetString(1);
                        string placeOfShow = dataR.GetString(3);
                        int noTicketsA = dataR.GetInt32(4);
                        int noTicketsS = dataR.GetInt32(5);
                        Show show = new Show(showName, artistName, noTicketsA, noTicketsS, date, placeOfShow);
                        artists.Add(show);
                    }
                }
            }
            log.InfoFormat("Exiting search artist with value {0}", null);
            return artists;
        }

        public Show update(Show entity)
        {
            var con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Update shows set \"noTicketsAvailable\"=@nA, \"noTicketsSold\"=@nS where \"showName\"=@sn";
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@nA";
                paramName.Value = entity.noTicketsAvailabe;
                comm.Parameters.Add(paramName);

                var paramNameS = comm.CreateParameter();
                paramNameS.ParameterName = "@nS";
                paramNameS.Value = entity.noTicketsSold;
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
    }

}
