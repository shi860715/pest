package com.isoftstone;

import java.lang.reflect.InvocationTargetException;

import org.springframework.util.ClassUtils;

public class Demo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
    }
}
