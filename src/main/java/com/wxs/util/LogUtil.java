package com.wxs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志封装类
 *
 * @author wxs
 * @date 2019/5/29 9:15
 */
public class LogUtil {

    private LogUtil() {

    }

    public static Logger get() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[2].getClassName());
    }
}
