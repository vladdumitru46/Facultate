using System.Collections.Generic;

namespace temaCSharp.repositoryes.interfaces
{
    public interface IRepository<TId, TE>
    {
        TE findOne(TId id);
        IEnumerable<TE> FindAll();

        TE Save(TE entity);

        TE Delete(TId id);

        TE Update(TE entity);
    }
}