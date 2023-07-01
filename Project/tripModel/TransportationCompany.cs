using System;
using System.Collections.Generic;

namespace tripModel
{
    [Serializable]
    public class TransportationCompany<ID> : Entity<ID>
    {
        private string _name;

        public TransportationCompany(ID id, string name) : base(id)
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