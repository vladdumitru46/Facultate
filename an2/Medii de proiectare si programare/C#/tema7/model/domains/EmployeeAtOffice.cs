using System;

namespace model.domains
{
    [Serializable]
    public class EmployeeAtOffice : Entity<int>
    {
        public EmployeeAtOffice(int idEmployee, string name, string email, string password)
        {
            IdEmployee = idEmployee;
            Name = name;
            Email = email;
            Password = password;
        }

        public int IdEmployee { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }

        public override string ToString()
        {
            return Email;
        }
    }
}