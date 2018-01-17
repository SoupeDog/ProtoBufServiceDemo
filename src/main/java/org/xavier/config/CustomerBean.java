package org.xavier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xavier.extend.CustomerHttpMessageConverter;

/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2018.01.17
 * @since Jdk 1.8
 */
@Configuration
public class CustomerBean {


    @Bean
    public CustomerHttpMessageConverter customerHttpMessageConverter() {
        return new CustomerHttpMessageConverter();
    }

}
