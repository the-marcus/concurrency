package com.concurrency.mall.example.commonUnsafe;

import com.concurrency.mall.annotation.NotThreadSafe;
import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateExample2 {
    /**一种最简单的安全写法：将SimpleDateFormat对象声明为局部变量 */
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static int clientTotal  = 1000;

    public static int ThreadTotal = 200;

    public static void update(){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20181101");
        }catch (Exception e){
            log.error("parse Exception");
        }
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);

                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
