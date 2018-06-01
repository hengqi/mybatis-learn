package com.chenhl.mybatis.service;

import com.chenhl.mybatis.dao.CommandContentDao;
import com.chenhl.mybatis.dao.CommandDao;
import com.chenhl.mybatis.dao.MessageDao;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.domain.CommandContent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 维护相关的业务功能
 */
public class MaintainService {

    /**
     * 单条删除 --逻辑删除
     */
    public void deleteOne(Integer id) {
        CommandContentDao commandContentDao = new CommandContentDao();
        commandContentDao.deleteOne(id);
    }

    /**
     * 批量删除 --逻辑删除
     */
    public void deleteBatch(List<Integer> ids) {
        CommandContentDao commandContentDao = new CommandContentDao();
        commandContentDao.deleteBatch(ids);
    }

    /**
     * 新增方法
     * @param commandName
     * @param description
     * @param contents
     */
    public void save(String commandName, String description, String[] contents) {
        Command command = new Command();
        command.setCommandName(commandName);
        command.setDescription(description);

        CommandDao commandDao = new CommandDao();
        commandDao.save(command, contents);
    }

    public Command selectCommandByContentId(Integer contentid) {
        CommandDao commandDao = new CommandDao();
        Command command = commandDao.selectCommandByContentId(contentid);
        return command;
    }

    public void update(String id, String commandName, String description, String content, String contentId) {
        CommandDao commandDao = new CommandDao();
        Command command = new Command();
        command.setId(Integer.valueOf(id));
        command.setCommandName(commandName);
        command.setDescription(description);
        commandDao.update(command, content, contentId);
    }
}
