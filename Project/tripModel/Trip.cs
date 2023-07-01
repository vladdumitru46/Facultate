using System;

namespace tripModel
{
    [Serializable]
    public class Trip<ID> : Entity<ID>
    {
        private int _idLandMark;

        private int _idTransportationCompany;

        private DateTime _departureTime;

        private double _price;

        private int _numberOfSeatsAvailable;

        public Trip(ID id, int idLandMark, int idTransportationCompany, DateTime departureTime, double price,
            int numberOfSeatsAvailable) : base(id)
        {
            this._idLandMark = idLandMark;
            this._idTransportationCompany = idTransportationCompany;
            this._departureTime = departureTime;
            this._price = price;
            this._numberOfSeatsAvailable = numberOfSeatsAvailable;
        }

        public int IdLandMark
        {
            get => _idLandMark;
            set => _idLandMark = value;
        }

        public int IdTransportationCompany
        {
            get => _idTransportationCompany;
            set => _idTransportationCompany = value;
        }

        public DateTime DepartureTime
        {
            get => _departureTime;
            set => _departureTime = value;
        }

        public double Price
        {
            get => _price;
            set => _price = value;
        }

        public int NumberOfSeatsAvailable
        {
            get => _numberOfSeatsAvailable;
            set => _numberOfSeatsAvailable = value;
        }
    }
}