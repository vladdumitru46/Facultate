using System.Collections.Generic;
using tripModel;

namespace Proiect.repository
{
    public interface IGenericRepository<ID, E> where E : Entity<ID>
    {
        E Save(E entity);

        E Delete(ID idEntity);

        E Update(E entity);

        E FindOne(ID idEntity);

        IEnumerable<E> FindAll();
    }
}