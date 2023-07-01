using System;

namespace model.domains
{
    [Serializable]
    public class Entity<TID>
    {
        public TID Id { get; set; }
    }
}