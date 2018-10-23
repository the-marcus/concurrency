package com.concurrency.mall.example;

import com.concurrency.mall.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class CountExample1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountExample1.class);

    public static int clientTotal  = 1000;

    public static int ThreadTotal = 200;

    public static int count = 0;

    public static void add(){
        count++;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    LOGGER.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        LOGGER.info("count:{}",count);
    }
}
