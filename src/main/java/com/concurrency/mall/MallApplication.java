package com.concurrency.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MallApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MallApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}

	/**这里没有继承WebMvcConfigurerAdapter是因为它已经过时了，
	 * 在Spring5时 WebMvcConfigurerAdapter已经被弃用，
	 * 所以用WebMvcConfigurer代替即可
	 * */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
