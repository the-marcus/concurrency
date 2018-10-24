package com.concurrency.mall.example.published;

import com.concurrency.mall.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublished {

    private String[] s = new String[]{"a","b","c"};

    public String[] getS(){
        return s;
    }

    public static void main(String[] args) {
        UnsafePublished unsafePublished = new UnsafePublished();
        log.info(Arrays.toString(unsafePublished.getS()));
        /**我们可以使用公有域访问私有域，导致s里的数据是可以被修改的
         * 当某一线程需要s中的数据时，不能保证s里的数据有没有被修改过。
         * */
        unsafePublished.getS()[0] = "d";
        log.info(Arrays.toString(unsafePublished.getS()));
    }
}
