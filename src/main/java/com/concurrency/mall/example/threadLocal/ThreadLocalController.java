package com.concurrency.mall.example.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    /**在Filter中使用RequestHolder.add方法将线程ID存储到ThreadLocal中，
     * 然后用Interceptor从ThreadLocal中获取这个ID，
     * 最后调用RequestHolder.remove将其移除*/
    @RequestMapping("/test")
    public Long test(){
        return RequestHolder.getId();
    }

}
