namespace temaCSharp
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
            this.components = new System.ComponentModel.Container();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.musicFestivalDataSet = new temaCSharp.musicFestivalDataSet();
            this.showsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.showsTableAdapter = new temaCSharp.musicFestivalDataSetTableAdapters.showsTableAdapter();
            this.showNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.artistNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dateDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.placeOfShowDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.noTicketsAvailableDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.noTicketsSoldDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.search_artist = new System.Windows.Forms.Button();
            this.sell_tickets = new System.Windows.Forms.Button();
            this.log_out = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.musicFestivalDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.showsBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.showNameDataGridViewTextBoxColumn,
            this.artistNameDataGridViewTextBoxColumn,
            this.dateDataGridViewTextBoxColumn,
            this.placeOfShowDataGridViewTextBoxColumn,
            this.noTicketsAvailableDataGridViewTextBoxColumn,
            this.noTicketsSoldDataGridViewTextBoxColumn});
            this.dataGridView1.DataSource = this.showsBindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(12, 12);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(784, 426);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // musicFestivalDataSet
            // 
            this.musicFestivalDataSet.DataSetName = "musicFestivalDataSet";
            this.musicFestivalDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // showsBindingSource
            // 
            this.showsBindingSource.DataMember = "shows";
            this.showsBindingSource.DataSource = this.musicFestivalDataSet;
            // 
            // showsTableAdapter
            // 
            this.showsTableAdapter.ClearBeforeFill = true;
            // 
            // showNameDataGridViewTextBoxColumn
            // 
            this.showNameDataGridViewTextBoxColumn.DataPropertyName = "showName";
            this.showNameDataGridViewTextBoxColumn.HeaderText = "showName";
            this.showNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.showNameDataGridViewTextBoxColumn.Name = "showNameDataGridViewTextBoxColumn";
            this.showNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // artistNameDataGridViewTextBoxColumn
            // 
            this.artistNameDataGridViewTextBoxColumn.DataPropertyName = "artistName";
            this.artistNameDataGridViewTextBoxColumn.HeaderText = "artistName";
            this.artistNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.artistNameDataGridViewTextBoxColumn.Name = "artistNameDataGridViewTextBoxColumn";
            this.artistNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // dateDataGridViewTextBoxColumn
            // 
            this.dateDataGridViewTextBoxColumn.DataPropertyName = "date";
            this.dateDataGridViewTextBoxColumn.HeaderText = "date";
            this.dateDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.dateDataGridViewTextBoxColumn.Name = "dateDataGridViewTextBoxColumn";
            this.dateDataGridViewTextBoxColumn.Width = 125;
            // 
            // placeOfShowDataGridViewTextBoxColumn
            // 
            this.placeOfShowDataGridViewTextBoxColumn.DataPropertyName = "placeOfShow";
            this.placeOfShowDataGridViewTextBoxColumn.HeaderText = "placeOfShow";
            this.placeOfShowDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.placeOfShowDataGridViewTextBoxColumn.Name = "placeOfShowDataGridViewTextBoxColumn";
            this.placeOfShowDataGridViewTextBoxColumn.Width = 125;
            // 
            // noTicketsAvailableDataGridViewTextBoxColumn
            // 
            this.noTicketsAvailableDataGridViewTextBoxColumn.DataPropertyName = "noTicketsAvailable";
            this.noTicketsAvailableDataGridViewTextBoxColumn.HeaderText = "noTicketsAvailable";
            this.noTicketsAvailableDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.noTicketsAvailableDataGridViewTextBoxColumn.Name = "noTicketsAvailableDataGridViewTextBoxColumn";
            this.noTicketsAvailableDataGridViewTextBoxColumn.Width = 125;
            // 
            // noTicketsSoldDataGridViewTextBoxColumn
            // 
            this.noTicketsSoldDataGridViewTextBoxColumn.DataPropertyName = "noTicketsSold";
            this.noTicketsSoldDataGridViewTextBoxColumn.HeaderText = "noTicketsSold";
            this.noTicketsSoldDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.noTicketsSoldDataGridViewTextBoxColumn.Name = "noTicketsSoldDataGridViewTextBoxColumn";
            this.noTicketsSoldDataGridViewTextBoxColumn.Width = 125;
            // 
            // search_artist
            // 
            this.search_artist.Location = new System.Drawing.Point(869, 98);
            this.search_artist.Name = "search_artist";
            this.search_artist.Size = new System.Drawing.Size(95, 23);
            this.search_artist.TabIndex = 1;
            this.search_artist.Text = "search artist";
            this.search_artist.UseVisualStyleBackColor = true;
            this.search_artist.Click += new System.EventHandler(this.search_artist_Click);
            // 
            // sell_tickets
            // 
            this.sell_tickets.Location = new System.Drawing.Point(869, 203);
            this.sell_tickets.Name = "sell_tickets";
            this.sell_tickets.Size = new System.Drawing.Size(95, 23);
            this.sell_tickets.TabIndex = 2;
            this.sell_tickets.Text = "sell tickets";
            this.sell_tickets.UseVisualStyleBackColor = true;
            this.sell_tickets.Click += new System.EventHandler(this.sell_tickets_Click);
            // 
            // log_out
            // 
            this.log_out.Location = new System.Drawing.Point(869, 309);
            this.log_out.Name = "log_out";
            this.log_out.Size = new System.Drawing.Size(95, 23);
            this.log_out.TabIndex = 3;
            this.log_out.Text = "log out";
            this.log_out.UseVisualStyleBackColor = true;
            // 
            // MainPage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1010, 450);
            this.Controls.Add(this.log_out);
            this.Controls.Add(this.sell_tickets);
            this.Controls.Add(this.search_artist);
            this.Controls.Add(this.dataGridView1);
            this.Name = "MainPage";
            this.Text = "Form2";
            this.Load += new System.EventHandler(this.Form2_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.musicFestivalDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.showsBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private musicFestivalDataSet musicFestivalDataSet;
        private System.Windows.Forms.BindingSource showsBindingSource;
        private musicFestivalDataSetTableAdapters.showsTableAdapter showsTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn showNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn artistNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn dateDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn placeOfShowDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn noTicketsAvailableDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn noTicketsSoldDataGridViewTextBoxColumn;
        private System.Windows.Forms.Button search_artist;
        private System.Windows.Forms.Button sell_tickets;
        private System.Windows.Forms.Button log_out;
    }
}