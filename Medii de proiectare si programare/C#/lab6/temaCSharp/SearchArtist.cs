using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using temaCSharp.domains;
using temaCSharp.repositoryes;
using temaCSharp.service;

namespace temaCSharp
{
    public partial class SearchArtist : Form
    {
        DataSet dataSet = new DataSet();
        BindingSource bindingSource = new BindingSource();

        Service service = new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());

        public SearchArtist()
        {
            InitializeComponent();
        }

        private void sell_Click(object sender, EventArgs e)
        {

            DataGridViewRow dataGridViewRow = dataGridView1.SelectedRows[0];
            string showName = dataGridViewRow.Cells[0].Value.ToString();
            SellTickets sellTickets = new SellTickets();
            sellTickets.setShowName(showName);
            sellTickets.Show();
        }

        private void search_Click(object sender, EventArgs e)
        {
            DataTable dt = new DataTable();
            dt.Columns.Add("showName", typeof(string));
            dt.Columns.Add("artistName", typeof(string));
            dt.Columns.Add("date", typeof(DateTime));
            dt.Columns.Add("place of show", typeof(string));
            dt.Columns.Add("noOfTicketsAvailabale", typeof(int));
            dt.Columns.Add("noOfTicketsSold", typeof(int));
            List<Show> list = service.searchArtistByDate(dateTimePicker.Value.Date);
            Console.WriteLine(list.Count);
            for(int i = 0; i < list.Count; i++)
            {
                dt.Rows.Add(list[i].ShowName, list[i].Artist, list[i].date, list[i].placeOfShow, list[i].noTicketsAvailabe, list[i].noTicketsSold);
            }
            dataGridView1.DataSource = dt;
        }
    }
}
