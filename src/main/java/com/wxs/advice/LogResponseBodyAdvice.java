package com.wxs.advice;

import com.wxs.util.JacksonUtil;
import com.wxs.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应log
 *
 * @author wxs
 * @date 2019/5/29 9:36
 */
@ControllerAdvice
public class LogResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 日志
     */
    private static Logger log = LogUtil.get();

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (log.isInfoEnabled()) {
            log.info("接口uri={} | 返回实体={}", request.getURI(), JacksonUtil.toJSon(body));
        }
        return body;
    }
}
