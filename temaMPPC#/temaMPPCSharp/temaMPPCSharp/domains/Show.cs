using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace temaMPPCSharp.domains
{
    internal class Show: Entity<String>
    {
        String numeSpectacol { get; set; }
        Artist artist { get; set; }
        public int noTicketsAvailabe { get; set; }
        public int noTicketsSold { get; set; }

        public DateOnly date { get; set; }
        public String placeOfShow { get; set; }

        public Show(String numeSpectacol,Artist artist, int noTicketsAvailabe, int noTicketsSold, DateOnly date, string placeOfShow)
        {
            this.numeSpectacol = numeSpectacol;
            this.artist = artist;
            this.noTicketsAvailabe = noTicketsAvailabe;
            this.noTicketsSold = noTicketsSold;
            this.date = date;
            this.placeOfShow = placeOfShow;
        }
    }
}
