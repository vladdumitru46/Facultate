using System;

namespace tripModel
{
    
    [Serializable]
    public class Person<ID> : Entity<ID>
    {
        private string _firstName;

        private string _lastName;

        private string _email;

        private string _password;

        private int _idTravelAgency;

        public Person(ID id, string email, string password) : base(id)
        {
            this._email = email;
            this._password = password;
        }
        
        public Person(ID id, string firstName, string lastName, string email, string password, int idTravelAgency) : base(id)
        {
            this._firstName = firstName;
            this._lastName = lastName;
            this._email = email;
            this._password = password;
            this._idTravelAgency = idTravelAgency;
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

        public string Email
        {
            get => _email;
            set => _email = value;
        }

        public string Password
        {
            get => _password;
            set => _password = value;
        }

        public int IdTravelAgency
        {
            get => _idTravelAgency;
            set => _idTravelAgency = value;
        }

        public override string ToString()
        {
            return _firstName + " " + _lastName + " " + _email + " " + _password;
        }
    }
}