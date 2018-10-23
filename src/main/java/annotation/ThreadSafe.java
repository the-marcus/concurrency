package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * target表示该注解的作用
 * retention表示注解存在的范围，source表示该注解存在于源码，编译时会被删除。
 * 所以这个注解仅仅为了表示某种含义
 * 该注解用于标记线程安全的类或写法
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
