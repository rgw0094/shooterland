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
        private Grid _grid;
        private TileSelector _tileSelector;

        public ShooterlandEditor()
        {
            InitializeComponent();
            _grid = new Grid(25, 100);
            _tileSelector = new TileSelector(440, 100);
            rightSingleCheck.Checked = true;
            bottomSingleCheck.Checked = true;
            MaximumSize = MinimumSize = Size;
            MaximizeBox = false;

            DoubleBuffered = true;
            frameTimer.Interval = 1000 / 40;
            frameTimer.Start();
        }

        private void Update(object sender, EventArgs e)
        {
            Invalidate();
        }

        private void Draw(object sender, PaintEventArgs e)
        {
            _grid.Draw(e.Graphics);
            _tileSelector.Draw(e.Graphics);
        }

        private void ShooterlandEditor_MouseClick(object sender, MouseEventArgs e)
        {
            if (_tileSelector.Contains(e.X, e.Y))
            {
                _tileSelector.onClick(e.X, e.Y, e.Button);
            }
        }

        private void rightDoubleCheck_CheckedChanged(object sender, EventArgs e)
        {
            if (rightDoubleCheck.Checked)
                rightSingleCheck.Checked = false;
        }

        private void rightSingleCheck_CheckedChanged(object sender, EventArgs e)
        {
            if (rightSingleCheck.Checked)
                rightDoubleCheck.Checked = false;
        }

        private void bottomDoubleCheck_CheckedChanged(object sender, EventArgs e)
        {
            if (bottomDoubleCheck.Checked)
                bottomSingleCheck.Checked = false;
        }

        private void bottomSingleCheck_CheckedChanged(object sender, EventArgs e)
        {
            if (bottomSingleCheck.Checked)
                bottomDoubleCheck.Checked = false;
        }

        private void ShooterlandEditor_Load(object sender, EventArgs e)
        {

        }

        
    }
}
