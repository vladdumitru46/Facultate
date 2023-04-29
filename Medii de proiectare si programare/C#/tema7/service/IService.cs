using System;
using System.Collections.Generic;
using model.domains;

namespace service
{
    public interface IService
    {
        void LogIn(string email, string password, IServiceObserver client);
        void LogOut(EmployeeAtOffice employeeAtOffice, IServiceObserver client);
        List<Show> SearchArtistByDate(DateTime date);
        List<Show> GetAllShows(EmployeeAtOffice employeeAtOffice);
        void SellTickets(string showName, string buyerName, int noOfTickets);
        Show findOneShow(string showName);
        Show UpdateShow(Show show);
        EmployeeAtOffice FindEmployeeByEmailAndPassword(string email, string password);
    }
}