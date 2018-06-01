package com.chenhl.mybatis.servlet;

import com.alibaba.fastjson.JSON;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.model.ResultModel;
import com.chenhl.mybatis.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditUIServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel;
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        PrintWriter out = resp.getWriter();
        try {
            String contentid = req.getParameter("contentid");
            if (null == contentid || "".equals(contentid.trim())) {
                resultModel = new ResultModel(false, "id不能为空");
                out.write(JSON.toJSONString(resultModel));
                out.flush();
            }

            MaintainService maintainService = new MaintainService();
            Command command = maintainService.selectCommandByContentId(Integer.valueOf(contentid));
            System.out.println(JSON.toJSONString(command));

            resultModel = ResultModel.Success;
            resultModel.setRetValue(command);
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
