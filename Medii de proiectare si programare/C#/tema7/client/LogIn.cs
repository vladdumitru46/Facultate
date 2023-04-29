using System;
using System.Windows.Forms;
using server;
using service;

namespace client
{
    public partial class LogIn : Form
    {
        private readonly Service _controller;

        private readonly IService _server;
        private MainPage _mainPage;

        public LogIn(IService server, Service controller)
        {
            _server = server;
            _controller = controller;
            InitializeComponent();
        }

        public void setClient(MainPage mainPage)
        {
            _mainPage = mainPage;
        }

        private void label1_Click(object sender, EventArgs e)
        {
        }

        private void label3_Click(object sender, EventArgs e)
        {
        }

        private void log_in_Click(object sender, EventArgs e)
        {
            try
            {
                _server.LogIn(emailTF.Text, passwordTF.Text, _mainPage);
                var employee = _controller.FindEmployeeByEmailAndPassword(emailTF.Text, passwordTF.Text);
                _mainPage.Closed += (s, args) => Close();
                _mainPage.SetEmployee(employee);
                _mainPage.SetServer(_server);
                _mainPage.Text = employee.Name;
                _mainPage.Show();
                Hide();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void LogIn_Load(object sender, EventArgs e)
        {
        }
    }
}