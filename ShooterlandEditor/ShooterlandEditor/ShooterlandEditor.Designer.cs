namespace ShooterlandEditor
{
    partial class ShooterlandEditor
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
            this.frameTimer = new System.Windows.Forms.Timer(this.components);
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.numericUpDown3 = new System.Windows.Forms.NumericUpDown();
            this.numericUpDown4 = new System.Windows.Forms.NumericUpDown();
            this.label3 = new System.Windows.Forms.Label();
            this.bottomSingleCheck = new System.Windows.Forms.CheckBox();
            this.rightSingleCheck = new System.Windows.Forms.CheckBox();
            this.bottomDoubleCheck = new System.Windows.Forms.CheckBox();
            this.rightDoubleCheck = new System.Windows.Forms.CheckBox();
            this.label4 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown4)).BeginInit();
            this.SuspendLayout();
            // 
            // frameTimer
            // 
            this.frameTimer.Tick += new System.EventHandler(this.Update);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 54);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(94, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Right shooter size:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(276, 19);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(119, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Initial roscoe delay (ms):";
            // 
            // numericUpDown3
            // 
            this.numericUpDown3.Location = new System.Drawing.Point(408, 17);
            this.numericUpDown3.Name = "numericUpDown3";
            this.numericUpDown3.Size = new System.Drawing.Size(120, 20);
            this.numericUpDown3.TabIndex = 4;
            // 
            // numericUpDown4
            // 
            this.numericUpDown4.Location = new System.Drawing.Point(408, 52);
            this.numericUpDown4.Name = "numericUpDown4";
            this.numericUpDown4.Size = new System.Drawing.Size(120, 20);
            this.numericUpDown4.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 19);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(102, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Bottom shooter size:";
            // 
            // bottomSingleCheck
            // 
            this.bottomSingleCheck.AutoSize = true;
            this.bottomSingleCheck.Location = new System.Drawing.Point(120, 18);
            this.bottomSingleCheck.Name = "bottomSingleCheck";
            this.bottomSingleCheck.Size = new System.Drawing.Size(53, 17);
            this.bottomSingleCheck.TabIndex = 11;
            this.bottomSingleCheck.Text = "single";
            this.bottomSingleCheck.UseVisualStyleBackColor = true;
            this.bottomSingleCheck.CheckStateChanged += new System.EventHandler(this.bottomSingleCheck_CheckedChanged);
            // 
            // rightSingleCheck
            // 
            this.rightSingleCheck.AutoSize = true;
            this.rightSingleCheck.Location = new System.Drawing.Point(120, 53);
            this.rightSingleCheck.Name = "rightSingleCheck";
            this.rightSingleCheck.Size = new System.Drawing.Size(53, 17);
            this.rightSingleCheck.TabIndex = 12;
            this.rightSingleCheck.Text = "single";
            this.rightSingleCheck.UseVisualStyleBackColor = true;
            this.rightSingleCheck.CheckStateChanged += new System.EventHandler(this.rightSingleCheck_CheckedChanged);
            // 
            // bottomDoubleCheck
            // 
            this.bottomDoubleCheck.AutoSize = true;
            this.bottomDoubleCheck.Location = new System.Drawing.Point(179, 18);
            this.bottomDoubleCheck.Name = "bottomDoubleCheck";
            this.bottomDoubleCheck.Size = new System.Drawing.Size(58, 17);
            this.bottomDoubleCheck.TabIndex = 13;
            this.bottomDoubleCheck.Text = "double";
            this.bottomDoubleCheck.UseVisualStyleBackColor = true;
            this.bottomDoubleCheck.CheckStateChanged += new System.EventHandler(this.bottomDoubleCheck_CheckedChanged);
            // 
            // rightDoubleCheck
            // 
            this.rightDoubleCheck.AutoSize = true;
            this.rightDoubleCheck.Location = new System.Drawing.Point(179, 53);
            this.rightDoubleCheck.Name = "rightDoubleCheck";
            this.rightDoubleCheck.Size = new System.Drawing.Size(58, 17);
            this.rightDoubleCheck.TabIndex = 14;
            this.rightDoubleCheck.Text = "double";
            this.rightDoubleCheck.UseVisualStyleBackColor = true;
            this.rightDoubleCheck.CheckStateChanged += new System.EventHandler(this.rightDoubleCheck_CheckedChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(276, 53);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(97, 13);
            this.label4.TabIndex = 15;
            this.label4.Text = "Roscoe delay (ms):";
            // 
            // ShooterlandEditor
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(653, 527);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.rightDoubleCheck);
            this.Controls.Add(this.bottomDoubleCheck);
            this.Controls.Add(this.rightSingleCheck);
            this.Controls.Add(this.bottomSingleCheck);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.numericUpDown4);
            this.Controls.Add(this.numericUpDown3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "ShooterlandEditor";
            this.Text = "Shooterland Editor";
            this.Load += new System.EventHandler(this.ShooterlandEditor_Load);
            this.Paint += new System.Windows.Forms.PaintEventHandler(this.Draw);
            this.MouseClick += new System.Windows.Forms.MouseEventHandler(this.ShooterlandEditor_MouseClick);
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown4)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Timer frameTimer;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.NumericUpDown numericUpDown3;
        private System.Windows.Forms.NumericUpDown numericUpDown4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.CheckBox bottomSingleCheck;
        private System.Windows.Forms.CheckBox rightSingleCheck;
        private System.Windows.Forms.CheckBox bottomDoubleCheck;
        private System.Windows.Forms.CheckBox rightDoubleCheck;
        private System.Windows.Forms.Label label4;
    }
}

