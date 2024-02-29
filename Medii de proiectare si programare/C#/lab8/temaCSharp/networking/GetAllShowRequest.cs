using System;
using model.domains;

namespace networking
{
    [Serializable]
    public class GetAllShowRequest : Request
    {
        private EmployeeAtOffice _employeeAtOffice;

        public GetAllShowRequest(EmployeeAtOffice employeeAtOffice)
        {
            _employeeAtOffice = employeeAtOffice;
        }

        public EmployeeAtOffice EmployeeAtOffice
        {
            get => _employeeAtOffice;
            set => _employeeAtOffice = value;
        }
    }
}