package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.NotThreadSafe;

/**
 * @description: 懒汉模式，--> 双重同步锁单例模式
 * @author: marcus
 * @date: 2018/10/25
 */
@NotThreadSafe
public class SingletonExample4 {

    /**私有构造函数 */
    private SingletonExample4(){

    }

    /**单例对象 */
    private static SingletonExample4 instance = null;

    /**静态工厂模式 */
    public static SingletonExample4 getInstance(){
        /**和example3相比，减小了锁的粒度，但它仍然线程不安全
         * 原因：new一个对象主要有以下三步：
         * 1、allocate memory 分配内存
         * 2、initInstance 初始化对象
         * 3、instance -> memory设置instance指向分配的内存
         *
         * 单线程下上面三步无论如何重排，结果不会变，但是多线程就不同了。
         * 如重排顺序为1，3，2
         * 线程1执行到了1，线程2执行完了3，
         * 这时线程1发现instance已经有地址了，就不再执行2，3，直接返回了空值。
         * */
        if(instance == null){
            synchronized (SingletonExample4.class){
                if(instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }

}
