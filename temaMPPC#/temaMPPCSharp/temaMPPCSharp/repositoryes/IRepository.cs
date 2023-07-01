using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaMPPCSharp.domains;

namespace temaMPPCSharp.repositoryes
{
    internal interface IRepository<ID, E> where E: Entity<ID>
    {

        E findOne(ID id);
        IEnumerable<E> findAll();

        E save(E entity);

        E delete(ID id);

        E update(E entity);
        




        void sellTickets(String artistName, String buyerName, int noOfTickets);

 
    }
}
