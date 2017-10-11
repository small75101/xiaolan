package com.xiaolan.common.config;

import com.xiaolan.common.constant.WebCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    WebCst webCst;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/error").setStatusCode(HttpStatus.NOT_FOUND).setViewName("error/404");
        registry.addViewController("/error").setStatusCode(HttpStatus.FORBIDDEN).setViewName("error/404");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/uploadpic/**").addResourceLocations("file:///" + webCst.getUploadPath() + WebCst.UPLOAD_PIC_PATH);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new UserLoadIntreceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean CharacterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("forceEncoding", "true");
        registration.setName("characterEncodingFilter");
        registration.setOrder(1);
        return registration;
    }
}
