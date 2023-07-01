// See https://aka.ms/new-console-template for more information


using ConsoleApp1.repository;
using ConsoleApp1.service;
using ConsoleApp1.UI;

public class Program
{
    public static void Main(string[] args)
    {
        string filename = "C:/Users/vladb/RiderProjects/lab12/ConsoleApp1/data/activePlayers.txt";
        ActivePlayerRepository activePlayerRepository =
            new ActivePlayerRepository(filename);

        PlayerRepository playerRepository =
            new PlayerRepository("C:/Users/vladb/RiderProjects/lab12/ConsoleApp1/data/player.txt");

        GameRepository gameRepository =
            new GameRepository("C:/Users/vladb/RiderProjects/lab12/ConsoleApp1/data/game.txt");
        Service service = new Service(activePlayerRepository, playerRepository, gameRepository);
        Ui ui = new Ui(service);
        foreach (var v in activePlayerRepository.findAll())
        {
            if (playerRepository.findOne(v.idPlayer).Team.Equals(gameRepository.findOne(25).Team1))
            {
                Console.WriteLine("HGDFYGASFDUF");
            }
        }
        ui.run();
       // Random rnd = new Random();

        
    }
}