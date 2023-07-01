using ConsoleApp1.domain;

namespace ConsoleApp1.repository;

public class PlayerRepository : FileRepository<int, Player>
{
    public PlayerRepository(string fileName) : base(fileName, DelegateEntitiesFromFile.DelegatePlayer)
    {
    }
    
    public List<Player> FindPlayersFromATeam(string teamName)
    {
        List<Player> list = new List<Player>();
        foreach (var player in findAll())
        {
            if (player.Team == teamName)
            {
                list.Add(player);
            }
        }

        return list;
    }
}