namespace ConsoleApp1.domain;

public class Team: Entity<int>
{
    public String Name { get; set; }

    public Team(int id, string name) : base(id)
    {
        Name = name;
    }

    public override string ToString()
    {
        return Id + " " + Name + "\n";
    }
}