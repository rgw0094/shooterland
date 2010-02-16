using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ShooterlandEditor
{
    public partial class ShooterlandEditor : Form
    {
        public ShooterlandEditor()
        {
            InitializeComponent();
            Graphics.init();
        }

        private void Update(object sender, EventArgs e)
        {

        }
    }
}
