package com.concurrency.mall.example.threadLocal;

/**
 * @description: ThreadLocal是一种很好的线程封闭方法，
 * JDBC中的Connection对象其实也做了线程封闭.
 * jdbc其实没有对Connection的线程安全做太多处理，
 * 当请求从连接池中获取一个Connection对象之后且没有返还之前，其他线程是无法获取到该Connection对象的。
 * 所以这种情况可以看做是将Connection对象封闭在线程里面。
 * @author: marcus
 * @date: 2018/11/1
 */
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
