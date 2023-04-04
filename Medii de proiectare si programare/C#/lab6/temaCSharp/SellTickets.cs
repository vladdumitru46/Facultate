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
    public partial class SellTickets : Form
    {
        private string showName;
        Service service = new Service(new ArtistRepository(), new BuyerRepo(), new EmployeeRepo(), new ShowRepo());



        public void setShowName(string showName)
        {
            this.showName = showName;
        }
        public SellTickets()
        {
            InitializeComponent();
        }

        private void SellTickets_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void sell_Click(object sender, EventArgs e)
        {
            service.sellTickets(showName, nameTF.Text, int.Parse(nTTF.Text));
            Show show = service.findOneShow(showName);
            show.noTicketsAvailabe = show.noTicketsAvailabe - int.Parse(nTTF.Text);
            show.noTicketsSold = show.noTicketsSold + int.Parse(nTTF.Text);
            service.updateShow(show);
        }
    }
}
