namespace temaCSharp
{
    partial class SellTickets
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
            this.nameTF = new System.Windows.Forms.TextBox();
            this.nTTF = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.sell = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // nameTF
            // 
            this.nameTF.Location = new System.Drawing.Point(322, 144);
            this.nameTF.Name = "nameTF";
            this.nameTF.Size = new System.Drawing.Size(185, 22);
            this.nameTF.TabIndex = 0;
            // 
            // nTTF
            // 
            this.nTTF.Location = new System.Drawing.Point(322, 214);
            this.nTTF.Name = "nTTF";
            this.nTTF.Size = new System.Drawing.Size(185, 22);
            this.nTTF.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(195, 150);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(41, 16);
            this.label1.TabIndex = 2;
            this.label1.Text = "name";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(195, 220);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(107, 16);
            this.label2.TabIndex = 3;
            this.label2.Text = "number of tickets";
            // 
            // sell
            // 
            this.sell.Location = new System.Drawing.Point(372, 332);
            this.sell.Name = "sell";
            this.sell.Size = new System.Drawing.Size(75, 23);
            this.sell.TabIndex = 4;
            this.sell.Text = "sell";
            this.sell.UseVisualStyleBackColor = true;
            this.sell.Click += new System.EventHandler(this.sell_Click);
            // 
            // SellTickets
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.sell);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.nTTF);
            this.Controls.Add(this.nameTF);
            this.Name = "SellTickets";
            this.Text = "SellTickets";
            this.Load += new System.EventHandler(this.SellTickets_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox nameTF;
        private System.Windows.Forms.TextBox nTTF;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button sell;
    }
}