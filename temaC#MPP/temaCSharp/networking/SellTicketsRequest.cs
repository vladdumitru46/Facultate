using System;
using model.domains;

namespace networking
{
    [Serializable]
    public class SellTicketsRequest : Request
    {
        public SellTicketsRequest(Buyer buyer)
        {
            Buyer = buyer;
        }

        public Buyer Buyer { get; set; }
    }
}