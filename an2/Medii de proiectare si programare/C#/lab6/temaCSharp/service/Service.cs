using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using temaCSharp.domains;
using temaCSharp.repositoryes;

namespace temaCSharp.service
{
    internal class Service
    {
        private IRepository<String, Artist> repoArtist;
        private RepoBuyer repoBuyer;
        private RepoEmployee repoEmployee;
        private RepoShow repoShow;

        public Service(IRepository<string, Artist> repoArtist, RepoBuyer repoBuyer, RepoEmployee repoEmployee, RepoShow repoShow)
        {
            this.repoArtist = repoArtist;
            this.repoBuyer = repoBuyer;
            this.repoEmployee = repoEmployee;
            this.repoShow = repoShow;
        }

        public bool logInEmployee(String email, String password)
        {
            return repoEmployee.login(email, password);
        }

        public List<Show> searchArtistByDate(DateTime date)
        {
            return (List<Show>)repoShow.searchArtist(date);
        }

        public List<Show> getAllShows()
        {
            return (List<Show>)repoShow.findAll();
        }

        public void sellTickets(String showName, String buyerName, int noOfTicktes)
        {
            repoBuyer.sellTicketsToShow(showName, buyerName, noOfTicktes);
        }

        public Show findOneShow(String showName)
        {
            return repoShow.findOne(showName);
        }

        public Show updateShow(Show show)
        {
            return repoShow.update(show);
        }

        public EmplyeeAtOffice findEmployeeByEmailAndPAssword(String email, String password)
        {
            return repoEmployee.findOneByEmailAndPassword(email, password);
        }
    }
}
