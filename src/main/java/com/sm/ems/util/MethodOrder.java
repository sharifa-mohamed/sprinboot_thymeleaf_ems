package com.sm.ems.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class MethodOrder {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Order {
        int value();
    }


    public static Method[] getMethodsOrdered(Method[] methods) {
        Arrays.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                Order or1 = o1.getAnnotation(Order.class);
                Order or2 = o2.getAnnotation(Order.class);
                // nulls last
                if (or1 != null && or2 != null) {
                    return or1.value() - or2.value();
                } else if (or1 != null && or2 == null) {
                    return -1;
                } else if (or1 == null && or2 != null) {
                    return 1;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
//        for (Method m : methods) {
//            System.out.println(m.getName());
//        }
        return methods;
    }

}