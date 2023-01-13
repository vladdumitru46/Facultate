using ConsoleApp1.domain;

namespace ConsoleApp1.repository;
public delegate E ParseLineEntity<E>(string line);

public class FileRepository<ID, E> : IRepository<ID, E> where E: Entity<ID>
{

    protected string FileName;
    protected ParseLineEntity<E> ParseLineEntity;
    private IDictionary<ID, E> dictionary = new Dictionary<ID, E>();

    public FileRepository(string fileName, ParseLineEntity<E> parseLineEntity)
    {
        FileName = fileName;
        ParseLineEntity = parseLineEntity;
        if (ParseLineEntity != null)
        {
            LoadFromFile();
        }
    }

    public void LoadFromFile()
    {
        using (var fileStream = File.OpenRead(this.FileName))
        using (var streamReader = new StreamReader(fileStream))
        {
            String line;
            while ((line = streamReader.ReadLine()) != null)
            {
                E entitate = this.ParseLineEntity(line);
                save(entitate);
            }
        }
    }

  /*  public void WriteToFile()
    {
        StreamWriter writer = new StreamWriter(FileName, true);

        foreach (var entity in dictionary)
        {
            writer.Write(entity);
        }
        writer.Close();
    }*/

    public E findOne(ID id)
    {
        foreach (var entity in dictionary)
        {
            if (entity.Key.Equals(id))
            {
                return entity.Value;
            }    
        }

        return null;
    }

    public IEnumerable<E> findAll()
    {
        List<E> list = new List<E>();
        foreach (var entity in dictionary)
        {
            list.Add(entity.Value);
        }
        return list;
    }

    public E save(E e)
    {
        if (dictionary.ContainsKey(e.Id))
        {
            return e;
        }
        dictionary.Add(e.Id, e);
        //WriteToFile();
        return e;
    }

    public E delete(E e)
    {
        if (dictionary.ContainsKey(e.Id))
        {
            dictionary.Remove(e.Id);
        }
        //WriteToFile();
        return e;
    }

    
}