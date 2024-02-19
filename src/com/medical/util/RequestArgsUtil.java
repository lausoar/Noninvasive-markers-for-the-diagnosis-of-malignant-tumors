package com.medical.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class RequestArgsUtil {
    public static <T> T transferToBean(HttpServletRequest request, Class<?> clazz){
        try{
            Object obj = clazz.newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields){
                //获取属性名
                String fieldName = field.getName();
                Object value = convertValue(request.getParameter(fieldName), field.getType());
                String setName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                clazz.getMethod(setName,field.getType()).invoke(obj,value);
            }

            return (T) obj;
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object convertValue(String value,Class<?> fieldType){
        Object returnVal = null;
        if(value == null) return null;
        if(  Long.class.getName().equals(fieldType.getName()) || long.class.getName().equals(fieldType.getName()) )
            returnVal = Long.parseLong(value);
        else if( Integer.class.getName().equals(fieldType.getName()) || int.class.getName().equals(fieldType.getName()) )
            returnVal = Integer.parseInt(value);
        else if( Float.class.getName().equals(fieldType.getName()) || float.class.getName().equals(fieldType.getName()) )
            returnVal = Float.parseFloat(value);
        else if( Double.class.getName().equals(fieldType.getName()) || double.class.getName().equals(fieldType.getName()) )
            returnVal = Double.parseDouble(value);
        else if( Date.class.getName().equals(fieldType.getName()))
            returnVal = new Date(value);
        else returnVal=value;
        return returnVal;
    }
}
