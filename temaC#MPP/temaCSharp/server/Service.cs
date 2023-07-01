using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using model.domains;
using persistence.repositoryes;
using service;
using temaCSharp.repositoryes;
using temaCSharp.repositoryes.interfaces;

namespace server
{
    public class Service : IService
    {
        private readonly IDictionary<int, IServiceObserver> _loggedEmployees;
        private readonly IRepoBuyer _repoBuyer;
        private readonly IRepoEmployee _repoEmployee;
        private readonly RepoShow _repoShow;
        private IRepository<string, Artist> _repoArtist;

        public Service(IRepository<string, Artist> repoArtist, IRepoBuyer repoBuyer, IRepoEmployee repoEmployee,
            RepoShow repoShow)
        {
            _repoArtist = repoArtist;
            _repoBuyer = repoBuyer;
            _repoEmployee = repoEmployee;
            _repoShow = repoShow;
            _loggedEmployees = new Dictionary<int, IServiceObserver>();
        }

        public void LogOut(EmployeeAtOffice employeeAtOffice, IServiceObserver client)
        {
            var localClient = _loggedEmployees[employeeAtOffice.IdEmployee];
            if (localClient == null) throw new Exception("Employee " + employeeAtOffice.Name + " is not logged in!\n");

            _loggedEmployees.Remove(employeeAtOffice.IdEmployee);
        }

        public List<Show> SearchArtistByDate(DateTime date)
        {
            return (List<Show>)_repoShow.SearchArtist(date);
        }

        public List<Show> GetAllShows(EmployeeAtOffice employeeAtOffice)
        {
            return (List<Show>)_repoShow.FindAll();
        }

        public void SellTickets(string showName, string buyerName, int noOfTickets)
        {
            Console.WriteLine("clients connected: " + _loggedEmployees.Count);
            var show = _repoShow.findOne(showName);
            if (noOfTickets < show.NoTicketsAvailable)
            {
                foreach (var iServiceObserver in _loggedEmployees)
                {
                    Console.WriteLine("Sending to: " + iServiceObserver.Key + ' ' + iServiceObserver.Value + " data: " +
                                      showName + ' ' +
                                      noOfTickets);
                    Task.Run(() =>
                    {
                        iServiceObserver.Value.TicketsSold(new Buyer(buyerName, noOfTickets, showName));
                    });
                }

                _repoBuyer.SellTicketsToShow(showName, buyerName, noOfTickets);
            }
            else
            {
                throw new Exception("Show doesn't have this many tickets!\n");
            }
        }

        public Show findOneShow(string showName)
        {
            return _repoShow.findOne(showName);
        }

        public Show UpdateShow(Show show)
        {
            return _repoShow.Update(show);
        }

        public EmployeeAtOffice FindEmployeeByEmailAndPassword(string email, string password)
        {
            return _repoEmployee.findOneByEmailAndPassword(email, password);
        }

        public void LogIn(string email, string password, IServiceObserver client)
        {
            var employeeAtOffice = _repoEmployee.findOneByEmailAndPassword(email, password);
            if (employeeAtOffice != null)
            {
                if (_loggedEmployees.ContainsKey(employeeAtOffice.IdEmployee))
                    throw new Exception("Employee already logged in!");

                _loggedEmployees[employeeAtOffice.IdEmployee] = client;
            }
            else
            {
                throw new Exception("Email or password invalid!");
            }
        }
    }
}