package com.xianjinyi.gameConsumer.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * 使用RibbonClient，为特定的目标服务自定义配置。
 * configuration属性，指定Ribbon的配置类。
 */
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
public class RibbonConfig {
}


class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则，改为随机
        return new RandomRule();
    }
}