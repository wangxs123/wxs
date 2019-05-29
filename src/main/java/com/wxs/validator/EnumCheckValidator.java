package com.wxs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 检查某个值是不是在指定的Enum中
 *
 * @author wxs
 * @date 2019-05-29 10:08
 */
public class EnumCheckValidator implements ConstraintValidator<EnumCheck, Object> {

    private EnumCheck enumCheck;

    @Override
    public void initialize(EnumCheck enumCheck) {
        this.enumCheck = enumCheck;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext){

        if(value == null){
            return true;
        }
        Integer intValue = null;
        if(value instanceof  Byte){
            intValue = ((Byte) value).intValue();
        }else if(value instanceof Integer){
            intValue = (Integer) value;
        }
        try {
            Class enumClass = Class.forName(enumCheck.className());
            if (enumClass.isEnum()) {
                Object[] enumConstants = enumClass.getEnumConstants();
                Object constant = enumConstants[0];
                //values method
                Method method = constant.getClass().getDeclaredMethod("values");
                Object[] resultArray = (Object[]) method.invoke(constant);
                //enum instance
                for(Object result : resultArray){
                    Field keyField = result.getClass().getDeclaredField("key");
                    Field descField = result.getClass().getDeclaredField("desc");
                    keyField.setAccessible(true);
                    descField.setAccessible(true);
                    Integer enumValue = keyField.getInt(result);
                    if(enumValue.equals(intValue)){
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }
}
