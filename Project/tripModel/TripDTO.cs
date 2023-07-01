using System;

namespace tripModel
{
    [Serializable]
    public class TripDTO<ID> : Entity<ID>
    {
        private string _nameLandMark;

        private string _nameTransportationCompany;

        private DateTime _departureTime;

        private double _price;

        private int _numberOfSeatsAvailable;

        public TripDTO(ID id, string nameLandMark, string nameTransportationCompany, DateTime departureTime, double price,
            int numberOfSeatsAvailable) : base(id)
        {
            this._nameLandMark = nameLandMark;
            this._nameTransportationCompany = nameTransportationCompany;
            this._departureTime = departureTime;
            this._price = price;
            this._numberOfSeatsAvailable = numberOfSeatsAvailable;
        }

        public string NameLandMark
        {
            get => _nameLandMark;
            set => _nameLandMark = value;
        }

        public string NameTransportationCompany
        {
            get => _nameTransportationCompany;
            set => _nameTransportationCompany = value;
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

        public override string ToString()
        {
            return base.ToString() + " " + _nameTransportationCompany + " " + _nameLandMark;
        }
    }
}