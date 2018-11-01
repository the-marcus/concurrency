package com.concurrency.mall.example.commonUnsafe;

import com.concurrency.mall.annotation.NotThreadSafe;
import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateExample3 {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static int clientTotal  = 1000;

    public static int ThreadTotal = 200;

    public static void update(int i){
        log.info("{}-{}",i,DateTime.parse("20181101",dateTimeFormatter).toDate());
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
                    update(count);
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
