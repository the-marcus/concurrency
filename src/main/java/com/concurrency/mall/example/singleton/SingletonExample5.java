package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.ThreadSafe;

/**
 * @description: 懒汉模式，--> 双重同步锁单例模式2
 * @author: marcus
 * @date: 2018/10/25
 */
@ThreadSafe
public class SingletonExample5 {

    /**私有构造函数 */
    private SingletonExample5(){

    }

    /**和example4相比加入了volatile，可以防止指令重排
     * volatile有两个使用场景：双重检测、状态标识量
     * */

    /**单例对象 */
    private volatile static SingletonExample5 instance = null;

    /**静态工厂模式 */
    public static SingletonExample5 getInstance(){

        if(instance == null){
            synchronized (SingletonExample5.class){
                if(instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
