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

@WebServlet(value = "/markersAll")

public class MarkersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String disease = req.getParameter("disease");
        String ExperimentalMethod = req.getParameter("ExperimentalMethod");

//        String Filtrate = req.getParameter("Filtrate")==null ? "" : req.getParameter("Filtrate");

        List<Map<String,Object>> maps = new ArrayList<>();
        String sql = "select * from sheet1 where disease like ? and Experimental_method like ? ";

        Object[] args = new Object[2];
        args[0] = "%" + disease + "%";
        args[1] = "%" + ExperimentalMethod + "%";



        maps = DBUtil.exeQuery(sql,args);

        System.out.println(maps);
        JSONObject json = new JSONObject();
        if (maps.size() == 0) {
            json.put("result", null);
        } else {

            json.put("result", maps);
        }
        resp.getWriter().print(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

