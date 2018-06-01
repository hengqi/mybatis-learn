package com.chenhl.mybatis.servlet;

//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSON;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.model.ResultModel;
import com.chenhl.mybatis.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel = ResultModel.Success;
        PrintWriter out = null;
        try {
            String commandName = req.getParameter("commandName");
            String description = req.getParameter("description");
            System.out.println("commandName: " + commandName);
            System.out.println("description: " + description);
            resp.setContentType("application/json; charset=utf-8");
            resp.setHeader("pragma", "no-cache");
            resp.setHeader("cache-control", "no-cache");

            out = resp.getWriter();
            QueryService queryService = new QueryService();
            // 查询消息列表并传给页面
            List<Command> commandList = queryService.queryCommandList(commandName, description);
            resultModel.setRetValue(commandList);
        } catch (Exception e) {
            e.printStackTrace();
            resultModel = ResultModel.OpException;
            resultModel.setMessage(e.getMessage());
        }

        String s = JSON.toJSONString(resultModel);
        System.out.println(s);
        out.write(s);
        out.flush();
    }
}
