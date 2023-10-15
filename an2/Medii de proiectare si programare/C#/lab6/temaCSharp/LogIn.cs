using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using temaCSharp.repositoryes;
using temaCSharp.service;
using temaCSharp.domains;

namespace temaCSharp
{
    public partial class LogIn : Form
    {
        Service service = new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());
        public LogIn()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        [STAThread]
        private void log_in_Click(object sender, EventArgs e)
        {
            if (service.logInEmployee(emailTF.Text, passwordTF.Text))
            {

                EmplyeeAtOffice employee = service.findEmployeeByEmailAndPAssword(emailTF.Text, passwordTF.Text);
                MainPage mainPage = new MainPage();
                mainPage.Show();
            }
        }

        private void LogIn_Load(object sender, EventArgs e)
        {

        }
    }
}
