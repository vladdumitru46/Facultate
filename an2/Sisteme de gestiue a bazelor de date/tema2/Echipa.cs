using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace tema1
{
    internal class Echipa
    {
        public string nume_echipa { get; set; }
        public int an_infintare { get; set; }

        public Echipa(string nume_echipa, int an_infintare)
        {
            this.nume_echipa = nume_echipa;
            this.an_infintare = an_infintare;
        }

        public static explicit operator Echipa(DataGridViewSelectedRowCollection v)
        {
            throw new NotImplementedException();
        }
    }
}
