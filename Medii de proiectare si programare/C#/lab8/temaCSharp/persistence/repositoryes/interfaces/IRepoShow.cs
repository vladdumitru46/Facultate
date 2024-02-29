using System;
using System.Collections.Generic;
using model.domains;
using temaCSharp.repositoryes.interfaces;

namespace temaCSharp.repositoryes
{
    public interface RepoShow : IRepository<string, Show>
    {
        IEnumerable<Show> SearchArtist(DateTime date);
    }
}