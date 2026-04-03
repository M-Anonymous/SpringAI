package com.study.ai.config;

import org.springframework.ai.mcp.McpToolNamePrefixGenerator;
import org.springframework.ai.mcp.ToolContextToMcpMetaConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class McpConfiguration {

    /**
     * 工具命名不要前缀，不推荐在同时使用多个 MCP 服务器时这样做
     */
    @Bean
    public McpToolNamePrefixGenerator mcpToolNamePrefixGenerator() {
        return McpToolNamePrefixGenerator.noPrefix();
    }

    /**
     * 完全禁用上下文到元数据的转换：
     * @return
     */
    @Bean
    public ToolContextToMcpMetaConverter toolContextToMcpMetaConverter() {
        return ToolContextToMcpMetaConverter.noOp();
    }
}
