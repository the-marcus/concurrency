package com.concurrency.mall.example;

public class RequestHolder {

    /**ThreadLocal会在所有线程中提供一个 已指定变量的 副本，线程之间互不影响，避免了线程安全问题。 */
    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
