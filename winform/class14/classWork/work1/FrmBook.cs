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
    public partial class FrmBook : Form
    {
        public FrmBook()
        {
            InitializeComponent();
        }
        private string dataStr;
        private void FrmBook_Load(object sender, EventArgs e)
        {
            dgvBook.AllowUserToAddRows = false;
        }

        private void btnS_Click(object sender, EventArgs e)
        {
            string sql = @"select id '商品编号',name '商品名称',"+
            "price '商品价格' from book where price >=500 "+
            "order by price";
            DataSet ds;
            ds = MySQLHelper.GetDataSet(out string Error,sql);           
            dgvBook.DataSource = ds.Tables[0];
            foreach (DataRow dr in ds.Tables[0].Rows) {
                dataStr += dr["商品名称"].ToString()+","+dr["商品价格"].ToString()+"\n";
            }            
        }

        private void btnStr_Click(object sender, EventArgs e)
        {
            FrmString frm = new FrmString(dataStr);
            frm.Show();
        }
    }
}
