package com.wxs;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 注册启动类(用于部署外部tomcat)
 *
 * @author wxs
 * @date 2019-05-29 10:40
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WxsApplication.class);
    }
}
