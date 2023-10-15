using System;
using System.Collections.Generic;
using System.Windows.Forms;
using model.domains;
using server;
using service;

namespace client
{
    public partial class MainPage : Form, IServiceObserver
    {
        private readonly LogIn _logIn;

        private readonly Service _service;
        private IService _controller;

        private EmployeeAtOffice _employee;

        private int _index = -1;

        private IList<Show> _tableData;

        public MainPage(Service controller, LogIn logIn)
        {
            _service = controller;
            _logIn = logIn;
            InitializeComponent();
        }

        public void TicketsSold(Buyer buyers)
        {
            dataGridView1.BeginInvoke((Action)delegate
            {
                _tableData = _controller.GetAllShows(_employee);
                dataGridView1.DataSource = _tableData;
                if (_index != -1)
                {
                    dataGridView1.Rows[dataGridView1.Rows.Count - 1].Selected = true;
                    _index = -1;
                }
            });
        }

        public void SetServer(IService controller)
        {
            _controller = controller;
            InitializeGui();
        }

        private void InitializeGui()
        {
            FillTable();
        }

        private void FillTable()
        {
            _tableData = _controller.GetAllShows(_employee);
            dataGridView1.DataSource = _tableData;
        }
        


        private void Form2_Load(object sender, EventArgs e)
        {
        }


        private void MainPage_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (e.CloseReason == CloseReason.UserClosing)
            {
                _controller.LogOut(_employee, this);
                Application.Exit();
            }
        }

        public void SetEmployee(EmployeeAtOffice employee)
        {
            _employee = employee;
        }


        private void sell_Click_1(object sender, EventArgs e)
        {
            var dataGridViewRow = dataGridView1.SelectedRows[0];
            var showName = dataGridViewRow.Cells[0].Value.ToString();
            var show = _service.findOneShow(showName);
            try
            {
                _index = -1;
                show.NoTicketsAvailable -= int.Parse(noTTF.Text);
                show.NoTicketsSold += int.Parse(noTTF.Text);
                _service.UpdateShow(show);
                _controller.SellTickets(showName, nameTF.Text, int.Parse(noTTF.Text));
                MessageBox.Show(showName + '\n' + nameTF.Text + '\n' + noTTF.Text);
                sellPage.Enabled = false;
                tabControl1.SelectTab(main_page);
            }
            catch (Exception exception)
            {
                MessageBox.Show(exception.Message);
            }
        }


        private void search_Click(object sender, EventArgs e)
        {
            _tableData = _service.SearchArtistByDate(dateTimePicker.Value.Date);
            if (_tableData.Count > 0)
            {
                dataGridView1.DataSource = _tableData;
            }
            else
            {
                _tableData = _controller.GetAllShows(_employee);
                dataGridView1.DataSource = _tableData;
                MessageBox.Show(@"No show in that date!");
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            _controller.LogOut(_employee, this);
            _logIn.Closed += (s, args) => Close();
            _logIn.Show();
            Hide();
        }
        
        private void TabPaneSelected(object sender, TabControlEventArgs e)
        {
            if (e.TabPage == main_page) sellPage.Enabled = false;
        }

        private void SelectionChanged(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count == 1)
            {
                _index = dataGridView1.SelectedRows[0].Index;
                sellPage.Enabled = true;
                tabControl1.SelectTab(sellPage);
            }
        }
    }
}