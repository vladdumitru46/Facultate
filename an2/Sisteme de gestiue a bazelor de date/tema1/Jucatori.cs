using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace tema1
{
    internal class Jucatori
    {
        public string nume_jucator { get; set; }
        public int varsta { get; set; }
        public string nume_echipa { get; set; }

        public Jucatori(string nume_jucator, int varsta, string nume_echipa)
        {
            this.nume_jucator = nume_jucator;
            this.varsta = varsta;
            this.nume_echipa = nume_echipa;
        }
    }
}
