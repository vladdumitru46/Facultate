namespace chat.client
{
    partial class ChatWindow
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
            this.messageList = new System.Windows.Forms.ListBox();
            this.friendList = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.messText = new System.Windows.Forms.TextBox();
            this.sendBut = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // messageList
            // 
            this.messageList.FormattingEnabled = true;
            this.messageList.ItemHeight = 16;
            this.messageList.Location = new System.Drawing.Point(19, 39);
            this.messageList.Margin = new System.Windows.Forms.Padding(4);
            this.messageList.Name = "messageList";
            this.messageList.Size = new System.Drawing.Size(328, 212);
            this.messageList.TabIndex = 0;
            this.messageList.SelectedIndexChanged += new System.EventHandler(this.messageList_SelectedIndexChanged);
            // 
            // friendList
            // 
            this.friendList.FormattingEnabled = true;
            this.friendList.ItemHeight = 16;
            this.friendList.Location = new System.Drawing.Point(376, 36);
            this.friendList.Margin = new System.Windows.Forms.Padding(4);
            this.friendList.Name = "friendList";
            this.friendList.Size = new System.Drawing.Size(127, 212);
            this.friendList.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(27, 16);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(72, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "Messages";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(387, 17);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(103, 17);
            this.label2.TabIndex = 3;
            this.label2.Text = "Logged friends";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(21, 266);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(76, 17);
            this.label3.TabIndex = 4;
            this.label3.Text = "Messsage ";
            // 
            // messText
            // 
            this.messText.Location = new System.Drawing.Point(149, 267);
            this.messText.Margin = new System.Windows.Forms.Padding(4);
            this.messText.Name = "messText";
            this.messText.Size = new System.Drawing.Size(308, 22);
            this.messText.TabIndex = 5;
            // 
            // sendBut
            // 
            this.sendBut.Location = new System.Drawing.Point(153, 319);
            this.sendBut.Margin = new System.Windows.Forms.Padding(4);
            this.sendBut.Name = "sendBut";
            this.sendBut.Size = new System.Drawing.Size(193, 34);
            this.sendBut.TabIndex = 6;
            this.sendBut.Text = "Send message";
            this.sendBut.UseVisualStyleBackColor = true;
            this.sendBut.Click += new System.EventHandler(this.sendBut_Click);
            // 
            // ChatWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(543, 386);
            this.Controls.Add(this.sendBut);
            this.Controls.Add(this.messText);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.friendList);
            this.Controls.Add(this.messageList);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "ChatWindow";
            this.Text = "ChatWindow";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.ChatWindow_FormClosing);
            this.Load += new System.EventHandler(this.ChatWindow_Load);
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.ListBox messageList;
        private System.Windows.Forms.ListBox friendList;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox messText;
        private System.Windows.Forms.Button sendBut;
    }
}