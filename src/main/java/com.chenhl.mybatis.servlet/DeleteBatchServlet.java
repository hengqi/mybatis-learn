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

public class DeleteBatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultModel resultModel;
        PrintWriter out = null;
        try {
            String[] ids = req.getParameterValues("ids");
            if (ids == null || ids.length==0) {
                resultModel = new ResultModel(false, "id不能为空");
            } else {
                MaintainService maintainService = new MaintainService();
                List<Integer> idList = new ArrayList<>();
                Stream.of(ids).forEach(id -> idList.add(Integer.valueOf(id)));
                maintainService.deleteBatch(idList);
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
