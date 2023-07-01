using System;
using persistence.repositoryes;

namespace temaCSharp.repositoryes
{
    public class BuyerRepo : IRepoBuyer
    {
        public void SellTicketsToShow(string showName, string buyerName, int noOfTickets)
        {
            var con = DbcUtils.GetConnection();
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
        }
    }
}