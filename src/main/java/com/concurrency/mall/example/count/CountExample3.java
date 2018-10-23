package com.concurrency.mall.example.count;

import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class CountExample3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountExample3.class);

    public static int clientTotal  = 1000;

    public static int ThreadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void add(){
        /**
         * LongAdder使用了热点分离，将竞争数据分解。
         * 热点分离的含义：将value分解成一个数组，每个线程访问时，通过哈希等算法进行分配，对其中一个数字进行计数，
         * 最后将数组累加，就可以得到结果。
         * LongAdder中，value会被分解为多个单元的cell，每个Cell独自维护内部的值，
         * 当前对象的实际值由所有的cell累加得到。
         * LongAdder也有缺陷：如果在统计是发生并发更新，可能会导致最后的结果有误差。
         * 例如，生成全局唯一的序列号时，使用AtomicLong是正确的选择。*/
        count.increment();
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
