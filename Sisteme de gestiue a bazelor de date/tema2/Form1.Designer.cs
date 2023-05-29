namespace tema1
{
    partial class Form1
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
            this.sterge = new System.Windows.Forms.Button();
            this.dataGridViewP = new System.Windows.Forms.DataGridView();
            this.dataGridViewS = new System.Windows.Forms.DataGridView();
            this.update = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.adauga = new System.Windows.Forms.Button();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewP)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewS)).BeginInit();
            this.SuspendLayout();
            // 
            // sterge
            // 
            this.sterge.Location = new System.Drawing.Point(945, 342);
            this.sterge.Name = "sterge";
            this.sterge.Size = new System.Drawing.Size(75, 23);
            this.sterge.TabIndex = 0;
            this.sterge.Text = "sterge";
            this.sterge.UseVisualStyleBackColor = true;
            this.sterge.Click += new System.EventHandler(this.sterge_click);
            // 
            // dataGridViewP
            // 
            this.dataGridViewP.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewP.Location = new System.Drawing.Point(29, 110);
            this.dataGridViewP.Name = "dataGridViewP";
            this.dataGridViewP.RowHeadersWidth = 51;
            this.dataGridViewP.RowTemplate.Height = 24;
            this.dataGridViewP.Size = new System.Drawing.Size(339, 236);
            this.dataGridViewP.TabIndex = 1;
            this.dataGridViewP.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewP_CellClick);
            // 
            // dataGridViewS
            // 
            this.dataGridViewS.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewS.Location = new System.Drawing.Point(431, 110);
            this.dataGridViewS.Name = "dataGridViewS";
            this.dataGridViewS.RowHeadersWidth = 51;
            this.dataGridViewS.RowTemplate.Height = 24;
            this.dataGridViewS.Size = new System.Drawing.Size(408, 236);
            this.dataGridViewS.TabIndex = 2;
            this.dataGridViewS.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewS_CellContentClick);
            // 
            // update
            // 
            this.update.Location = new System.Drawing.Point(945, 299);
            this.update.Name = "update";
            this.update.Size = new System.Drawing.Size(75, 23);
            this.update.TabIndex = 5;
            this.update.Text = "update";
            this.update.UseVisualStyleBackColor = true;
            this.update.Click += new System.EventHandler(this.update_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(157, 59);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(81, 16);
            this.label3.TabIndex = 8;
            this.label3.Text = "tabel echipe";
            this.label3.Click += new System.EventHandler(this.label3_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(566, 59);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(83, 16);
            this.label4.TabIndex = 9;
            this.label4.Text = "tabel jucatori";
            // 
            // adauga
            // 
            this.adauga.Location = new System.Drawing.Point(945, 256);
            this.adauga.Name = "adauga";
            this.adauga.Size = new System.Drawing.Size(75, 23);
            this.adauga.TabIndex = 12;
            this.adauga.Text = "adauga";
            this.adauga.UseVisualStyleBackColor = true;
            this.adauga.Click += new System.EventHandler(this.adauga_Click);
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Location = new System.Drawing.Point(919, 124);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(200, 100);
            this.tableLayoutPanel1.TabIndex = 13;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1287, 450);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.adauga);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.update);
            this.Controls.Add(this.dataGridViewS);
            this.Controls.Add(this.dataGridViewP);
            this.Controls.Add(this.sterge);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewP)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewS)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button sterge;
        private System.Windows.Forms.DataGridView dataGridViewP;
        private System.Windows.Forms.DataGridView dataGridViewS;
        private System.Windows.Forms.Button update;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button adauga;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
    }
}

