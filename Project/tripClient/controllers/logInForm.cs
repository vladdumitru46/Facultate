using System;
using System.Windows.Forms;
using Proiect.service;
using tripModel;

namespace Proiect
{
    public partial class logInForm : Form
    {
        private IServices _server;

        private Service _service;
        public logInForm(IServices server, Service service)
        {
            _server = server;
            _service = service;
            InitializeComponent();    
        }
                                  

        private void logInButton_Click(object sender, EventArgs e)
        {
            string email = emailTextBox.Text;
            string password = passwordTextBox.Text;

            Person<int> person = new Person<int>(_service.CheckPersonAccountExistence(email, password), email, password);
            
            try
            {
                tripsForm tripsForm = new tripsForm(_service, person, this);
                _server.Login(person, tripsForm);
                tripsForm.SetServer(_server);
                Hide();
                tripsForm.Closed += (s, args) => this.Close();
                tripsForm.Show();
                emailTextBox.Clear();
                passwordTextBox.Clear();
            }catch(TripException ex)
            {
                MessageBox.Show(ex.Message);
                //MessageBox.Show("Incorrect email or password");
            }
        }
    }
}