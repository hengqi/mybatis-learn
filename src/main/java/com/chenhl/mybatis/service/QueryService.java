package com.chenhl.mybatis.service;

import com.chenhl.mybatis.dao.CommandDao;
import com.chenhl.mybatis.dao.MessageDao;
import com.chenhl.mybatis.domain.Command;
import com.chenhl.mybatis.domain.CommandContent;
import com.chenhl.mybatis.domain.Message;
import com.chenhl.mybatis.util.Iconst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class QueryService {

    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);


//    public List<Message> queryMessageList(String command, String description) {
//        logger.info("查询列表开始：command:{}, description:{}", command, description);
//        MessageDao messageDao = new MessageDao();
//        return messageDao.queryMessageList(command, description);
//    }

    public List<Command> queryCommandList(String command, String description) {
        logger.info("查询列表开始：command:{}, description:{}", command, description);
        CommandDao commandDao = new CommandDao();
        return commandDao.queryCommandList(command, description);
    }

    /**
     * 通过指令查询自动回复的内容
     * @param command 指令
     * @return 自动回复的内容
     */
    public String queryByCommand(String command) {
        CommandDao commandDao = new CommandDao();
        List<Command> commandList;
        if(Iconst.HELP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(null, null);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < commandList.size(); i++) {
                if(i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + commandList.get(i).getCommandName() + "]可以查看" + commandList.get(i).getDescription());
            }
            return result.toString();
        }
        commandList = commandDao.queryCommandList(command, null);
        if(commandList.size() > 0) {
            List<CommandContent> commandContentList = commandList.get(0).getContentList();
            int i = new Random().nextInt(commandContentList.size());
            return commandContentList.get(i).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }
}
