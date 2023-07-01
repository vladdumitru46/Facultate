using System;
using System.Collections.Generic;
using System.Data;
using System.Globalization;
using System.Linq;
using System.Windows.Forms;
using Proiect.service;
using tripModel;

namespace Proiect
{
    public partial class tripsForm : Form, IObserver
    {

        private logInForm _logInForm;

        private int index = -1;
        
        private List<TripDTO<int>> tripsDTO = new List<TripDTO<int>>();

        private IServices _server;

        private Service _service;

        private Person<int> _person;
        public tripsForm(Service service, Person<int> person, logInForm logInForm)
        {
            _service = service;
            _person = person;
            _logInForm = logInForm;
            InitializeComponent();
        }

        public void SetServer(IServices server)
        {
            _server = server;
            InitializeGUI();
        }

        public void ConstructTripsDTO()
        {
            tripsDTO.Clear();
            tripsDTO = _server.FindAllTrips();
        }
        
        public void FillTable()
        {
            if (tripsTable.Columns != null)
            {
                tripsTable.Columns["landMarkColumn"].DataPropertyName = "NameLandMark";
                tripsTable.Columns["nameCompany"].DataPropertyName = "NameTransportationCompany";
                tripsTable.Columns["departureTime"].DataPropertyName = "DepartureTime";
                tripsTable.Columns["priceColumn"].DataPropertyName = "Price";
                tripsTable.Columns["numberOfSeatsAvailable"].DataPropertyName = "NumberOfSeatsAvailable";
            }

            ConstructTripsDTO();
            tripsTable.DataSource = tripsDTO;

        }

        public void InitializeGUI()
        {
            customersComboBox.Items.AddRange(_service.FindAllCustomers().ToList().ToArray());
            FillTable();
        }

        private void landMarkTextBox_TextChanged(object sender, EventArgs e)
        {
            tripsTable.DataSource = tripsDTO
                .Where(tripsDTO => tripsDTO.NameLandMark.ToLower().Contains(landMarkTextBox.Text.ToLower()) && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(startHour.Value.ToString()), 0, 0)) >= 0 && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(endHour.Value.ToString()), 0, 0)) <= 0).ToList();
        }

        private void startHour_ValueChanged(object sender, EventArgs e)
        {
            tripsTable.DataSource = tripsDTO
                .Where(tripsDTO => tripsDTO.NameLandMark.ToLower().Contains(landMarkTextBox.Text.ToLower()) && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(startHour.Value.ToString()), 0, 0)) >= 0 && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(endHour.Value.ToString()), 0, 0)) <= 0).ToList();
        }

        private void endHour_ValueChanged(object sender, EventArgs e)
        {
            tripsTable.DataSource = tripsDTO
                .Where(tripsDTO => tripsDTO.NameLandMark.ToLower().Contains(landMarkTextBox.Text.ToLower()) && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(startHour.Value.ToString()), 0, 0)) >= 0 && DateTime.Compare(new DateTime(2023, 01, 01, tripsDTO.DepartureTime.Hour, tripsDTO.DepartureTime.Minute, 0), new DateTime(2023, 01, 01, Int32.Parse(endHour.Value.ToString()), 0, 0)) <= 0).ToList();
        }

        private void tripsTable_SelectionChanged(object sender, EventArgs e)
        {
            if (tripsTable.SelectedRows.Count == 1)
            {
                tabPage2.Enabled = true;
                tabPane.SelectTab(tabPage2);
            }
        }

        private void TabPaneOnSelected(object sender, TabControlEventArgs e)
        {
            if (e.TabPage == tabPage1)
            {
                tabPage2.Enabled = false;
            }
        }

        private void customersComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            Customer<int> customerSelected = (Customer<int>) customersComboBox.SelectedItem;
            numberOfTickets.Value = customerSelected.NumberOfTickets;
        }

        private void bookingButton_Click(object sender, EventArgs e)
        {
            if (tripsTable.SelectedRows.Count == 1)
            {
                index = tripsTable.SelectedRows[0].Index;
                var row1 = tripsTable.SelectedRows[0];
                var row2 = tripsTable.Rows[tripsTable.Rows.Count - 1];
                tripsTable.Rows[index].SetValues(row2);
                tripsTable.Rows[tripsTable.Rows.Count - 1].SetValues(row1);
                //tripsTable.Rows[tripsTable.Rows.Count - 1].Selected = true;
                //index = tripsTable.Rows.Count - 1;
                TripDTO<int> tripDTOSelected = (TripDTO<int>) tripsTable.SelectedRows[0].DataBoundItem;
                Customer<int> customerSelected = (Customer<int>) customersComboBox.SelectedItem;
                int difference = tripDTOSelected.NumberOfSeatsAvailable - Convert.ToInt32(numberOfTickets.Value);
                if(difference >= 0 && numberOfTickets.Value != 0 && customerSelected != null)
                {
                    _service.SaveBooking(new Booking<int>(1, _person, customerSelected, _service.FindOneTrip(tripDTOSelected.Id)));
                    //_service.UpdateNumberOfSeatsAvailable(Convert.ToInt32(numberOfTickets.Value), tripDTOSelected.Id);
                    tripDTOSelected.NumberOfSeatsAvailable = (Convert.ToInt32(numberOfTickets.Value));
                    _server.BookTrip(tripDTOSelected);
                    //FillTable();
                }else
                {
                    MessageBox.Show("This booking cannot be save");
                }
            }
        }

        private void logOutButton_Click(object sender, EventArgs e)
        {
            Hide();
            _server.Logout(_person, this);
            _logInForm.Closed += (s, args) => this.Close();
            _logInForm.Show();
        }

        public void newBooking(TripDTO<int> tripDTO)
        {
            tripsTable.BeginInvoke((Action) delegate
            {
               // MessageBox.Show("UPDATE");
               FillTable();
               if (index != -1)
               {
                   tripsTable.Rows[tripsTable.Rows.Count - 1].Selected = true;
                   index = -1;
               }
            });
        }
    }
}