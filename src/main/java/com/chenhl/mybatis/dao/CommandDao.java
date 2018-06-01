package com.chenhl.mybatis.dao;

import com.chenhl.mybatis.db.DBAccess;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.domain.CommandContent;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CommandDao {

    public int save(Command command,String[] contents) {
        DBAccess dbAccess = new DBAccess();
        // mybatis查询不到结果也不会返回null,而是返回一个空的集合ArrayList
        SqlSession sqlSession = null;
        int v=0;
        try {
            sqlSession = dbAccess.getSqlSession();
            v = sqlSession.insert("Command.save", command);
            List<CommandContent> commandContentList = new ArrayList<>();
            Stream.of(contents).forEach(content -> {
                CommandContent commandContent = new CommandContent();
                commandContent.setContent(content);
                commandContent.setCommandId(command.getId());
                commandContentList.add(commandContent);
            });
            sqlSession.insert("CommandContent.saveBatch", commandContentList);
//            CommandContentDao commandContentDao = new CommandContentDao();
//            commandContentDao.saveBatch(commandContentList);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return v;
    }

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
