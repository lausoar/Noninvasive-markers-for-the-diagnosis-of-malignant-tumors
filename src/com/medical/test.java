package com.medical;

import com.medical.util.DBUtil;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        // 查询sheet1表
        String sqlQuery = "select id, Experimental_method from sheet1";
        List<Map<String, Object>> result = DBUtil.exeQuery(sqlQuery);  // 执行查询，result是查询结果

        for (int i = 0; i < result.size(); i++) {
            // 取出result中的一个map（"id": "", "Experimental_method": ""）
            Map<String, Object> item = result.get(i);
            // 取出item里Experimental_method的内容(目前是字符串)
            String exStr = (String) item.get("Experimental_method");
            // 如果Experimental_method里面是空的，就跳过
            if (exStr == "") {
                continue;
            }
            // 将Experimental_method的内容转换成数组（因为字符串是以逗号分割的，所以参数写","）
            String[] exArr = exStr.split(",");

            // 遍历Experimental_method数组，分别写sql语句插入到sheet3中
            for (int j = 0; j < exArr.length; j++) {
                // 取出Experimental_method数组中的一个值（trim的意思是去掉头尾空格）
                String ex = exArr[j].trim();
                // 插入的sql语句（?是占位符，表示这里有要填充的东西）
                String sql = "insert into sheet3 values (null, ?, ?)";

                // 把要填充到?里的东西放进一个数组中
                Object[] arg = new Object[2];
                arg[0] = item.get("id");
                arg[1] = ex;

                // 把sql语句和填充数组放进执行sql语句的方法，进行一条语句的添加
                DBUtil.exeUpdate(sql, arg);
            }
//            if (i == 1) {
//                break;
//            }
        }
        System.out.println("结束");
    }
}
