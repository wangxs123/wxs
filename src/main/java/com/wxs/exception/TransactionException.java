package com.wxs.exception;


import common.enums.ResultCodeEnum;

/**
 * 事务层异常
 *
 * @author wxs
 * @date 2019/5/29 8:40
 */
public class TransactionException extends RuntimeException {

    private static final long serialVersionUID = 8604424364318396626L;


    /**
     * 异常代码
     */
    private ResultCodeEnum code;

    /**
     * 异常说明
     */
    private String desc;


    public TransactionException() {
        super();
    }


    public TransactionException(String message) {
        super(message);
        this.desc = message;
    }


    public TransactionException(ResultCodeEnum code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public TransactionException(ResultCodeEnum code, String desc, Throwable cause) {
        super(cause);
        this.code = code;
        this.desc = desc;
    }


    public TransactionException(ResultCodeEnum code, String desc, String message) {
        super(message);
        this.code = code;
        this.desc = desc;
    }


    public ResultCodeEnum getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }


    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return desc;
        }
        return super.getMessage();
    }
}
