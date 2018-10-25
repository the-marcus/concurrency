package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.Recommend;
import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 使用枚举做实例化是最安全的
 * @author: marcus
 * @date: 2018/10/25
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    /**私有构造函数 */
    private SingletonExample7(){

    }

    /**静态工厂模式 */
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        /**JVM保证这个构造方法只被调用一次，且是在这个类调用之前初始化的。 */
        Singleton (){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

}
