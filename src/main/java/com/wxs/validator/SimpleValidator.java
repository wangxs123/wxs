package com.wxs.validator;


import com.wxs.exception.ParamValidateException;
import com.wxs.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 数据校验
 *
 * @author wxs
 * @date 2019-05-29 10:18
 */
public class SimpleValidator {

    public static void isBlank(String str, String message) {
        if (StringUtil.isEmpty(str)) {
            throw new ParamValidateException(str, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ParamValidateException(null, message);
        }
    }

    public static void isInvalidEnumValue(Class enumClass , Integer value, String message){
        if(enumClass.isEnum()){
            Object[] enumConstants = enumClass.getEnumConstants();
            Object constant = enumConstants[0];
            try{
                //values method
                Method method = constant.getClass().getDeclaredMethod("values");
                Object[] resultArray = (Object[]) method.invoke(constant);
                //enum instance
                for(Object result : resultArray){
                    Field descField = result.getClass().getDeclaredField("desc");
                    Field keyField = result.getClass().getDeclaredField("key");
                    keyField.setAccessible(true);
                    descField.setAccessible(true);
                    Integer enumValue = keyField.getInt(result);
                    if(value.intValue() == enumValue.intValue()){
                        return ;
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |NoSuchFieldException  e) {
                e.printStackTrace();
                throw new ParamValidateException(value, message);
            }
        }
        throw new ParamValidateException(value, message);
    }
}
