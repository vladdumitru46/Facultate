using System;

namespace tripModel
{
    [Serializable]
    public class TravelAgency<ID> : Entity<ID>
    {
        private string _name;

        public TravelAgency(ID id, string name) : base(id)
        {
            this._name = name;
        }

        public string Name
        {
            get => _name;
            set => _name = value;
        }
        
    }
}