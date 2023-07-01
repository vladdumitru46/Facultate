using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaMPPCSharp.domains
{
    internal class Artist : Entity<String>
    {
        public String Name { get; set; }
        public String stageName { get; set; }
        public int age { get; set; }

        public Artist(string name, string stageName, int age)
        {
            Name = name;
            this.stageName = stageName;
            this.age = age;
        }
    }
}
