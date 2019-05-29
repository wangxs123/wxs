package com.wxs.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author wxs
 * @date 2019-05-29 10:34
 */
public class ReflectUtil {

    private ReflectUtil() {

    }

    /**
     * 根据对象，返回一个class对象，用于获取方法
     *
     * @param obj 对象
     * @return class对象
     */
    public static Class<?> getClass(Object obj) throws ClassNotFoundException {
        return Class.forName(obj.getClass().getName());
    }

    /**
     * 根据对象，获取某个方法
     *
     * @param obj            对象
     * @param methodName     方法名
     * @param parameterTypes 该方法需传的参数类型，如果不需传参，则不传
     * @return 方法
     */
    public static Method getMethod(Object obj, String methodName, Class<?>... parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        Method method = getClass(obj).getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    /**
     * 根据class对象，获取某个方法
     *
     * @param cls            class对象
     * @param methodName     方法名
     * @param parameterTypes 该方法需传的参数类型，如果不需传参，则不传
     * @return 方法
     */
    public static Method getMethod(Class<?> cls, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = cls.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    /**
     * 直接传入对象、方法名、参数，即可使用该对象的隐藏方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param parameter  可变参数
     * @return 对象
     */
    public static Object invoke(Object obj, String methodName, Object... parameter) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Class<?>[] types = new Class<?>[parameter.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = parameter[i].getClass();
        }
        return getMethod(obj, methodName, types).invoke(obj, parameter);
    }

    /**
     * 直接传入类名、方法名、参数，即可使用该对象的隐藏静态方法
     *
     * @param cls        Class对象
     * @param methodName 方法名
     * @param parameter  可变参数
     * @return 对象
     */
    public static Object invoke(Class<?> cls, String methodName, Object... parameter) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?>[] parameterTypes = new Class<?>[parameter.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterTypes[i] = parameter[i].getClass();
        }
        return getMethod(cls, methodName, parameterTypes).invoke(cls.newInstance(), parameter);
    }
}
