namespace ConsoleApp1.domain;

public class Entity<TID>
{
    public TID Id { get; set; }

    public Entity(TID id)
    {
        Id = id;
    }
}