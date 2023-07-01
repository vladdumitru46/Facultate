namespace ConsoleApp1.domain;

public class Game: Entity<int>
{
    public String Team1 { get; set; }
    public String Team2 { get; set; }
    public String date { get; set; }

    public Game(int id, string team1, string team2, string date) : base(id)
    {
        Team1 = team1;
        Team2 = team2;
        this.date = date;
    }

    public override string ToString()
    {
        return Team1 + " vs " + Team2 + " on " + date + "\n";
    }
}