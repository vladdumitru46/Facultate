using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaMPPCSharp.domains
{
    internal class EmplyeeAtOffice : Entity<int>
    {
        public String name { get; set; }
        public String email { get; set; }
        public String password { get; set; }

        public EmplyeeAtOffice(string name, string email, string password)
        {
            this.name = name;
            this.email = email;
            this.password = password;
        }

    }
}
