using tripModel;

namespace Proiect.repository
{
    public interface IPersonRepository : IGenericRepository<int, Person<int>>
    {
        bool CheckEmailExistenceInPersons(string email);
        int CheckPersonAccountExistence(string email, string password);
        Person<int> FindPersonByEmail(string emailAddress);
        
    }
}