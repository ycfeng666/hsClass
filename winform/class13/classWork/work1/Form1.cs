using System;
using System.Windows.Forms;

namespace work1
{
    public partial class frmStu : Form
    {
        public frmStu()
        {
            InitializeComponent();
        }
        public void Select(string sql) {
            dgbStu.DataSource = MySQLHelper.GetDataTable(out string sError, sql);
        }
        private void frmStu_Load(object sender, EventArgs e)
        {
            dgbStu.AllowUserToAddRows = false;
            string sql = @"select * from stu";
            Select(sql);
        }

        private void btnDel_Click(object sender, EventArgs e)
        {
            if (dgbStu.SelectedRows.Count == 1) {
                int id = int.Parse(dgbStu.CurrentRow.Cells["id"].Value.ToString());
                DialogResult dr = MessageBox.Show("确认要删除？", "确认", MessageBoxButtons.OKCancel);
                if (dr == DialogResult.OK) {
                    string sql = @"delete from stu where id = "+id;
                    bool r = MySQLHelper.UpdateData(out string sError, sql);
                    if (r) {
                        string sql2 = @"select * from stu";
                        Select(sql2);
                    } 

                }
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            FrmAddEdit frm = new FrmAddEdit(0,this);
            frm.Show();
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (dgbStu.SelectedRows.Count == 1) {
                int id = int.Parse(dgbStu.CurrentRow.Cells["id"].Value.ToString());
                FrmAddEdit frm = new FrmAddEdit(id, this);
                frm.Show();
            }
        }
    }
}
