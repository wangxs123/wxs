package com.wxs.advice;

import com.wxs.exception.ParamValidateException;
import com.wxs.exception.TransactionException;
import com.wxs.util.JacksonUtil;
import com.wxs.util.LogUtil;
import com.wxs.util.StringUtil;
import common.enums.ResultCodeEnum;
import common.response.Result;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wxs
 * @date 2019/5/29 9:18
 **/
public class ExceptionHandlerAdvice implements ResponseBodyAdvice {

    private ThreadLocal<Object> modelHolder = new ThreadLocal<>();

    /**
     * 日志
     */
    private static Logger log = LogUtil.get();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        log.error(tips, e);
        return new Result(ResultCodeEnum.REQUEST_PARAM_ERROR, tips);
    }

    @ExceptionHandler(ParamValidateException.class)
    public Result handleIllegalParamException(ParamValidateException e) {
        String tips = "缺少请求参数";
        if (!StringUtil.isEmpty(e.getMessage())) {
            tips = e.getMessage();
        }
        log.error(tips, e);
        return new Result(ResultCodeEnum.REQUEST_PARAM_LACK, tips);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("uri={} | requestBody={}", request.getRequestURI(), JacksonUtil.toJSon(modelHolder.get()), e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return new Result(ResultCodeEnum.SYSTEM_EXCEPTION, "Http请求Method不对，请查看接口文档！");
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            return new Result(ResultCodeEnum.SYSTEM_EXCEPTION, "Http请求Content type不对，请查看接口文档！");
        } else if (e instanceof TransactionException) {
            return new Result(((TransactionException) e).getCode(), e.getMessage());
        }
        return new Result(ResultCodeEnum.UNKNOWN_ERROR, "");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // ModelHolder 初始化
        modelHolder.set(webDataBinder.getTarget());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // ModelHolder 清理
        modelHolder.remove();
        return body;
    }

}
