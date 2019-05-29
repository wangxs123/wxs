package com.wxs.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 检查某个值是不是在指定的Enum中
 *
 * @author wxs
 * @date 2019-05-29 10:00
 */
@Constraint(validatedBy = EnumCheckValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumCheck {

    String className() default "";

    String message() default "不是合法的值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

