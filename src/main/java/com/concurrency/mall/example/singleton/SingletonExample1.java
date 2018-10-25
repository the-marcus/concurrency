package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.NotThreadSafe;

/**
 * @description: 懒汉模式，这种单例模式是线程不安全的
 * 线程安全的懒汉模式见example3
 * @author: marcus
 * @date: 2018/10/25
 */
@NotThreadSafe
public class SingletonExample1 {

    /**私有构造函数 */
    private SingletonExample1(){

    }

    /**单例对象 */
    private static SingletonExample1 instance = null;

    /**静态工厂模式 */
    public static SingletonExample1 getInstance(){
        /**if语句不具有原子性，
         * 检查是否为null时可能会有另一个线程检查并初始化 */
        if(instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
