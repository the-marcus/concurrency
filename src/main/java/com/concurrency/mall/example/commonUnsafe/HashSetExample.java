package com.concurrency.mall.example.commonUnsafe;

import com.concurrency.mall.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashSetExample {

    private static Set<Integer> set = new HashSet<>();

    public static int clientTotal  = 1000;

    public static int ThreadTotal = 200;

    public static void add(int i){
        set.add(i);
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    /**不能直接将i传入，lambda表达式中的变量得是final or effectively final
                     * effectively final 是JAVA8的新特性
                     * 局部内部类或匿名内部类访问的局部变量必须由final修饰，JAVA8不需要了。
                     * */
                    add(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);

                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        /**输出结果小于1000
         * HashSet是非线程安全的*/
        log.info("hashSet's size = {}",set.size());
    }
}
