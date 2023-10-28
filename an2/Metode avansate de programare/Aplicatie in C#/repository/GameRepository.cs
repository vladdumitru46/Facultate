using ConsoleApp1.domain;

namespace ConsoleApp1.repository;

public class GameRepository: FileRepository<int, Game>
{
    public GameRepository(string fileName) : base(fileName, DelegateEntitiesFromFile.DelegateGame)
    {
    }

    public List<Game> GamesFromAPeriodOfTime(string dateStart, string dateFinish)
    {
        List<Game> list = new List<Game>();

        string yearStart = "";
        string monthStart = "";
        string dayStart = "";
        string yearFinish = "";
        string monthFinish = "";
        string dayFinish = "";
        
        dayStart+=dateStart[0];
        dayStart+=dateStart[1];
        monthStart+=dateStart[3];
        monthStart+=dateStart[4];
        yearStart+=dateStart[6];
        yearStart+=dateStart[7];
        yearStart+=dateStart[8];
        yearStart+=dateStart[9];

        

        for (int i = 0; i < dateFinish.Length; i++)
        {
            if (i < 2)
            {
                dayFinish+=dateFinish[i];
            }

            else if (i > 2 && i <= 4)
            {
                monthFinish+=dateFinish[i];
            }
            else
            {
                if (dateFinish[i] != '/' && i>4)
                {
                    yearFinish+=dateFinish[i];
                }
            }
        }

        foreach (var game in base.findAll())
        {
            string ys = "";
            string ms = "";
            string ds = "";
            for (int i = 0; i < game.date.Length; i++)
            {
                if (i < 2)
                {
                    ds+=game.date[i];
                }

                else if (i > 2 && i <= 4)
                {
                    ms+=game.date[i];
                }
                else
                {
                    if (game.date[i] != '/' && i>4)
                    {
                        ys+=game.date[i];
                    }
                }
            }
            
            if (int.Parse(yearStart) <= int.Parse(ys) && int.Parse(ys) <= int.Parse(yearFinish))
            {
                if (int.Parse(monthStart) <= int.Parse(ms) && int.Parse(ms) <= int.Parse(monthFinish))
                {
                    if (int.Parse(dayStart) <= int.Parse(ds) && int.Parse(ds) <= int.Parse(dayFinish))
                    {
                        list.Add(game);
                    }
                }
            }
        }
        return list;
    }
}