using model.domains;
using temaCSharp.repositoryes.interfaces;

namespace persistence.repositoryes
{
    public interface IRepoEmployee : IRepository<int, EmployeeAtOffice>
    {
        bool Login(string email, string password);
        EmployeeAtOffice Logout();
        EmployeeAtOffice findOneByEmailAndPassword(string email, string password);
    }
}