// using System;
// using System.Collections.Generic;
// using model.domains;
// using service;
//
// namespace client
// {
//     public class ClientController : IServiceObserver
//     {
//         private readonly IService _service;
//         private readonly IService _serviceNormal;
//         private EmployeeAtOffice _currentEmployee;
//
//         public ClientController(IService service, IService serviceNormal)
//         {
//             _service = service;
//             _serviceNormal = serviceNormal;
//             _currentEmployee = null;
//         }
//
//         public void TicketsSold(Buyer buyers)
//         {
//             var employeeEventArgs = new EmployeeEventArgs(EmployeeEvent.SellTickets, buyers);
//             OnEmployeeEvent(employeeEventArgs);
//         }
//
//         private void OnEmployeeEvent(EmployeeEventArgs employeeEventArgs)
//         {
//             if (UpdateEvent == null) return;
//             UpdateEvent(this, employeeEventArgs);
//         }
//
//         public void LogIn(string email, string password)
//         {
//             _service.LogIn(email, password, this);
//             var employeeAtOffice = _serviceNormal.FindEmployeeByEmailAndPassword(email, password);
//             _currentEmployee = employeeAtOffice;
//         }
//
//         public void LogOut()
//         {
//             _service.LogOut(_currentEmployee, this);
//             _currentEmployee = null;
//         }
//
//         public void SellTickets(string showName, string buyerName, int noTicketsAvailable)
//         {
//             var buyer = new Buyer(buyerName, noTicketsAvailable, showName);
//             var employeeEventArgs = new EmployeeEventArgs(EmployeeEvent.SellTickets, buyer);
//             OnEmployeeEvent(employeeEventArgs);
//             _service.SellTickets(showName, buyerName, noTicketsAvailable);
//         }
//
//         public void UpdateShow(Show show)
//         {
//             var employeeEventArgs = new EmployeeEventArgs(EmployeeEvent.UpdateShow, show);
//             OnEmployeeEvent(employeeEventArgs);
//             _serviceNormal.UpdateShow(show);
//         }
//
//         public IList<Show> GetAllShows()
//         {
//             IList<Show> list = new List<Show>();
//             var showList = _serviceNormal.GetAllShows();
//             foreach (var show in showList) list.Add(show);
//
//             return list;
//         }
//
//         public event EventHandler<EmployeeEventArgs> UpdateEvent;
//     }
// }

