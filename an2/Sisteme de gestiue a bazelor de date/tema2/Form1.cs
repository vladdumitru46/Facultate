using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data;
using Microsoft.Data.SqlClient;
using System.Configuration;

namespace tema1
{
    public partial class Form1 : Form
    {
        string connectionString = ConfigurationManager.ConnectionStrings["cn"].ConnectionString;
        SqlDataAdapter adapter = new SqlDataAdapter();
        BindingSource bindingSource = new BindingSource();
        SqlDataAdapter adapterSon = new SqlDataAdapter();
        BindingSource bindingSourceSon = new BindingSource();
        DataSet dataSetP = new DataSet();
        DataSet dataSet = new DataSet();

        List<string> columnNames = new List<string>(ConfigurationManager.AppSettings["childColumnNames"].Split(','));
        int noOfColumns = int.Parse(ConfigurationManager.AppSettings["childNrColumns"]);
        string parentName = ConfigurationManager.AppSettings["parentName"];
        string childName = ConfigurationManager.AppSettings["childName"];
        List<TextBox> textFieldNames = new List<TextBox>();
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
                    string sql = ConfigurationManager.AppSettings["delete"];
                    string sql2 = ConfigurationManager.AppSettings["selectAll"];
                    adapterSon.DeleteCommand = new SqlCommand(sql, conn);
                    adapterSon.DeleteCommand.Parameters.Add(columnNames[0], SqlDbType.VarChar).Value = dataSet.Tables[0].Rows[bindingSourceSon.Position][0];
                    adapterSon.DeleteCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand(sql2, conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, childName);
                    DataColumn parentColumn = dataSet.Tables[childName].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables[childName];
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

                    string sql = ConfigurationManager.AppSettings["update"];
                    string sql2 = ConfigurationManager.AppSettings["selectAll"];
                    adapterSon.UpdateCommand = new SqlCommand(sql, conn);
                    adapterSon.UpdateCommand.Parameters.Add(columnNames[0], SqlDbType.VarChar).Value = dataSet.Tables[0].Rows[bindingSourceSon.Position][0];
                    for (int i = 1; i < columnNames.Count; i++)
                    {
                        adapterSon.UpdateCommand.Parameters.AddWithValue(columnNames[i], textFieldNames[i].Text);
                    }
                    adapterSon.UpdateCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand(sql2, conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet,childName);
                    DataColumn parentColumn = dataSet.Tables[childName].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables[childName];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error!");
                }
            }
            
        }

        private void insertIntoPanel()
        {
            int i;
            tableLayoutPanel1.RowCount = noOfColumns;
            tableLayoutPanel1.ColumnCount = 3;

            for(i = 0; i < tableLayoutPanel1.RowCount;i++)
            {
                tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Absolute, 30F));
            }
            int cnt = 0;
            foreach(string k in columnNames)
            {
                Label label = new Label();
                label.Text = k;
                label.AutoSize = true;
                TextBox textBox = new TextBox();
                tableLayoutPanel1.Controls.Add(textBox, 0, cnt);
                tableLayoutPanel1.Controls.Add(label, 0, cnt);
                textFieldNames.Add(textBox);
                cnt++;  
            }

        }
        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                string con = ConfigurationManager.ConnectionStrings["cn"].ConnectionString;
                using (SqlConnection conn = new SqlConnection(con))
                {
                    conn.Open();
                    string select = ConfigurationManager.AppSettings["select"];
                    adapter.SelectCommand = new SqlCommand(select, conn);
                    adapter.Fill(dataSetP, parentName);
                    DataColumn parentColumn = dataSetP.Tables[parentName].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSource.DataSource = dataSetP.Tables[parentName];
                    dataGridViewP.DataSource = bindingSource;
                    insertIntoPanel();
                    conn.Close();
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
                    adapterSon.SelectCommand = new SqlCommand(ConfigurationManager.AppSettings["selectFromParent"], conn);
                    adapterSon.SelectCommand.Parameters.Add(ConfigurationManager.AppSettings["parentPrimary"], SqlDbType.VarChar).Value = selectedRow.Cells[0].Value.ToString();
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, childName);
                    DataColumn parentColumn = dataSet.Tables[childName].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables[childName];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
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

                    string sql = ConfigurationManager.AppSettings["insert"];
                    string sql2 = ConfigurationManager.AppSettings["selectAll"];
                    string ne = "INSERT INTO " + childName + " VALUES ( " + ConfigurationManager.AppSettings["childColumnNames"] + " )";
                    adapterSon.InsertCommand = new SqlCommand(ne, conn);
                    for (int i = 0; i < columnNames.Count; i++)
                    {
                        adapterSon.InsertCommand.Parameters.AddWithValue(columnNames[i], textFieldNames[i].Text);
                    }
                    adapterSon.InsertCommand.ExecuteNonQuery();
                    adapterSon.SelectCommand = new SqlCommand(sql2, conn);
                    dataSet.Clear();
                    adapterSon.Fill(dataSet);
                    adapterSon.Fill(dataSet, childName);
                    DataColumn parentColumn = dataSet.Tables[childName].Columns["cod_d"];
                    //DataRelation relation = new D
                    bindingSourceSon.DataSource = dataSet.Tables[childName];
                    dataGridViewS.DataSource = bindingSourceSon;
                    conn.Close();
                }
                catch(Exception ex)
                {
                    MessageBox.Show("Error!");
                }
            }
            
        }

    
    }
}
