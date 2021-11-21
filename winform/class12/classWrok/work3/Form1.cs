using System;
using System.Windows.Forms;

namespace work3
{
public partial class FormList : Form
{
    public FormList()
    {
        InitializeComponent();
    }
    public void SetTable() {
        ListView lv = lvStu;
        lv.Clear();
        lv.Groups.Clear();
        lv.ShowGroups = false;
        lv.View = View.Details;
        string[,] arrList = { { "001", "张三", "99" }, { "002", "李四", "59" } };
        lv.Columns.Add("学号", 100, HorizontalAlignment.Center);
        lv.Columns.Add("姓名", 100, HorizontalAlignment.Center);
        lv.Columns.Add("成绩", 100, HorizontalAlignment.Center);
        lv.BeginUpdate();
        for (int i = 0; i < 2; i++)
        {
            ListViewItem lt = new ListViewItem();
            for (int j = 0; j < 3; j++)
            {
                if (j == 0) lt.Text = arrList[i, j];
                else lt.SubItems.Add(arrList[i, j]);
            }
            lv.Items.Add(lt);
        }
        lv.EndUpdate();
    }
    public void SetGroup() {
        // 这里有Bug，暂未解决，分组正常显示，但是内容无法显示
        // 调试与监视均没有发现问题
        ListView lv = lvStu;
        lv.Items.Clear();
        lv.Clear();
        lv.ShowGroups = true;
        ListViewGroup menGroup = new ListViewGroup
        {
            Header = "男生"
        };
        ListViewGroup woMenGroup = new ListViewGroup
        {
            Header = "女生"
        };
        lv.Groups.Add(menGroup);
        lv.Groups.Add(woMenGroup);
        string[] arrList = { "喜羊羊", "灰太狼", "美羊羊", "红太狼" };
        for (int i = 0; i < arrList.Length; i++)
        {
            lv.Items.Add(arrList[i]);
            if (i < 2) lv.Items[i].Group = lv.Groups[0];
            else lv.Items[i].Group = lv.Groups[1];
        }
    }
    private void FormList_Load(object sender, EventArgs e)
    {
        SetTable();
    }

    private void btnGroup_Click(object sender, EventArgs e)
    {
        SetGroup();
    }

    private void btnTable_Click(object sender, EventArgs e)
    {
        SetTable();
    }
}
}
