package com.chenhl.mybatis.model;

public class ResultModel {

    public static final ResultModel Success = new ResultModel(true,
            "op_success");
    public static final ResultModel NoPrivilege = new ResultModel(false,
            "op_no_privilege"); // 无权限
    public static final ResultModel OpException = new ResultModel(false,
            "op_exception"); // 操作异常
    public static final ResultModel ErrorStateAction = new ResultModel(false,
            "op_error_state_action"); // 当前状态不能执行此操作。
    public static final ResultModel CannotFindBizObj = new ResultModel(false,
            "cannot_find_biz_obj"); // 不能找到对象。
    public static final ResultModel CannotFindAction = new ResultModel(false, "cannot_find_action"); //不能找到当前Action。

    /**
     * 操作编码
     */
    private String operateCode;

    /**
     * 返回值
     */
    private Object retValue;

    /**
     * 操作是否成功
     */
    private boolean isSuccess;

    /**
     * 操作消息
     */
    private String message;

    /**
     * 错误消息，比如说异常
     */
    private String error;

    /**
     * @param isSuccess
     *            初始化是否成功
     */
    public ResultModel(boolean isSuccess, String message, String operateCode,
                        Object retValue, String error) {
        super();
        this.isSuccess = isSuccess;
        this.message = message;
        this.operateCode = operateCode;
        this.retValue = retValue;
        this.error = error;
    }

    /**
     * @param isSuccess
     *            初始化是否成功
     * @param message
     *            初始化消息
     */
    public ResultModel(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
    /**
     * 构造返回数据信息方法
     * @param isSuccess
     * @param retValue
     */

    public ResultModel(boolean isSuccess,Object retValue) {
        this.retValue = retValue;
        this.isSuccess = isSuccess;
    }

    public String getOperateCode() {
        return operateCode;
    }

    public Object getRetValue() {
        return retValue;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public String getError() {
        return error;
    }

    public void setRetValue(Object retValue) {
        this.retValue = retValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
