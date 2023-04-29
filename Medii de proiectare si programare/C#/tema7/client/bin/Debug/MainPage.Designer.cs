namespace client
{
    partial class MainPage
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.main_page = new System.Windows.Forms.TabPage();
            this.search = new System.Windows.Forms.Button();
            this.dateTimePicker = new System.Windows.Forms.DateTimePicker();
            this.logOut = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.sellPage = new System.Windows.Forms.TabPage();
            this.label2 = new System.Windows.Forms.Label();
            this.name = new System.Windows.Forms.Label();
            this.sell = new System.Windows.Forms.Button();
            this.noTTF = new System.Windows.Forms.TextBox();
            this.nameTF = new System.Windows.Forms.TextBox();
            this.tabControl1.SuspendLayout();
            this.main_page.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.sellPage.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.main_page);
            this.tabControl1.Controls.Add(this.sellPage);
            this.tabControl1.Location = new System.Drawing.Point(12, 4);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(939, 444);
            this.tabControl1.TabIndex = 0;
            this.tabControl1.Selected += new System.Windows.Forms.TabControlEventHandler(this.TabPaneSelected);
            // 
            // main_page
            // 
            this.main_page.Controls.Add(this.search);
            this.main_page.Controls.Add(this.dateTimePicker);
            this.main_page.Controls.Add(this.logOut);
            this.main_page.Controls.Add(this.dataGridView1);
            this.main_page.Location = new System.Drawing.Point(4, 25);
            this.main_page.Name = "main_page";
            this.main_page.Padding = new System.Windows.Forms.Padding(3);
            this.main_page.Size = new System.Drawing.Size(931, 415);
            this.main_page.TabIndex = 0;
            this.main_page.Text = "main page";
            this.main_page.UseVisualStyleBackColor = true;
            // 
            // search
            // 
            this.search.Location = new System.Drawing.Point(526, 22);
            this.search.Name = "search";
            this.search.Size = new System.Drawing.Size(75, 23);
            this.search.TabIndex = 4;
            this.search.Text = "search";
            this.search.UseVisualStyleBackColor = true;
            this.search.Click += new System.EventHandler(this.search_Click);
            // 
            // dateTimePicker
            // 
            this.dateTimePicker.Location = new System.Drawing.Point(239, 20);
            this.dateTimePicker.Name = "dateTimePicker";
            this.dateTimePicker.Size = new System.Drawing.Size(241, 22);
            this.dateTimePicker.TabIndex = 2;
            // 
            // logOut
            // 
            this.logOut.Location = new System.Drawing.Point(834, 197);
            this.logOut.Name = "logOut";
            this.logOut.Size = new System.Drawing.Size(75, 23);
            this.logOut.TabIndex = 1;
            this.logOut.Text = "log out";
            this.logOut.UseVisualStyleBackColor = true;
            this.logOut.Click += new System.EventHandler(this.button1_Click);
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(6, 48);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(808, 350);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.SelectionChanged += new System.EventHandler(this.SelectionChanged);
            // 
            // sellPage
            // 
            this.sellPage.Controls.Add(this.label2);
            this.sellPage.Controls.Add(this.name);
            this.sellPage.Controls.Add(this.sell);
            this.sellPage.Controls.Add(this.noTTF);
            this.sellPage.Controls.Add(this.nameTF);
            this.sellPage.Location = new System.Drawing.Point(4, 25);
            this.sellPage.Name = "sellPage";
            this.sellPage.Padding = new System.Windows.Forms.Padding(3);
            this.sellPage.Size = new System.Drawing.Size(931, 415);
            this.sellPage.TabIndex = 1;
            this.sellPage.Text = "sell tickets";
            this.sellPage.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(143, 206);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(122, 23);
            this.label2.TabIndex = 4;
            this.label2.Text = "number of tickets";
            // 
            // name
            // 
            this.name.Location = new System.Drawing.Point(156, 122);
            this.name.Name = "name";
            this.name.Size = new System.Drawing.Size(100, 23);
            this.name.TabIndex = 3;
            this.name.Text = "name";
            // 
            // sell
            // 
            this.sell.Location = new System.Drawing.Point(388, 301);
            this.sell.Name = "sell";
            this.sell.Size = new System.Drawing.Size(75, 23);
            this.sell.TabIndex = 2;
            this.sell.Text = "sell";
            this.sell.UseVisualStyleBackColor = true;
            this.sell.Click += new System.EventHandler(this.sell_Click_1);
            // 
            // noTTF
            // 
            this.noTTF.Location = new System.Drawing.Point(290, 207);
            this.noTTF.Name = "noTTF";
            this.noTTF.Size = new System.Drawing.Size(277, 22);
            this.noTTF.TabIndex = 1;
            // 
            // nameTF
            // 
            this.nameTF.Location = new System.Drawing.Point(290, 119);
            this.nameTF.Name = "nameTF";
            this.nameTF.Size = new System.Drawing.Size(277, 22);
            this.nameTF.TabIndex = 0;
            // 
            // MainPage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.ClientSize = new System.Drawing.Size(963, 460);
            this.Controls.Add(this.tabControl1);
            this.Location = new System.Drawing.Point(15, 15);
            this.Name = "MainPage";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainPage_FormClosing);
            this.Load += new System.EventHandler(this.Form2_Load);
            this.tabControl1.ResumeLayout(false);
            this.main_page.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.sellPage.ResumeLayout(false);
            this.sellPage.PerformLayout();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.TabPage main_page;

        private System.Windows.Forms.Button search;

        private System.Windows.Forms.DateTimePicker dateTimePicker;

        private System.Windows.Forms.Button logOut;
        private System.Windows.Forms.TextBox nameTF;
        private System.Windows.Forms.TextBox noTTF;
        private System.Windows.Forms.Label name;
        private System.Windows.Forms.Label label2;

        private System.Windows.Forms.Button sell;

        // private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage sellPage;
        private System.Windows.Forms.DataGridView dataGridView1;

        private System.Windows.Forms.TabControl tabControl1;

        // private System.Windows.Forms.DataGridViewTextBoxColumn noTicketsSoldDataGridViewTextBoxColumn;
        // private System.Windows.Forms.DataGridViewTextBoxColumn noTicketsAvailableDataGridViewTextBoxColumn;
        // private System.Windows.Forms.DataGridViewTextBoxColumn placeOfShowDataGridViewTextBoxColumn;
        // private System.Windows.Forms.DataGridViewTextBoxColumn dateDataGridViewTextBoxColumn;
        // private System.Windows.Forms.DataGridViewTextBoxColumn artistNameDataGridViewTextBoxColumn;
        // private System.Windows.Forms.DataGridViewTextBoxColumn showNameDataGridViewTextBoxColumn;

        #endregion

        // private temaCSharp.musicFestivalDataSet musicFestivalDataSet;
        // private musicFestivalDataSetTableAdapters.showsTableAdapter showsTableAdapter;
    }
}