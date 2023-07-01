using System;
using model.domains;

namespace networking
{
    [Serializable]
    public class NewSellTicketsResponse : UpdateResponse

    {
        private Buyer _buyer;

        public NewSellTicketsResponse(Buyer buyer)
        {
            _buyer = buyer;
        }

        public Buyer Buyer
        {
            get => _buyer;
            set => _buyer = value;
        }
    }
}