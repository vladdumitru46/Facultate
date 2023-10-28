using System;

namespace client
{
    public enum EmployeeEvent
    {
        SellTickets,
        UpdateShow
    }

    public class EmployeeEventArgs : EventArgs
    {
        public EmployeeEventArgs(EmployeeEvent employeeEvent, object data)
        {
            EmployeeEvent = employeeEvent;
            Data = data;
        }

        public object Data { get; }

        public EmployeeEvent EmployeeEvent { get; }
    }
}