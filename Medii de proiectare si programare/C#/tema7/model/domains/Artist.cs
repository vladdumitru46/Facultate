namespace model.domains
{
    public class Artist : Entity<string>
    {
        public Artist(string name, string stageName, int age)
        {
            Name = name;
            StageName = stageName;
            Age = age;
        }

        public string Name { get; set; }
        public string StageName { get; set; }
        public int Age { get; set; }
    }
}