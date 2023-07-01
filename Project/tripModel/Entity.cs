using System;

namespace tripModel
{
    [Serializable]
    public class Entity<ID>
    {
        private ID id;
        
        public Entity(ID id)
        {
            this.id = id;
        }

        public ID Id
        {
            get { return id; }

            set { id = value; }
        }

    }
}