using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaCSharp.domains;

namespace temaCSharp.repositoryes
{
    internal interface RepoEmployee: IRepository<int, EmplyeeAtOffice>
    {
        bool login(String email, String password);

        EmplyeeAtOffice logout();
        EmplyeeAtOffice findOneByEmailAndPassword(String email, String password);
    }
}
