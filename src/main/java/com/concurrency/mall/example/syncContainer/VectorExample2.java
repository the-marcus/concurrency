package com.concurrency.mall.example.syncContainer;

import com.concurrency.mall.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.Iterator;
import java.util.Vector;

/**
 * @description: Vector也有线程不安全的时候
 * @author: marcus
 * @date: 2018/11/1
 */
@Slf4j
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    /**使用Iterator遍历的情况下会抛出异常：
     * java.util.ConcurrentModificationException
     * 如果使用for循环就不会。
     * */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }

        /**用for */
//            new Thread(() -> {
//                for (int i = 0; i < vector.size(); i++) {
//                    vector.remove(i);
//                }
//            },"thread1").start();
//
//            new Thread(() -> {
//                for (int i = 0; i < vector.size(); i++) {
//                    vector.get(i);
//                }
//            },"thread2").start();

        /**用Iterator */
            new Thread(() -> {
                Iterator<Integer> iterator = vector.iterator();
                while (iterator.hasNext()){
                    Integer num = iterator.next();
                    vector.remove(num);
                }
            },"thread3").start();
    }
}
