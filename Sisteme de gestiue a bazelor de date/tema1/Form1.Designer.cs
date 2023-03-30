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
            this.varstaTF = new System.Windows.Forms.TextBox();
            this.echipaTF = new System.Windows.Forms.TextBox();
            this.update = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.adauga = new System.Windows.Forms.Button();
            this.numeJucatorTF = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
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
            // varstaTF
            // 
            this.varstaTF.Location = new System.Drawing.Point(945, 160);
            this.varstaTF.Name = "varstaTF";
            this.varstaTF.Size = new System.Drawing.Size(100, 22);
            this.varstaTF.TabIndex = 3;
            // 
            // echipaTF
            // 
            this.echipaTF.Location = new System.Drawing.Point(945, 204);
            this.echipaTF.Name = "echipaTF";
            this.echipaTF.Size = new System.Drawing.Size(100, 22);
            this.echipaTF.TabIndex = 4;
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
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(845, 160);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(44, 16);
            this.label1.TabIndex = 6;
            this.label1.Text = "varsta";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(845, 204);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(48, 16);
            this.label2.TabIndex = 7;
            this.label2.Text = "echipa";
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
            // numeJucatorTF
            // 
            this.numeJucatorTF.Location = new System.Drawing.Point(945, 121);
            this.numeJucatorTF.Name = "numeJucatorTF";
            this.numeJucatorTF.Size = new System.Drawing.Size(100, 22);
            this.numeJucatorTF.TabIndex = 13;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(848, 121);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(83, 16);
            this.label5.TabIndex = 14;
            this.label5.Text = "nume jucator";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1109, 450);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.numeJucatorTF);
            this.Controls.Add(this.adauga);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.update);
            this.Controls.Add(this.echipaTF);
            this.Controls.Add(this.varstaTF);
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
        private System.Windows.Forms.TextBox varstaTF;
        private System.Windows.Forms.TextBox echipaTF;
        private System.Windows.Forms.Button update;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button adauga;
        private System.Windows.Forms.TextBox numeJucatorTF;
        private System.Windows.Forms.Label label5;
    }
}

