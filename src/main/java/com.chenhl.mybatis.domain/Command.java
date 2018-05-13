package com.chenhl.mybatis.domain;

import java.util.List;

public class Command {

    private Integer id;
    /**
     * 指令名称
     */
    private String commandName;
    /**
     * 描述
     */
    private String description;

    /**
     * 一条指令对应的自动回复内容列表
     */
    private List<CommandContent> contentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommandContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<CommandContent> contentList) {
        this.contentList = contentList;
    }
}

