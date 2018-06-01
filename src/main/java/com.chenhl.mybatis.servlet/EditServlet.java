package com.chenhl.mybatis.servlet;

import com.alibaba.fastjson.JSON;
import com.chenhl.mybatis.model.ResultModel;
import com.chenhl.mybatis.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel;
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        PrintWriter out = resp.getWriter();
        try {
            String id = req.getParameter("id");
            String commandName = req.getParameter("commandName");
            String description = req.getParameter("description");
            String[] contents = req.getParameterValues("contents");
            if (null == commandName || "".equals(commandName.trim())) {
                resultModel = new ResultModel(false, "指令名称不能为空");
                out.write(JSON.toJSONString(resultModel));
                out.flush();
            }
            if (null == description || "".equals(description.trim())) {
                resultModel = new ResultModel(false, "描述不能为空");
                out.write(JSON.toJSONString(resultModel));
                out.flush();
            }
            if (null == contents || 0 == contents.length) {
                resultModel = new ResultModel(false, "内容不能为空");
                out.write(JSON.toJSONString(resultModel));
                out.flush();
            }
            // 新增
            if (null == id || "".equals(id)) {
                MaintainService maintainService = new MaintainService();
                maintainService.save(commandName, description, contents);
                resultModel = ResultModel.Success;
            }
            // 修改
            else {
                resultModel = ResultModel.Success;
            }

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
