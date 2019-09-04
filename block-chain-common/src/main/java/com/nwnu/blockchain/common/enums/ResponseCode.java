package com.nwnu.blockchain.common.enums;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/13     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/13 13:00
 * @date 2018/10/13 13:00
 * @since 1.0.0
 */
public enum ResponseCode {
    /**
     * 0代表SUCCESS
     * 1代表ERROR
     * 10代表需要登录
     */
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    /**
     * 构造器
     */
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 将构造器开放
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
