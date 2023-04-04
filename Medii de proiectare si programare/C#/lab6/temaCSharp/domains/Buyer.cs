using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaCSharp.domains
{
    internal class Buyer: Entity<String>
    {
        String buyerName { get; set; }
        int noOfTicketsBought { get; set; }

        String showName { get; set; }

        public Buyer(string buyerName, int noOfTicketsBought, string showName)
        {
            this.buyerName = buyerName;
            this.noOfTicketsBought = noOfTicketsBought;
            this.showName = showName;
        }
    }
}
