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
    public partial class FrmAddEdit : Form
    {
        public FrmAddEdit()
        {
            InitializeComponent();
        }
        private int id;

        private frmStu stu;

        public FrmAddEdit(int id,frmStu stu) {
            InitializeComponent();
            this.id = id;
            this.stu = stu;
        }
        private void FrmAddEdit_Load(object sender, EventArgs e)
        {
            if (id != 0)
            {
                Text = "修改";
                string sql = @"select * from stu where id =" + id;
                DataTable dt = MySQLHelper.GetDataTable(out string error, sql);
                txtName.Text = dt.Rows[0]["name"].ToString();
                numAge.Value = (int)dt.Rows[0]["age"];
            }
            else Text = "新增";
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            string sql = "";
            if (id != 0) {
                sql = @"update stu set name='"
                + txtName.Text + "',age=" + numAge.Value +
                " where id=" + id;
            } else {
                sql = @"insert into stu(`name`,age) values('"
                + txtName.Text + "',"+numAge.Value+")";
            }
            bool r = MySQLHelper.UpdateData(out string sError, sql);
            if (r)
            {
                Close();
                sql = @"select * from stu";
                stu.Select(sql);
            }
            else MessageBox.Show("保存失败！");
        }
    }
}
