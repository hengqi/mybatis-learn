package com.chenhl.mybatis.dao;

import com.chenhl.mybatis.db.DBAccess;
import com.chenhl.mybatis.domain.Command;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommandDao {

    public List<Command> queryCommandList(String name, String description) {
        DBAccess dbAccess = new DBAccess();
        // mybatis查询不到结果也不会返回null,而是返回一个空的集合ArrayList
        List<Command> commandList = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            Command command = new Command();
            command.setCommandName(name);
            command.setDescription(description);
            // 通过sqlSession执行SQL语句
            commandList = sqlSession.selectList("Command.queryCommandList", command);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return commandList;
    }
}
