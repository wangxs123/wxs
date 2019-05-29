package com.wxs;

import com.wxs.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 程序入口
 * @author wxs
 * @date
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class WxsApplication {

    /**
     * 日志
     */
    private static Logger log = LogUtil.get();
    public static void main(String[] args) {
        SpringApplication.run(WxsApplication.class, args);
        log.info("wxs服务启动了。。。。。");

    }

}
