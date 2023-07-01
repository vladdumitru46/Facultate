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
    public partial class MainPage : Form
    {
        DataSet dataSet = new DataSet();
        BindingSource bindingSource = new BindingSource();
        Service service = new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());

        public MainPage()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'musicFestivalDataSet.shows' table. You can move, or remove it, as needed.
            this.showsTableAdapter.Fill(this.musicFestivalDataSet.shows);

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void search_artist_Click(object sender, EventArgs e)
        {
            SearchArtist searchArtist = new SearchArtist();
            searchArtist.Show();
        }

        private void sell_tickets_Click(object sender, EventArgs e)
        {
            DataGridViewRow dataGridViewRow = dataGridView1.SelectedRows[0];
            string showName = dataGridViewRow.Cells[0].Value.ToString();
            SellTickets sellTickets = new SellTickets();
            sellTickets.setShowName(showName);
            sellTickets.Show();
        }
    }
}
