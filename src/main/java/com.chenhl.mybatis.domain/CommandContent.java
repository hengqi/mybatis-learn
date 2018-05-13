package com.chenhl.mybatis.domain;

public class CommandContent {

    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 关联的指令表主键
     */
    private Integer commandId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }
}
