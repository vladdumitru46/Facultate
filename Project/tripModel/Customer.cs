using System;

namespace tripModel
{
    [Serializable]
    public class Customer<ID> : Entity<ID>
    {
        private string _firstName;

        private string _lastName;

        private string _phoneNumber;

        private int _numberOfTickets;

        public Customer(ID id, string firstName, string lastName, string phoneNumber, int numberOfTickets) : base(id)
        {
            this._firstName = firstName;
            this._lastName = lastName;
            this._phoneNumber = phoneNumber;
            this._numberOfTickets = numberOfTickets;
        }

        public string FirstName
        {
            get => _firstName;
            set => _firstName = value;
        }

        public string LastName
        {
            get => _lastName;
            set => _lastName = value;
        }

        public string PhoneNumber
        {
            get => _phoneNumber;
            set => _phoneNumber = value;
        }

        public int NumberOfTickets
        {
            get => _numberOfTickets;
            set => _numberOfTickets = value;
        }

        public override string ToString()
        {
            return _firstName + " " + _lastName + " -> " + _phoneNumber;
        }
    }
}