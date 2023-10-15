using ConsoleApp1.domain;
using ConsoleApp1.repository;

namespace ConsoleApp1.service;

public class Service
{
    private ActivePlayerRepository activePlayerRepository;
    private PlayerRepository PlayerRepository;
    private GameRepository GameRepository;

    public Service(ActivePlayerRepository activePlayerRepository, PlayerRepository playerRepository, GameRepository gameRepository)
    {
        this.activePlayerRepository = activePlayerRepository;
        PlayerRepository = playerRepository;
        GameRepository = gameRepository;
    }

    public List<Player> FindPlayersFromATeam(string teamName)
    {
        return PlayerRepository.FindPlayersFromATeam(teamName);
    }

    public List<Game> GamesFromAPeriodOfTime(string dateStart, string dateFinish)
    {
        return GameRepository.GamesFromAPeriodOfTime(dateStart, dateFinish);
    }

    public List<ActivePlayer> AllActivePlayersFromAGame(int idGame)
    {
        return activePlayerRepository.AllActivePlayersFromAGame(idGame);
    }

    public String PointsInAGame(int IdGame)
    {
        return activePlayerRepository.PointsInAGame(IdGame);
    }
}