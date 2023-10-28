using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Microsoft.Data.SqlClient;
using System.Data;

namespace tema1
{
    public partial class Form1 : Form
    {
        string connectionString = @"Server=DESKTOP-SO4QA4B\SQLEXPRESS;
        Database=Club_de_fotbal;Integrated Security=true;
        TrustServerCertificate=true;";
        SqlDataAdapter adapter = new SqlDataAdapter();
        BindingSource bindingSource = new BindingSource();
        SqlDataAdapter adapterSon = new SqlDataAdapter();
        BindingSource bindingSourceSon = new BindingSource();
        DataSet dataSetP = new DataSet();
        DataSet dataSet = new DataSet();
       
        public Form1()
        {
            InitializeComponent();
        }

        private void sterge_click(object sender, EventArgs j)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {

                try
                {
                    conn.Open();
                    adapterSon.DeleteCommand = new SqlCommand("DELETE FROM Jucatori WHERE nume_jucator = @j;", conn);
                    adapterSon.DeleteCommand.Parameters.Add("@j", SqlDbType.VarChar).Value = dataSet.Tables[0].Rows[bindingSourceSon.Position][0];
                    adapterSon.DeleteCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand("SELECT * FROM Jucatori", conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, "Jucatori");
                    DataColumn parentColumn = dataSet.Tables["Jucatori"].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables["Jucatori"];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Eroare!");
                }
            }
        }

            private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void dataGridViewS_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void veziJucatori_click(object sender, EventArgs e)
        {
            //Echipa echipa = (Echipa)dataGridViewP.Selected;
           // string nume_echipa = dataGridViewP.SelectedCells();

        }

        private void update_Click(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                try
                {
                    conn.Open();
                    adapterSon.UpdateCommand = new SqlCommand("UPDATE Jucatori SET varsta=@v, nume_echipa=@e where nume_jucator = @j", conn);
                    adapterSon.UpdateCommand.Parameters.Add("@v", SqlDbType.Int).Value = varstaTF.Text;
                    if (int.Parse(varstaTF.Text) <= 16 || int.Parse(varstaTF.Text) >= 38)
                    {
                        MessageBox.Show("Varsta trebe sa fie intre 16 si 38 de ani!\n");
                        return;
                    }
                    adapterSon.UpdateCommand.Parameters.Add("@e", SqlDbType.VarChar).Value = echipaTF.Text;
                    adapterSon.UpdateCommand.Parameters.Add("@j", SqlDbType.VarChar).Value = dataSet.Tables[0].Rows[bindingSourceSon.Position][0];
                    adapterSon.UpdateCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand("SELECT * FROM Jucatori", conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, "Jucatori");
                    DataColumn parentColumn = dataSet.Tables["Jucatori"].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables["Jucatori"];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
                }
                catch (Exception ex)
                {
                    string c = "";
                    if (varstaTF.Text == "")
                    {
                        c += "Varsta invalida!\n";
                    }
                    if (echipaTF.Text == "")
                    {
                        c += "Echipa invalida!\n";
                    }
                    MessageBox.Show(c);
                }
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                using(SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    adapter.SelectCommand = new SqlCommand("SELECT * FROM " + "echipa;", conn);
                    adapter.Fill(dataSetP, "echipa");
                    DataColumn parentColumn = dataSetP.Tables["echipa"].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSource.DataSource = dataSetP.Tables["echipa"];
                    dataGridViewP.DataSource = bindingSource;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewP_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            int index = e.RowIndex;
            DataGridViewRow selectedRow = dataGridViewP.Rows[index];
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    adapterSon.SelectCommand = new SqlCommand("SELECT * FROM Jucatori WHERE nume_echipa=@as;", conn);
                    adapterSon.SelectCommand.Parameters.Add("@as", SqlDbType.VarChar).Value = selectedRow.Cells[0].Value.ToString();
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, "Jucatori");
                    DataColumn parentColumn = dataSet.Tables["Jucatori"].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables["Jucatori"];
                    dataGridViewS.DataSource = bindingSourceSon;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void adauga_Click(object sender, EventArgs e)
        {
            using (SqlConnection conn = new SqlConnection(connectionString))
            {
                try
                {
                    conn.Open();
                    adapterSon.InsertCommand = new SqlCommand("INSERT INTO Jucatori VALUES (@n, @v, @e);", conn);
                    adapterSon.InsertCommand.Parameters.Add("@n", SqlDbType.VarChar).Value = numeJucatorTF.Text;
                    if (int.Parse(varstaTF.Text) <= 16 || int.Parse(varstaTF.Text) >= 38)
                    {
                        MessageBox.Show( "Varsta trebe sa fie intre 16 si 38 de ani!\n");
                        return;
                    }
                    adapterSon.InsertCommand.Parameters.Add("@v", SqlDbType.Int).Value = varstaTF.Text;
                    adapterSon.InsertCommand.Parameters.Add("@e", SqlDbType.VarChar).Value = echipaTF.Text;
                    adapterSon.InsertCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand("SELECT * FROM Jucatori", conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, "Jucatori");
                    DataColumn parentColumn = dataSet.Tables["Jucatori"].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables["Jucatori"];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
                }
                catch(Exception ex)
                {
                    string c = "";
                    if(numeJucatorTF.Text == "")
                    {
                        c += "Nume invalid!\n";
                    }
                    if (varstaTF.Text == "")
                    {
                        c += "Varsta invalida!\n";
                    }
                    if (int.Parse(varstaTF.Text) <= 16 || int.Parse(varstaTF.Text) >= 38)
                    {
                        c += "Varsta trebe sa fie intre 16 si 38 de ani!\n";
                    }
                    if (echipaTF.Text == "")
                    {
                        c += "Echipa invalida!\n";
                    }
                    MessageBox.Show(c);
                }
            }
        }

    
    }
}
