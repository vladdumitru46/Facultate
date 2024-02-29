using log4net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaCSharp.repositoryes;

namespace temaCSharp.repositoryes
{
    internal class BuyerRepo : RepoBuyer
    {
        private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");

        public void sellTicketsToShow(string showName, string buyerName, int noOfTickets)
        {
            log.InfoFormat("Entering sell with value {0}", buyerName);
            var con = DBCUtils.GetConnection();
            con.Open();
            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "Insert into buyers values(@buyerName, @noOfTickets, @noOfTickets)";
                var paramName = comm.CreateParameter();
                paramName.ParameterName = "@buyerName";
                paramName.Value = buyerName;
                comm.Parameters.Add(paramName);

                var paramT = comm.CreateParameter();
                paramT.ParameterName = "@noOfTickets";
                paramT.Value = noOfTickets;
                comm.Parameters.Add(paramT);

                var paramT2 = comm.CreateParameter();
                paramT2.ParameterName = "@noOfTickets";
                paramT2.Value = showName;
                comm.Parameters.Add(paramT2);
                var result = comm.ExecuteNonQuery();
                if (result == 0)
                    throw new Exception("No buyer added !");
            }
            log.InfoFormat("Exiting sell with value {0}", buyerName);
        }
    }
}
