package com.example.demo.设计模式.工厂模式.简单工厂模式;

import java.lang.reflect.InvocationTargetException;

public class CourseFactory {

    public static Course getInstance(Class clazz) {
        try {
            return (Course)clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
