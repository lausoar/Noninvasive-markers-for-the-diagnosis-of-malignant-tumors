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

@WebServlet(value = "/analysisAll")

public class AnalysisServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String source = req.getParameter("source") == null ? "" : req.getParameter("source");
        String markersMethods = req.getParameter("markersMethods");
        String diseasesMethods = req.getParameter("diseasesMethods");
        String marker = req.getParameter("marker");
        String disease = req.getParameter("disease");

        List<Map<String,Object>> maps = new ArrayList<>();
        String sql = "select * from image where marker like ? and disease like ? and source like ? and methods_of_mirnas like ? and markers_methods like ?";

        Object[] args = new Object[5];
        args[0] = "%" + marker + "%";
        args[1] = "%" + disease + "%";
        args[2] = "%" + source + "%";
        args[3] = "%" + diseasesMethods + "%";
        args[4] = "%" + markersMethods + "%";

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
