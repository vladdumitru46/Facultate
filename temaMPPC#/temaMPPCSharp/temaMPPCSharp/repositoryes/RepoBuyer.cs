using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaMPPCSharp.repositoryes
{
    internal interface RepoBuyer
    {
        void sellTicketsToShow(String showName, String buyerName, int noOfTickets);
    }
}
