package com.study.ai.converter;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.mcp.ToolContextToMcpMetaConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ChatModel chatModel = ...
 * String response = ChatClient.create(chatModel)
 *         .prompt("Tell me more about the customer with ID 42")
 *         // 在这里通过 toolContext 传递额外的元数据（如进度令牌）
 *         .toolContext(Map.of("progressToken", "my-progress-token"))
 *         .call()
 *         .content();
 * 框架提供了内置的转换器：
 * ToolContextToMcpMetaConverter.defaultConverter()
 * 过滤掉 MCP 交换键和空值（如果没有提供自定义 Bean，则默认使用此转换器）。
 * MCP 客户端启动器支持通过 ToolContextToMcpMetaConverter 接口，自定义将 Spring AI 的 ToolContext（工具上下文）转换为 MCP 工具调用元数据。
 * 此功能允许你将额外的上下文信息（例如用户 ID、密钥令牌等）作为元数据，连同 LLM 生成的调用参数一起传递。
 */
@Component
public class CustomToolContextToMcpMetaConverter implements ToolContextToMcpMetaConverter {

    @Override
    public Map<String, Object> convert(ToolContext toolContext) {
        if (toolContext == null || toolContext.getContext() == null) {
            return Map.of();
        }

        // 将工具上下文转换为 MCP 元数据的自定义逻辑
        Map<String, Object> metadata = new HashMap<>();

        // 示例：为所有键添加自定义前缀
        for (Map.Entry<String, Object> entry : toolContext.getContext().entrySet()) {
            if (entry.getValue() != null) {
                metadata.put("app_" + entry.getKey(), entry.getValue());
            }
        }

        // 示例：添加额外的元数据
        metadata.put("timestamp", System.currentTimeMillis());
        metadata.put("source", "spring-ai");

        return metadata;
    }
}
