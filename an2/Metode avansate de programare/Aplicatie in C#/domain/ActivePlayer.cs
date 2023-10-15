namespace ConsoleApp1.domain;

public class ActivePlayer: Entity<int>
{
    public int idPlayer { get; set; }
    public int idGame { get; set; }
    public int numberOfPoints { get; set; }
    public String type { get; set; }

    public ActivePlayer(int id, int idPlayer, int idGame, int numberOfPoints, string type) : base(id)
    {
        this.idPlayer = idPlayer;
        this.idGame = idGame;
        this.numberOfPoints = numberOfPoints;
        this.type = type;
    }

    public override string ToString()
    {
        return idPlayer + " " + idGame + " " + numberOfPoints + " " + type + "\n";
    }
}