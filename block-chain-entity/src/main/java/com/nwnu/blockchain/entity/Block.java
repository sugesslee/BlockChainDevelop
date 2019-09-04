package com.nwnu.blockchain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/15     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/15 10:14
 * @date 2018/10/15 10:14
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    /**
     * 索引
     */
    private int index;
    /**
     * 前一个区块的hash值
     */
    private String previousHash;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 数据，交易数据等
     */
    private String data;
    /**
     * hash值
     */
    private String hash;

    /**
     * 复杂度
     */
    private long nonce;
}
