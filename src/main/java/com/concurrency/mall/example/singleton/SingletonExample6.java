package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.NotRecommend;
import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 饿汉模式，线程安全，使用静态代码块创建对象
 * @author: marcus
 * @date: 2018/10/25
 */
@ThreadSafe
@Slf4j
public class SingletonExample6 {

    /**私有构造函数 */
    private SingletonExample6(){

    }

    /**单例对象 */
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    /**静态工厂模式 */
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        /**两次执行结果是一样的，得到的是同一个对象 */
        log.info("{}",getInstance().hashCode());
        log.info("{}",getInstance().hashCode());
    }

}
