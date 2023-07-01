using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaMPPCSharp.domains;

namespace temaMPPCSharp.repositoryes
{
    internal interface RepoShow
    {
        IEnumerable<Artist> searchArtist(DateOnly date);
    }
}
