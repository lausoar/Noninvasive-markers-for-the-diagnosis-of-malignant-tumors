package com.medical.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestJDBC {
    public static void main(String[] args){
    String sql="select * from sheet1";
        List<Map<String, Object>> maps = DBUtil.exeQuery(sql, null);
        System.out.println(maps);
    }
}
