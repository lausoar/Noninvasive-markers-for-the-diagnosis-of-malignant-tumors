package com.medical.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.medical.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns  = "/selectAll")

public class TextServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
     //   String column = req.getParameter("column");
     //   String Filtrate = req.getParameter("Filtrate")==null ? "" : req.getParameter("Filtrate");
        String marker = req.getParameter("marker");
        String ExperimentalMethod = req.getParameter("ExperimentalMethod");


//        String value = req.getParameter(column);
//        String sql = String.format("select * from sheet1 where %s like %s ",column,"? ");
//        String param = "%"+value+"%";
//        System.out.println(param);
//        List<Map<String,Object>> maps = DBUtil.exeQuery(sql,new Object[]{param});
//        JSONObject json = new JSONObject();
//        if(maps!=null && maps.size()!=0)
//            json.put("result",maps.get(0));
//
//        resp.getWriter().print(json);
//    }
        List<Map<String,Object>> maps = new ArrayList<>();
        String sql = "select * from sheet1 where Name like ? and Experimental_method like ?";

        Object[] args = new Object[2];
        args[0] = "%" + marker + "%";
        args[1] = "%" + ExperimentalMethod + "%";


        maps = DBUtil.exeQuery(sql,args);

        System.out.println(maps);
        JSONObject json = new JSONObject();
        if (maps.size() == 0) {
            json.put("result", null);
        } else {
            Map<String, Object> oneData = maps.get(0);
            json.put("result", oneData);
        }
        resp.getWriter().print(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
