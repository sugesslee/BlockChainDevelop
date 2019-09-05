package com.nwnu.blockchain.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * ${desc}
 * <pre>
 *  Version         Date            Author          Description
 * ---------------------------------------------------------------------------------------
 *  1.0.0           2018/10/15     redli        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2018/10/15 10:36
 * @date 2018/10/15 10:36
 * @since 1.0.0
 */
@Slf4j
public class CryptoUtil {
    private CryptoUtil() {
    }

    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            log.error("getSHA256 is error{}", e.getMessage());
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
