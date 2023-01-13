using ConsoleApp1.domain;

namespace ConsoleApp1.repository;

public interface IRepository<ID, E> where E : Entity<ID>
{
    E findOne(ID id);

    IEnumerable<E> findAll();

    E save(E e);

    E delete(E e);

}