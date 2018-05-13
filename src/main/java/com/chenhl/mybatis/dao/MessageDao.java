package com.chenhl.mybatis.dao;

import com.chenhl.mybatis.db.DBAccess;
import com.chenhl.mybatis.domain.Message;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class MessageDao {

    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(String command, String description) {
        DBAccess dbAccess = new DBAccess();
        // mybatis查询不到结果也不会返回null,而是返回一个空的集合ArrayList
        List<Message> messageList = null;
        SqlSession sqlSession = null;
        try {
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            messageList = sqlSession.selectList("Message.queryMessageList", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 单条删除
     */
    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            sqlSession.delete("Message.deleteOne", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }


    /**
     * 单条删除
     */
    public void deleteBatch(List<Integer> ids) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            sqlSession.delete("Message.deleteBatch", ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
