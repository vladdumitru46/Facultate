using System;

namespace model.domains
{
    [Serializable]
    public class Buyer : Entity<string>
    {
        public Buyer(string buyerName, int noOfTicketsBought, string showName)
        {
            BuyerName1 = buyerName;
            NoOfTicketsBought1 = noOfTicketsBought;
            ShowName1 = showName;
        }

        public string BuyerName1 { get; set; }

        public int NoOfTicketsBought1 { get; set; }

        public string ShowName1 { get; set; }
    }
}