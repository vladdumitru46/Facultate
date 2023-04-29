using System;
using model.domains;

namespace networking
{
    [Serializable]
    public class LogOutRequest : Request
    {
        public LogOutRequest(EmployeeAtOffice employeeAtOffice)
        {
            EmployeeAtOffice = employeeAtOffice;
        }

        public EmployeeAtOffice EmployeeAtOffice { get; }
    }
}