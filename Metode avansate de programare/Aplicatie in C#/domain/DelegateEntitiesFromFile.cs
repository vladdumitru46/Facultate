namespace ConsoleApp1.domain;

public class DelegateEntitiesFromFile
{
    private static char separator = ';';

    public static Game DelegateGame(string line)
    {
        string[] splitGAme = line.Split(separator);
        Game game = new Game(int.Parse(splitGAme[0]), splitGAme[1], splitGAme[2], splitGAme[3]);
        return game;
    }

    public static Player DelegatePlayer(string line)
    {
        string[] splitPlayer = line.Split(separator);
        Player player = new Player(int.Parse(splitPlayer[0]), splitPlayer[1], splitPlayer[2], splitPlayer[3]);
        return player;
    }

    public static ActivePlayer DelegateActivePlayer(string line)
    {
        string[] splitActivePlayer = line.Split(separator);
        ActivePlayer activePlayer = new ActivePlayer(int.Parse(splitActivePlayer[0]), int.Parse(splitActivePlayer[1]),
            int.Parse(splitActivePlayer[2]), int.Parse(splitActivePlayer[3]), splitActivePlayer[4]);
        return activePlayer;
    }
}