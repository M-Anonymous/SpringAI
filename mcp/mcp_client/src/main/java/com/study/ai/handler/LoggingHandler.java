package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpLogging;
import org.springframework.stereotype.Component;

/**
 * @McpLogging 注解用于处理来自 MCP 服务器的日志消息通知。
 */
//@Component
public class LoggingHandler {

    @McpLogging(clients = "my-mcp-server")
    public void handleLoggingMessage(McpSchema.LoggingMessageNotification notification) {
        System.out.println("Received log: " + notification.level() +
                " - " + notification.data());
    }

    @McpLogging(clients = "my-mcp-server")
    public void handleLoggingWithParams(McpSchema.LoggingLevel level, String logger, String data) {
        System.out.println(String.format("[%s] %s: %s", level, logger, data));
    }
}