package com.study.ai.filter;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.McpConnectionInfo;
import org.springframework.ai.mcp.McpToolFilter;
import org.springframework.stereotype.Component;

/**
 * 工具过滤
 */
//@Component
public class CustomMcpToolFilter implements McpToolFilter {

    @Override
    public boolean test(McpConnectionInfo connectionInfo, McpSchema.Tool tool) {
        // 基于连接信息和工具属性的过滤逻辑
        // 返回 true 表示包含该工具，返回 false 表示排除该工具

        // 示例：排除来自特定客户端的工具
        if (connectionInfo.clientInfo().name().equals("restricted-client")) {
            return false;
        }

        // 示例：仅包含具有特定名称的工具
        if (tool.name().startsWith("allowed_")) {
            return true;
        }

        // 示例：基于工具描述或其他属性进行过滤
        if (tool.description() != null &&
                tool.description().contains("experimental")) {
            return false;
        }

        return true; // 默认包含所有其他工具
    }
}
