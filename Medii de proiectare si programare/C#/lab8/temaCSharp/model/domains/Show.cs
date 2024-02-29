using System;

namespace model.domains
{
    [Serializable]
    public class Show : Entity<string>
    {
        public Show(string showName, string artist, DateTime date, string placeOfShow, int noTicketsAvailable,
            int noTicketsSold)
        {
            ShowName = showName;
            Artist = artist;
            Date = date;
            PlaceOfShow = placeOfShow;
            NoTicketsAvailable = noTicketsAvailable;
            NoTicketsSold = noTicketsSold;
        }

        public string ShowName { get; set; }
        public string Artist { get; set; }

        public DateTime Date { get; set; }
        public string PlaceOfShow { get; set; }
        public int NoTicketsAvailable { get; set; }
        public int NoTicketsSold { get; set; }

        public override string ToString()
        {
            return ShowName;
        }
    }
}