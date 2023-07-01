using System;

namespace tripModel
{
    [Serializable]
    public class Collaboration<ID> : Entity<ID>
    {
        private TravelAgency<int> _travelAgency;

        private TransportationCompany<int> _transportationCompany;

        public Collaboration(ID id, TravelAgency<int> travelAgency, TransportationCompany<int> transportationCompany) : base(id)
        {
            this._travelAgency = travelAgency;
            this._transportationCompany = transportationCompany;
        }

        public TravelAgency<int> TravelAgency
        {
            get => _travelAgency;
            set => _travelAgency = value;
        }

        public TransportationCompany<int> TransportationCompany
        {
            get => _transportationCompany;
            set => _transportationCompany = value;
        }
    }
}