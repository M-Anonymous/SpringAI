package com.study.ai.provider;

import com.study.ai.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 自动配置功能会将工具回调（tool callbacks）自动注册为 MCP 工具。你可以定义多个产生 ToolCallback 的 Bean，自动配置机制会将它们合并在一起。
 */
//@Component
public class ToolProvider {

    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }
}
