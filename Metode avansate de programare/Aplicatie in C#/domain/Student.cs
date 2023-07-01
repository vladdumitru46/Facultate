namespace ConsoleApp1.domain;

public class Student: Entity<int>
{
    public String Name { get; set; }
    public String School { get; set; }

    public Student(int id, string name, string school) : base(id)
    {
        Name = name;
        School = school;
    }

    public override string ToString()
    {
        return Id + " " + Name + " " + School + "\n";
    }
}