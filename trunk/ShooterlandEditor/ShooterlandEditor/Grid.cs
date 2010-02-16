using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace ShooterlandEditor
{
    public class Grid
    {
        private int[,] _tiles;
        private int _x, _y, _size;

        public Grid(int x, int y)
        {
            _x = x;
            _y = y;
            _size = Constants.GridSize * 40;
            _tiles = new int[Constants.GridSize, Constants.GridSize];
        }

        public float X { get; set; }
        public float Y { get; set; }

        public void Draw(Graphics g)
        {
            for (int i = 0; i <= Constants.GridSize; i++)
                g.DrawLine(Pens.Black, new Point(_x + i * 40, _y), new Point(_x + i * 40, _y + _size));

            for (int j = 0; j <= Constants.GridSize; j++)
                g.DrawLine(Pens.Black, new Point(_x, _y + j * 40), new Point(_x + _size, _y + j * 40));


            //for (int i = 0; i < Constants.GridSize; i++)
            //{
            //    for (int j = 0; j < Constants.GridSize; j++)
            //    {
            //        if (_tiles[i,j] != -1)
            //        {
            //            g.DrawImage(Utils.GetBitmap(_tiles[i, j]), 0.0f, 0.0f);
            //        }
            //    }
            //}
        }


    }
}
