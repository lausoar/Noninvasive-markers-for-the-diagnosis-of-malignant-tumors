package com.medical.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
    private static final String CON_URL="jdbc:mysql://101.200.59.13:3306/hcnma?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false";
    private static final String USER_NAME="hcnma";
    private static final String PASS_WORD="123456";

    private Connection con=null;
    private PreparedStatement preparedStatement =null;
    private ResultSet resultSet = null;


    //注册驱动，获取连接
    private void init(String sql,Object ...args) throws ClassNotFoundException, SQLException {

        try {

            //注册驱动
            Class.forName(DRIVER_NAME);
            //获取连接
            this.con = DriverManager.getConnection(CON_URL,USER_NAME,PASS_WORD);
            //获取操作对象，预编译
            this.preparedStatement = this.con.prepareStatement(sql);
            //设置参数
            if(args!=null) {
                for (int index = 0; index < args.length; index++)
                    this.preparedStatement.setObject(index + 1, args[index]);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //传sql和获取操作对象，预编译
    public List<Map<String, Object>> select(String sql, Object... args){
        try {
            this.init(sql,args);
            this.resultSet = this.preparedStatement.executeQuery();
            //处理返回数据
            List< Map<String,Object> > list = new ArrayList<>();
            //1、他有多少列。2、他每列叫啥
            ResultSetMetaData metaData = this.resultSet.getMetaData();
            //列数
            int columnCount = metaData.getColumnCount();
            //对结果集进行每行遍历
            while (this.resultSet.next()){
                //对结果集进行每列遍历
                Map<String,Object> map = new HashMap<>();
                for (int col = 1 ; col<=columnCount ; col++){
                    //获取每列的名称
                    String columnName = metaData.getColumnName(col);
                    map.put(columnName,resultSet.getObject(col));
                }
                list.add(map);
            }

            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关连接
            this.closeAll();
        }
    }
    public int update(String sql,Object... args){
        try {
            this.init(sql,args);
            int flag = this.preparedStatement.executeUpdate();
            //处理返回数据
            return flag;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            //关连接
            this.closeAll();
        }
    }

    private void closeAll(){
        try {
            if(this.resultSet!=null) resultSet.close();
            if(this.preparedStatement!=null) this.preparedStatement.close();
            if(this.con!=null) this.con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String,Object>> exeQuery(String sql,Object... args){
        DBUtil util = new DBUtil();
        return util.select(sql,args);
    };
    public static int exeUpdate(String sql,Object ...args){
        DBUtil util = new DBUtil();
        return util.update(sql,args);
    }

}
