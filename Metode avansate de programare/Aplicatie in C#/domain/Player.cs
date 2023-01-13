namespace ConsoleApp1.domain;

public class Player : Student
{
    public String Team { get; set; }

    public Player(int id, string name, string school, string team) : base(id, name, school)
    {
        Team = team;
    }

    public override string ToString()
    {
        return Id + " " + Name + " " + School + " " + Team + "\n";
    }
}