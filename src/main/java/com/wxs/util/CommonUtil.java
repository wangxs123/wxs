package com.wxs.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.UUID;

/**
 * 基本工具类
 *
 * @author wxs
 * @date 2019-05-29 10:25
 */
public class CommonUtil {

    /**
     * 生成去分隔符的UUID(32位)
     *
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        if (!StringUtil.isEmpty(uuid)) {
            uuid = uuid.replace("-", "");
            return uuid;
        }
        return StringUtil.EMPTY;
    }

    /**
     * base64编码
     *
     * @param bytes
     * @return
     */
    public static String base64Encode(byte[] bytes) {
        String str = StringUtil.EMPTY;
        if (bytes.length > 0) {
            str = new BASE64Encoder().encode(bytes);
        }
        return str;
    }

    /**
     * base64解码
     *
     * @param str
     * @return
     */
    public static byte[] base64Decode(String str) {
        byte[] bytes = null;
        try {
            if (!StringUtil.isEmpty(str)) {
                bytes = new BASE64Decoder().decodeBuffer(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
