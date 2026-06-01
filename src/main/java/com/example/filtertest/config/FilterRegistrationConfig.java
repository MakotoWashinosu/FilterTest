package com.example.filtertest.config;

import com.example.filtertest.filter.PathForwardFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {

    // ここで＠Beanをコメントアウトすると、PathForwardFilter2はFilterRegistrationBeanで登録されなくなります。
    //@Bean
    public FilterRegistrationBean<PathForwardFilter2> pathForwardFilter2Registration(SysCheckProperties properties) {
        PathForwardFilter2 filter = new PathForwardFilter2(properties);
        FilterRegistrationBean<PathForwardFilter2> registration = new FilterRegistrationBean<>(filter);
        registration.setName("pathForwardFilter2");
        registration.setOrder(1);
        registration.setUrlPatterns(properties.getPaths());
        return registration;
    }
}
