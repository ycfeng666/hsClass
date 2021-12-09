using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace work1
{
    public partial class FrmString : Form
    {
        private string dataStr;
        public FrmString(string str)
        {
            InitializeComponent();
            dataStr = str;
        }

        private void FrmString_Load(object sender, EventArgs e)
        {
            label1.Text = dataStr;
        }
    }
}
