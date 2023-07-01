using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaMPPCSharp.domains;

namespace temaMPPCSharp.repositoryes
{
    internal interface RepoEmployee
    {
        EmplyeeAtOffice login(String email, String password);

        EmplyeeAtOffice logout();
        EmplyeeAtOffice findOneByEmailAndPassword(String email, String password);
    }
}
