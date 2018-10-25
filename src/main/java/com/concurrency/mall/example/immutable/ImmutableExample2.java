package com.concurrency.mall.example.immutable;

import com.concurrency.mall.annotation.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @description: 不可变对象--final关键字
 * @author: marcus
 * @date: 2018/10/25
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        /**使用Collections.unmodifiableXXX
         * 其原理很简单，把参数map里的数据拷贝到一个final Map里，
         * 其他的修改数据的方法中，操作全部去掉，改为抛出异常
         * */
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        /**报错 */
        map.put(1,3);
        log.info("{}",map.size());
    }


}
