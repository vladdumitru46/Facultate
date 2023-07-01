using System.ComponentModel;

namespace Proiect
{
    partial class tripsForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

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
            this.tabPane = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.logOutButton = new System.Windows.Forms.Button();
            this.endHour = new System.Windows.Forms.NumericUpDown();
            this.startHour = new System.Windows.Forms.NumericUpDown();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.landMarkTextBox = new System.Windows.Forms.TextBox();
            this.tripsTable = new System.Windows.Forms.DataGridView();
            this.landMarkColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.nameCompany = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.departureTime = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.priceColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.numberOfSeatsAvailable = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.bookingButton = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.numberOfTickets = new System.Windows.Forms.NumericUpDown();
            this.label3 = new System.Windows.Forms.Label();
            this.customersComboBox = new System.Windows.Forms.ComboBox();
            this.tabPane.SuspendLayout();
            this.tabPage1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.endHour)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.startHour)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.tripsTable)).BeginInit();
            this.tabPage2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numberOfTickets)).BeginInit();
            this.SuspendLayout();
            // 
            // tabPane
            // 
            this.tabPane.Controls.Add(this.tabPage1);
            this.tabPane.Controls.Add(this.tabPage2);
            this.tabPane.Location = new System.Drawing.Point(0, 1);
            this.tabPane.Name = "tabPane";
            this.tabPane.SelectedIndex = 0;
            this.tabPane.Size = new System.Drawing.Size(912, 459);
            this.tabPane.TabIndex = 0;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.logOutButton);
            this.tabPage1.Controls.Add(this.endHour);
            this.tabPage1.Controls.Add(this.startHour);
            this.tabPage1.Controls.Add(this.label2);
            this.tabPage1.Controls.Add(this.label1);
            this.tabPage1.Controls.Add(this.landMarkTextBox);
            this.tabPage1.Controls.Add(this.tripsTable);
            this.tabPage1.Location = new System.Drawing.Point(4, 25);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(904, 430);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Excursii";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // logOutButton
            // 
            this.logOutButton.Location = new System.Drawing.Point(354, 383);
            this.logOutButton.Name = "logOutButton";
            this.logOutButton.Size = new System.Drawing.Size(108, 36);
            this.logOutButton.TabIndex = 7;
            this.logOutButton.Text = "LogOut";
            this.logOutButton.UseVisualStyleBackColor = true;
            this.logOutButton.Click += new System.EventHandler(this.logOutButton_Click);
            // 
            // endHour
            // 
            this.endHour.Location = new System.Drawing.Point(670, 87);
            this.endHour.Maximum = new decimal(new int[] { 23, 0, 0, 0 });
            this.endHour.Name = "endHour";
            this.endHour.Size = new System.Drawing.Size(58, 22);
            this.endHour.TabIndex = 6;
            this.endHour.Value = new decimal(new int[] { 12, 0, 0, 0 });
            this.endHour.ValueChanged += new System.EventHandler(this.endHour_ValueChanged);
            // 
            // startHour
            // 
            this.startHour.Location = new System.Drawing.Point(577, 87);
            this.startHour.Maximum = new decimal(new int[] { 23, 0, 0, 0 });
            this.startHour.Name = "startHour";
            this.startHour.Size = new System.Drawing.Size(58, 22);
            this.startHour.TabIndex = 5;
            this.startHour.Value = new decimal(new int[] { 10, 0, 0, 0 });
            this.startHour.ValueChanged += new System.EventHandler(this.startHour_ValueChanged);
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(354, 87);
            this.label2.Name = "label2";
            this.label2.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.label2.Size = new System.Drawing.Size(171, 23);
            this.label2.TabIndex = 4;
            this.label2.Text = "Interval ora de plecare:";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(370, 46);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(136, 23);
            this.label1.TabIndex = 2;
            this.label1.Text = "Obiectiv turistic:";
            // 
            // landMarkTextBox
            // 
            this.landMarkTextBox.Location = new System.Drawing.Point(563, 43);
            this.landMarkTextBox.Name = "landMarkTextBox";
            this.landMarkTextBox.Size = new System.Drawing.Size(174, 22);
            this.landMarkTextBox.TabIndex = 1;
            this.landMarkTextBox.TextChanged += new System.EventHandler(this.landMarkTextBox_TextChanged);
            // 
            // tripsTable
            // 
            this.tripsTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.tripsTable.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] { this.landMarkColumn, this.nameCompany, this.departureTime, this.priceColumn, this.numberOfSeatsAvailable });
            this.tripsTable.Location = new System.Drawing.Point(0, 176);
            this.tripsTable.Name = "tripsTable";
            this.tripsTable.RowTemplate.Height = 24;
            this.tripsTable.Size = new System.Drawing.Size(905, 187);
            this.tripsTable.TabIndex = 0;
            // 
            // landMarkColumn
            // 
            this.landMarkColumn.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.landMarkColumn.HeaderText = "Obiectiv turistic";
            this.landMarkColumn.Name = "landMarkColumn";
            // 
            // nameCompany
            // 
            this.nameCompany.HeaderText = "Nume firma";
            this.nameCompany.Name = "nameCompany";
            // 
            // departureTime
            // 
            this.departureTime.HeaderText = "Ora plecarii";
            this.departureTime.Name = "departureTime";
            // 
            // priceColumn
            // 
            this.priceColumn.HeaderText = "Pret";
            this.priceColumn.Name = "priceColumn";
            // 
            // numberOfSeatsAvailable
            // 
            this.numberOfSeatsAvailable.HeaderText = "Numar de locuri";
            this.numberOfSeatsAvailable.Name = "numberOfSeatsAvailable";
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.bookingButton);
            this.tabPage2.Controls.Add(this.label4);
            this.tabPage2.Controls.Add(this.numberOfTickets);
            this.tabPage2.Controls.Add(this.label3);
            this.tabPage2.Controls.Add(this.customersComboBox);
            this.tabPage2.Enabled = false;
            this.tabPage2.Location = new System.Drawing.Point(4, 25);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(904, 430);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Rezervare";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // bookingButton
            // 
            this.bookingButton.Location = new System.Drawing.Point(312, 304);
            this.bookingButton.Name = "bookingButton";
            this.bookingButton.Size = new System.Drawing.Size(166, 29);
            this.bookingButton.TabIndex = 4;
            this.bookingButton.Text = "Rezerva";
            this.bookingButton.UseVisualStyleBackColor = true;
            this.bookingButton.Click += new System.EventHandler(this.bookingButton_Click);
            // 
            // label4
            // 
            this.label4.Location = new System.Drawing.Point(205, 213);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(170, 23);
            this.label4.TabIndex = 3;
            this.label4.Text = "Numar de bilete dorite:";
            // 
            // numberOfTickets
            // 
            this.numberOfTickets.Location = new System.Drawing.Point(422, 211);
            this.numberOfTickets.Name = "numberOfTickets";
            this.numberOfTickets.Size = new System.Drawing.Size(56, 22);
            this.numberOfTickets.TabIndex = 2;
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(236, 143);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(74, 23);
            this.label3.TabIndex = 1;
            this.label3.Text = "Clienti:";
            // 
            // customersComboBox
            // 
            this.customersComboBox.FormattingEnabled = true;
            this.customersComboBox.Location = new System.Drawing.Point(351, 140);
            this.customersComboBox.Name = "customersComboBox";
            this.customersComboBox.Size = new System.Drawing.Size(213, 24);
            this.customersComboBox.TabIndex = 0;
            this.customersComboBox.SelectedIndexChanged += new System.EventHandler(this.customersComboBox_SelectedIndexChanged);
            // 
            // tripsForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.Control;
            this.ClientSize = new System.Drawing.Size(907, 463);
            this.Controls.Add(this.tabPane);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Location = new System.Drawing.Point(15, 15);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "tripsForm";
            this.tabPane.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.endHour)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.startHour)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.tripsTable)).EndInit();
            this.tabPage2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.numberOfTickets)).EndInit();
            this.ResumeLayout(false);
        }

        private System.Windows.Forms.Button logOutButton;

        private System.Windows.Forms.Button bookingButton;

        private System.Windows.Forms.Label label4;

        private System.Windows.Forms.NumericUpDown numberOfTickets;

        private System.Windows.Forms.Label label3;

        private System.Windows.Forms.ComboBox customersComboBox;

        private System.Windows.Forms.NumericUpDown startHour;
        private System.Windows.Forms.NumericUpDown endHour;

        private System.Windows.Forms.Label label2;

        private System.Windows.Forms.Label label1;

        private System.Windows.Forms.TextBox landMarkTextBox;

        private System.Windows.Forms.DataGridViewTextBoxColumn landMarkColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn nameCompany;
        private System.Windows.Forms.DataGridViewTextBoxColumn departureTime;
        private System.Windows.Forms.DataGridViewTextBoxColumn priceColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn numberOfSeatsAvailable;

        private System.Windows.Forms.DataGridView tripsTable;

        private System.Windows.Forms.TabControl tabPane;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;

        #endregion
    }
}