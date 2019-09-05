package com.nwnu.blockchain.common.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/15     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/15 15:43
 * @date 2018/10/15 15:43
 * @since 1.0.0
 */
@Data
public class Message implements Serializable {
    /**
     * 类型
     * QUERY_LATEST=0;
     * QUERY_ALL=1;
     * RESPONSE_BLOCKCHAIN=2;
     */
    private int type;
    /**
     * 数据
     */
    private String data;

    public Message() {
    }

    public Message(int type) {
        this.type = type;
    }

    public Message(int type, String data) {
        this.type = type;
        this.data = data;
    }

}
