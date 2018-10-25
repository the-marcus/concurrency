package com.concurrency.mall.example.singleton;

import com.concurrency.mall.annotation.NotRecommend;
import com.concurrency.mall.annotation.ThreadSafe;

/**
 * @description: 饿汉模式，线程安全，单例对象在装载时进行创建
 * 缺陷：如果构造函数中存在过多处理，会导致性能问题
 *  * 饿汉模式另一种写法见example6
 * @author: marcus
 * @date: 2018/10/25
 */
@ThreadSafe
@NotRecommend /**性能差 */
public class SingletonExample2 {

    /**私有构造函数 */
    private SingletonExample2(){

    }

    /**单例对象 */
    private static SingletonExample2 instance = new SingletonExample2();

    /**静态工厂模式 */
    public static SingletonExample2 getInstance(){
        return instance;
    }

}
