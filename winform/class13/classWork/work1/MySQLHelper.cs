using System;
using System.Collections.Generic;

using System.Data;
using MySql.Data.MySqlClient;
using System.Configuration;
using System.Reflection;

namespace work1
{
    // MySql帮助类
    public class MySQLHelper
    {
        public static string ConnString = "";// 这里可以直接输入数据库链接字符串
        public static string Conn_Config_Str_Name = @"ConnString";// 配置文件中定义的Name
        public static string Conn_Server = @"127.0.0.1";
        public static string Conn_DBName = @"info";
        public static string Conn_Uid = @"root";
        public static string Conn_Pwd = @"Root@123456";
        public static string Conn_Ssl = @"None";
        public static string Conn_Char = @"utf8";
        public static string Conn_Key = @"true";

        // 链接
        private static string _ConnString
        {
            get
            {
                // 如果全局变量中定义了数据库连接字符串则直接使用
                if (!string.IsNullOrEmpty(ConnString))
                    return ConnString;

                // 这里是读取App.config数据库配置
                //object oConn = ConfigurationManager.ConnectionStrings[Conn_Config_Str_Name];
                //if (oConn != null && oConn.ToString() != "")
                //    return oConn.ToString();

                // 这里是拼接数据库连接字符串
                return string.Format(@"server={0};database={1};userid={2};password={3};
                    SslMode={4};Charset={5};allowPublicKeyRetrieval={6}", 
                    Conn_Server, Conn_DBName, Conn_Uid, Conn_Pwd,
                    Conn_Ssl, Conn_Char, Conn_Key);
            }
        }
        // 读取数据 datatable
        public static DataTable GetDataTable(out string sError, string sSQL)
        {
            DataTable dt = null;
            sError = string.Empty;

            MySqlConnection myConn = null;
            try
            {
                myConn = new MySqlConnection(_ConnString);
                MySqlCommand myCommand = new MySqlCommand(sSQL, myConn);
                myConn.Open();
                MySqlDataAdapter adapter = new MySqlDataAdapter(myCommand);
                dt = new DataTable();
                adapter.Fill(dt);
                myConn.Close();
            }
            catch (Exception ex)
            {
                sError = ex.Message;
            }
            return dt;
        }
        // 读取数据 dataset
        public static DataSet GetDataSet(out string sError, string sSQL)
        {
            DataSet ds = null;
            sError = string.Empty;

            MySqlConnection myConn = null;
            try
            {
                myConn = new MySqlConnection(_ConnString);
                MySqlCommand myCmd = new MySqlCommand(sSQL, myConn);
                myConn.Open();
                MySqlDataAdapter adapter = new MySqlDataAdapter(myCmd);
                ds = new DataSet();
                adapter.Fill(ds);
                myConn.Close();
            }
            catch (Exception ex)
            {
                sError = ex.Message;
            }
            return ds;
        }
        // 取最大的ID
        public static int GetMaxID(out string sError, string sKeyField, string sTableName)
        {
            DataTable dt = GetDataTable(out sError, "select IFNULL(max(" + sKeyField + "),0) as MaxID from " + sTableName);
            if (dt != null && dt.Rows.Count > 0)
            {
                return Convert.ToInt32(dt.Rows[0][0].ToString());
            }

            return 0;
        }
        // 取行数
        public static int GetCount(out string sError, string sTableName) {
            DataTable dt = GetDataTable(out sError, "select count(*) as count " + sTableName);
            if (dt != null && dt.Rows.Count > 0)
            {
                return Convert.ToInt32(dt.Rows[0][0].ToString());
            }

            return 0;
        }
        // 插入，修改，删除，是否使用事务
        public static bool UpdateData(out string sError, string sSQL, bool bUseTransaction = false)
        {
            int iResult = 0;
            sError = string.Empty;

            MySqlConnection myConn = null;

            if (!bUseTransaction)
            {
                try
                {
                    myConn = new MySqlConnection(_ConnString);
                    MySqlCommand myCmd = new MySqlCommand(sSQL, myConn);
                    myConn.Open();
                    iResult = myCmd.ExecuteNonQuery();
                    myConn.Close();
                }
                catch (Exception ex)
                {
                    sError = ex.Message;
                    iResult = -1;
                }
            }
            else // 使用事务
            {
                MySqlTransaction myTrans = null;
                try
                {
                    myConn = new MySqlConnection(_ConnString);
                    myConn.Open();
                    myTrans = myConn.BeginTransaction();
                    MySqlCommand myCmd = new MySqlCommand(sSQL, myConn);
                    myCmd.Transaction = myTrans;
                    iResult = myCmd.ExecuteNonQuery();
                    myTrans.Commit();
                    myConn.Close();
                }
                catch (Exception ex)
                {
                    sError = ex.Message;
                    iResult = -1;
                    myTrans.Rollback();
                }
            }

            return iResult > 0;
        }
        // dataTable转属性,反射实现
        public static List<T> DataTableToT<T>(DataTable source) where T : class, new()
        {
            List<T> itemlist = null;
            if (source == null || source.Rows.Count == 0)
            {
                return itemlist;
            }
            itemlist = new List<T>();
            T item;
            Type targettype = typeof(T);
            Type ptype;
            object value;
            foreach (DataRow dr in source.Rows)
            {
                item = new T();
                foreach (PropertyInfo pi in targettype.GetProperties())
                {
                    if (pi.CanWrite && source.Columns.Contains(pi.Name))
                    {
                        ptype = Type.GetType(pi.PropertyType.FullName);
                        value = Convert.ChangeType(dr[pi.Name], ptype);
                        pi.SetValue(item, value, null);
                    }
                }
                itemlist.Add(item);
            }
            return itemlist;
        }
        // dataRow转属性,反射实现
        public static T DataRowToT<T>(DataRow source) where T : class, new()
        {
            T item = null;
            if (source == null)
            {
                return item;
            }
            item = new T();
            Type targettype = typeof(T);
            Type ptype;
            Object value;

            foreach (PropertyInfo pi in targettype.GetProperties())
            {
                if (pi.CanWrite && source.Table.Columns.Contains(pi.Name))
                {
                    ptype = Type.GetType(pi.PropertyType.FullName);
                    value = Convert.ChangeType(source[pi.Name], ptype);
                    pi.SetValue(item, value, null);
                }
            }
            return item;
        }
    }
}
