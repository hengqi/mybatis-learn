package com.chenhl.mybatis.servlet;

import com.alibaba.fastjson.JSON;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.model.ResultModel;
import com.chenhl.mybatis.service.MaintainService;
import com.chenhl.mybatis.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DeleteOneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ResultModel resultModel;
        PrintWriter out = null;
        try {
            String id = req.getParameter("id");
            if (id == null || "".equals(id.trim())) {
                resultModel = new ResultModel(false, "id不能为空");
            } else {
                MaintainService maintainService = new MaintainService();
                maintainService.deleteOne(Integer.valueOf(id));
                resultModel = new ResultModel(true, "删除成功");
            }
            resp.setContentType("application/json; charset=utf-8");
            resp.setHeader("pragma", "no-cache");
            resp.setHeader("cache-control", "no-cache");
            out = resp.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = ResultModel.OpException;
            resultModel.setMessage(e.getMessage());
        }
        String s = JSON.toJSONString(resultModel);
        out.write(s);
        out.flush();
    }
}
