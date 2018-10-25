package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.ThreadSafe;

/**
 * @description: 修改后的懒汉模式，修改了静态工厂方法
 * @author: marcus
 * @date: 2018/10/25
 */
@ThreadSafe
public class SingletonExample3 {

    /**私有构造函数 */
    private SingletonExample3(){

    }

    /**单例对象 */
    private static SingletonExample3 instance = null;

    /**静态工厂模式 */
    public static synchronized SingletonExample3 getInstance(){
        /**if语句不具有原子性，
         * 检查是否为null时可能会有另一个线程检查并初始化 */
        if(instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
