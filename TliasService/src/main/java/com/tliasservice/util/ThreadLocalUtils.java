package com.tliasservice.util;

public class ThreadLocalUtils {
    private static final ThreadLocal<Integer> CURRENT_LOCAL=new ThreadLocal<>();


    public static void setCurrentLocal(Integer empId){
        CURRENT_LOCAL.set(empId);
    }

    public static Integer getCurrentId(){
        return CURRENT_LOCAL.get();
    }

    public static void remove(){
        CURRENT_LOCAL.remove();
    }
}
