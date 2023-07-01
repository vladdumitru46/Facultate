using System;

namespace tripModel
{
    [Serializable]
    public class LandMark<ID> : Entity<ID>
    {
        private string _name;

        private string _city;

        public LandMark(ID id, string name, string city) : base(id)
        {
            this._name = name;
            this._city = city;
        }

        public string Name
        {
            get => _name;
            set => _name = value;
        }

        public string City
        {
            get => _city;
            set => _city = value;
        }
    }
}