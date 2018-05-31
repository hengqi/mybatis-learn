package com.chenhl.mybatis.dao;

import com.chenhl.mybatis.db.DBAccess;
import com.chenhl.mybatis.domain.CommandContent;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommandContentDao {


    public int deleteOne(Integer id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        int v=0;
        try {
            sqlSession = dbAccess.getSqlSession();
            v = sqlSession.update("CommandContent.deleteOne", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return v;
    }

    public int deleteBatch(List<Integer> ids) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        int v=0;
        try {
            sqlSession = dbAccess.getSqlSession();
            v = sqlSession.update("CommandContent.deleteBatch", ids);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return v;
    }

    public void saveBatch(List<CommandContent> commandContentList) {
        DBAccess dbAccess = new DBAccess();
        // mybatis查询不到结果也不会返回null,而是返回一个空的集合ArrayList
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.insert("CommandContent.saveBatch", commandContentList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
