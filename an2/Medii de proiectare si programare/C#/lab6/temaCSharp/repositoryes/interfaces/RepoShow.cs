using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaCSharp.domains;

namespace temaCSharp.repositoryes
{
    internal interface RepoShow: IRepository<String, Show>
    {
        IEnumerable<Show> searchArtist(DateTime date);
    }
}
