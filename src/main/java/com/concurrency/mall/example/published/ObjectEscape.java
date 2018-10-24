package com.concurrency.mall.example.published;

import com.concurrency.mall.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ObjectEscape {

    private int num = 0;

    public ObjectEscape(){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}",ObjectEscape.this.num);
        }
    }

    public static void main(String[] args) {
        /**在ObjectEscape对象没有构造完成之前就使用了ObjectEscape对象，
         * 这里是不安全的
         * */
        new ObjectEscape();
    }
}
