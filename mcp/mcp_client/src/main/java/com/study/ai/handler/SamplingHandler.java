package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpSampling;
import org.springframework.stereotype.Component;

/**
 * @McpSampling 注解用于处理来自 MCP 服务器的 LLM 补全采样请求。
 */
//@Component
public class SamplingHandler {

    @McpSampling(clients = "llm-server")
    public McpSchema.CreateMessageResult handleSamplingRequest(McpSchema.CreateMessageRequest request) {
        // 处理请求并生成响应
        String response = "";//generateLLMResponse(request);

        return McpSchema.CreateMessageResult.builder()
                .role(McpSchema.Role.ASSISTANT)
                .content(new McpSchema.TextContent(response))
                .model("gpt-4")
                .build();
    }
}
