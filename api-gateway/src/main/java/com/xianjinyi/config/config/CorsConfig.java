package com.xianjinyi.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;;

import java.util.Arrays;

/**
 * 跨域配置
 *
 * 跨域资源共享
 * C - Cross  O - Origin  R - Resource  S - Sharing
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // 允许cookies跨域
        config.setAllowedOrigins(Arrays.asList("*")); //允许来源的域 http:www.a.com
        config.setAllowedHeaders(Arrays.asList("*"));// 头
        config.setAllowedMethods(Arrays.asList("*"));// get post这些
        config.setMaxAge(300l);// 缓存时间，对于相同的跨域请求，此时间内不再检查

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
