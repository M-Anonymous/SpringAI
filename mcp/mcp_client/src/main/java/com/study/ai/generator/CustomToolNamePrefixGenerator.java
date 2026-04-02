package com.study.ai.generator;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.McpConnectionInfo;
import org.springframework.ai.mcp.McpToolNamePrefixGenerator;
import org.springframework.stereotype.Component;

/**
 * 如果多个服务器提供了同名的工具，这可能会导致冲突。
 * DefaultMcpToolNamePrefixGenerator
 * 这是默认使用的生成器（如果你没有提供自定义 Bean 的话）。它通过追踪重复项并在必要时添加计数器前缀，来确保工具名称的唯一性。
 */
@Component
public class CustomToolNamePrefixGenerator implements McpToolNamePrefixGenerator {

    @Override
    public String prefixedToolName(McpConnectionInfo connectionInfo, McpSchema.Tool tool) {
        // 生成带前缀工具名称的自定义逻辑

        // 示例：使用服务器名称和版本作为前缀
        String serverName = connectionInfo.initializeResult().serverInfo().name();
        String serverVersion = connectionInfo.initializeResult().serverInfo().version();
        return serverName + "_v" + serverVersion.replace(".", "_") + "_" + tool.name();
    }
}
