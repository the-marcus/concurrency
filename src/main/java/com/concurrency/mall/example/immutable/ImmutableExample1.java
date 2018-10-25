package com.concurrency.mall.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @description: 不可变对象--final关键字
 * @author: marcus
 * @date: 2018/10/25
 */
@Slf4j
public class ImmutableExample1 {

    private static final int a = 1;

    private static final Integer b = 2;

    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }
    /**final关键字，修饰类，方法，变量。
     修饰类：不能被继承；
     修饰方法：不能被继承类修改，效率高（被转为内嵌调用）；
     修饰变量：基本类型变量的值不能被修改，引用类型变量不能指向其他对象。

     但是引用类型可以被修改，如final的Map<K,V>,里面的内容可以被修改。
     */

    public static void main(String[] args) {
//        a = 2;会出现编译错误
//        b = 3;同样编译错误
        map.put(1,3);
        log.info("{}",map.size());//结果为3
    }


}
