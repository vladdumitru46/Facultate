using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace temaCSharp.domains
{
    internal class Show: Entity<String>
    {
        public String ShowName { get; set; }
        public String Artist { get; set; }
        public int noTicketsAvailabe { get; set; }
        public int noTicketsSold { get; set; }

        public DateTime date { get; set; }
        public String placeOfShow { get; set; }

        public Show(string showName, string artist, int noTicketsAvailabe, int noTicketsSold, DateTime date, string placeOfShow)
        {
            this.ShowName = showName;
            this.Artist = artist;
            this.noTicketsAvailabe = noTicketsAvailabe;
            this.noTicketsSold = noTicketsSold;
            this.date = date;
            this.placeOfShow = placeOfShow;
        }

        public string ToString()
        {
            return ShowName;
        }
    }
}
