using System;
using model.domains;

namespace networking
{
    [Serializable]
    public class LogInRequest : Request
    {
        public LogInRequest(EmployeeAtOffice employeeAtOffice)
        {
            _employeeAtOffice = employeeAtOffice;
        }

        public EmployeeAtOffice _employeeAtOffice { get; }
    }
}