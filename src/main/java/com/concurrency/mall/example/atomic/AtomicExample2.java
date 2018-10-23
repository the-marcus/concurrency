package com.concurrency.mall.example.atomic;

import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    private static AtomicIntegerFieldUpdater<AtomicExample2> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count");
    public static volatile int count = 100;

    private static AtomicExample2 example2 = new AtomicExample2();

    public static void main(String[] args) {
        if(updater.compareAndSet(example2,100,120)){
            log.info("success,count : {}",count);
        } else {
            log.info("failed,count : {}",count);
        }

        if(updater.compareAndSet(example2,100,120)){
            log.info("success,count : {}",count);
        } else {
            log.info("failed,count : {}",count);
        }
    }


}
