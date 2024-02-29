using ConsoleApp1.domain;

namespace ConsoleApp1.repository;

public class ActivePlayerRepository : FileRepository<int, ActivePlayer>
{
    public ActivePlayerRepository(string fileName) : base(fileName, DelegateEntitiesFromFile.DelegateActivePlayer)
    {
    }

    public List<ActivePlayer> AllActivePlayersFromAGame(int idGame)
    {
        List<ActivePlayer> list = new List<ActivePlayer>();
        foreach (var player in base.findAll())
        {
            if (player.idGame == idGame)
            {
                list.Add(player);
            }
        }

        return list;
    }

    public String PointsInAGame(int IdGame)
    {
        PlayerRepository playerRepository = new PlayerRepository("C:/Users/vladb/RiderProjects/lab12/ConsoleApp1/data/player.txt");
        GameRepository gameRepository = new GameRepository("C:/Users/vladb/RiderProjects/lab12/ConsoleApp1/data/game.txt");
        int pointsT1 = 0;
        int pointsT2 = 0;
        List<ActivePlayer> list = AllActivePlayersFromAGame(IdGame);
        foreach (var player in list)
        {
            if (playerRepository.findOne(player.idPlayer).Team.Equals(gameRepository.findOne(IdGame).Team1))
            {
                pointsT1 += player.numberOfPoints;
            }
            if (playerRepository.findOne(player.idPlayer).Team.Equals(gameRepository.findOne(IdGame).Team2))
            {
                pointsT2 += player.numberOfPoints;
            }
        }

        return gameRepository.findOne(IdGame).Team1 + " " + pointsT1 + " vs " + pointsT2 + " " +
               gameRepository.findOne(IdGame).Team2 + "\n";
    }
}