namespace tema1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.apasa = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // apasa
            // 
            this.apasa.Location = new System.Drawing.Point(609, 159);
            this.apasa.Name = "apasa";
            this.apasa.Size = new System.Drawing.Size(131, 29);
            this.apasa.TabIndex = 0;
            this.apasa.Text = "Apasaaa";
            this.apasa.UseVisualStyleBackColor = true;
            this.apasa.Click += new System.EventHandler(this.apasa_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1499, 689);
            this.Controls.Add(this.apasa);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private Button apasa;
    }
}