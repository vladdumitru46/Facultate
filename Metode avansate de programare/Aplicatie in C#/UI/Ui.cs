using ConsoleApp1.domain;
using ConsoleApp1.service;

namespace ConsoleApp1.UI;

public class Ui
{
    private Service service;

    public Ui(Service service)
    {
        this.service = service;
    }

    private static void menu()
    {
        Console.WriteLine("1.All players from a team");
        Console.WriteLine("2.All active players from a game");
        Console.WriteLine("3.All games from a period");
        Console.WriteLine("4.Score from a game");
        Console.WriteLine("Write exit for exit");
    }

    public void run()
    {
        string cmd = "";

        while (true)
        {
            menu();
            Console.WriteLine(">>>");
            cmd = Console.ReadLine();
            if (cmd.Equals("1"))
            {
                Console.WriteLine("team:");
                string team = Console.ReadLine();
                List<Player> players = service.FindPlayersFromATeam(team);
                foreach (var p in players)
                {
                    Console.WriteLine(p);
                }
            }
            else if (cmd.Equals("2"))
            {
                Console.WriteLine("game id: ");
                string id = Console.ReadLine();
                int idgame = int.Parse(id);

                List<ActivePlayer> activePlayers = service.AllActivePlayersFromAGame(idgame);
                foreach (var a in activePlayers)
                {
                    Console.WriteLine(a);
                }
            }
            else if (cmd.Equals("3"))
            {
                Console.WriteLine("date start(dd/mm/yyyy): ");
                string dateStart = Console.ReadLine();
                Console.WriteLine("date finish(dd/mm/yyyy): ");
                string dateFinish = Console.ReadLine();
                List<Game> games = service.GamesFromAPeriodOfTime(dateStart, dateFinish);
                foreach (var g in games)
                {
                    Console.WriteLine(g);
                }
            }
            else if (cmd.Equals("4"))
            {
                Console.WriteLine("game id");
                string id = Console.ReadLine();
                int idgame = int.Parse(id);
                Console.WriteLine(service.PointsInAGame(idgame));
            }
            else if (cmd.Equals("exit"))
            {
                return;
            }
        }
    }
}