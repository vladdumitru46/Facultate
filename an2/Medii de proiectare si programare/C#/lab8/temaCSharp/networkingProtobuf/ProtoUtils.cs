using System.Xml.Serialization;
using Google.Protobuf.Reflection;
using model.domains;
using Proto;
using EmployeeAtOffice = model.domains.EmployeeAtOffice;

namespace networkingProtobuf
{
    public class ProtoUtils
    {
        public static Response CreateNewSellTicketResponse(Buyer buyers)
        {
            Proto.Buyers buyersNew = new Proto.Buyers
            {
                ShowName = buyers.ShowName1,
                BuyerName = buyers.BuyerName1,
                NoOfTickets = buyers.NoOfTicketsBought1.ToString()
            };
            Response response = new Response { Type = Response.Types.Type.SellTicketsResponse, Show = buyersNew};
            return response;
        }

        public static EmployeeAtOffice GetEmployee(Request request)
        {
            model.domains.EmployeeAtOffice employeeAtOffice =
                new model.domains.EmployeeAtOffice(request.Employee.Email, request.Employee.Password);
            return employeeAtOffice;
        }

        public static Response CreateOkResponse()
        {
            return new Response { Type = Response.Types.Type.Ok };
        }

        public static Response CreateErrorResponse(string eMessage)
        {
            return new Response { Type = Response.Types.Type.Error, Error = eMessage };
        }

        public static Buyer GetBuyer(Request request)
        {
            model.domains.Buyer buyer = new model.domains.Buyer(request.Show.BuyerName,
                int.Parse(request.Show.NoOfTickets), request.Show.ShowName);
            return buyer;
        }
    }
}