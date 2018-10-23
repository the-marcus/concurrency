package com.concurrency.mall.example;

import com.concurrency.mall.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample3 {
    /**
     * CAS的ABA问题：
     * CAS中，需要从内存中取出某一时刻的数据，然后在下一时刻进行比较和替换。
     * 中间有个时间差，可能会发生数据被修改的问题。
     * 然而，在JAVA中，由于GC的存在，不会发生某个地址里的值被替换然后又改回原值。
     * https://blog.csdn.net/liuguangqiang/article/details/52139280
     * 因为对象的生命周期，GC不可能将对应地址的内存释放，也就不可能发生A被B替换的情况。
     * 若无GC，就允许发生这种情况。
     * 但是如果是链表或者栈之类的场景，情况就不同了。
     * http://www.bubuko.com/infodetail-205993.html
     * 比如链表ABC，
     * 线程一要将AB交换，他需要检查表头是不是A，
     * 线程二恰好在线程一检查前将B去掉了，
     * 链表变成了AC，但是依然可以通过线程一的检查，于是CAS操作通过了。
     * 为了解决这个问题，引入了版本号的概念。
     * */



}
