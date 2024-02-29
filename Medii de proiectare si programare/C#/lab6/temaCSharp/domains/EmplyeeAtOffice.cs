using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaCSharp.domains
{
    internal class EmplyeeAtOffice : Entity<int>
    {
        public int id { get; set; }
        public String name { get; set; }
        public String email { get; set; }
        public String password { get; set; }

        public EmplyeeAtOffice(int id, string name, string email, string password)
        {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
        }
        public virtual string ToString()
        {
            return this.email;
        }

    }
}
