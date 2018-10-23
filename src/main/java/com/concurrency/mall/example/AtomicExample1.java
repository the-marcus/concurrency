package com.concurrency.mall.example;

import com.concurrency.mall.annotation.NotThreadSafe;
import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample1 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);//yes
        count.compareAndSet(0,1);//no
        count.compareAndSet(1,3);//no
        count.compareAndSet(2,4);//yes
        count.compareAndSet(3,5);//no
        /**最后的结果是4不是5
         * 其中有几句是不执行的。
         * */
        System.out.println(count);
    }
}
