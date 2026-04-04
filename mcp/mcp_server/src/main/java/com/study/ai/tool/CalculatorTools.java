package com.study.ai.tool;

import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.mcp.annotation.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @McpTool 注解将一个方法标记为 MCP 工具的实现，并支持自动生成 JSON Schema
 */
@Component
public class CalculatorTools {

    @McpTool(name = "add", description = "Add two numbers together")
    public int add(
            @McpToolParam(description = "First number", required = true) int a,
            @McpToolParam(description = "Second number", required = true) int b) {
        return a + b;
    }

    @McpTool(name = "multiply", description = "Multiply two numbers")
    public double multiply(
            @McpToolParam(description = "First number", required = true) double x,
            @McpToolParam(description = "Second number", required = true) double y) {
        return x * y;
    }

    @McpTool(name = "flexible-tool", description = "处理动态 Schema")
    public McpSchema.CallToolResult processDynamic(McpSchema.CallToolRequest request) {
        Map<String, Object> args = request.arguments();

        // 根据运行时 Schema 进行处理
        String result = "动态处理了 " + args.size() + " 个参数";

        return McpSchema.CallToolResult.builder()
                .addTextContent(result)
                .build();
    }

    @McpTool(name = "process-data", description = "使用请求上下文处理数据")
    public String processData(
            McpSyncRequestContext context,
            @McpToolParam(description = "要处理的数据", required = true) String data) {

        // 发送日志通知
        context.info("正在处理数据: " + data);

        // 发送进度通知（使用便捷方法）
        context.progress(p -> p.progress(0.5).total(1.0).message("处理中..."));

        // 向客户端发送 Ping
        context.ping();

        return "已处理: " + data.toUpperCase();
    }

}