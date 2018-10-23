package com.concurrency.mall.example;

import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    private static AtomicIntegerFieldUpdater<AtomicExample2> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count");
    public volatile int count = 100;

    private static AtomicExample2 example2 = new AtomicExample2();

    public static void main(String[] args) {
        if(updater.compareAndSet(example2,100,120)){
            System.out.println("success");
        } else {
            System.out.println("faild");
        }

        if(updater.compareAndSet(example2,100,120)){
            System.out.println("success");
        } else {
            System.out.println("faild");
        }
    }


}
