using System;

namespace tripModel
{
    [Serializable]
    public class Booking<ID> : Entity<ID>
    {
        private Person<int> _person;

        private Customer<int> _customer;

        private Trip<int> _trip;

        public Booking(ID id, Person<int> person, Customer<int> customer, Trip<int> trip) : base(id)
        {
            this._person = person;
            this._customer = customer;
            this._trip = trip;
        }

        public Person<int> Person
        {
            get => _person;
            set => _person = value;
        }

        public Customer<int> Customer
        {
            get => _customer;
            set => _customer = value;
        }

        public Trip<int> Trip
        {
            get => _trip;
            set => _trip = value;
        }
    }
}