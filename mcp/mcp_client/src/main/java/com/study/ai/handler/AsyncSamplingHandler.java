package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpSampling;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

//@Component
public class AsyncSamplingHandler {

    @McpSampling(clients = "llm-server")
    public Mono<McpSchema.CreateMessageResult> handleAsyncSampling(McpSchema.CreateMessageRequest request) {
        return Mono.fromCallable(() -> {
            String response = "";//generateLLMResponse(request);

            return McpSchema.CreateMessageResult.builder()
                    .role(McpSchema.Role.ASSISTANT)
                    .content(new McpSchema.TextContent(response))
                    .model("gpt-4")
                    .build();
        }).subscribeOn(Schedulers.boundedElastic());
    }
}