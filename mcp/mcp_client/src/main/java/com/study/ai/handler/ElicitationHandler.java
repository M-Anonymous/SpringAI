package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpElicitation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @McpElicitation 注解用于处理旨在从用户那里收集额外信息的诱导请求。
 */
@Component
public class ElicitationHandler {

    @McpElicitation(clients = "interactive-server")
    public McpSchema.ElicitResult handleElicitationRequest(McpSchema.ElicitRequest request) {
        // 向用户展示请求并收集输入
        Map<String, Object> userData = null;//presentFormToUser(request.requestedSchema());

        if (userData != null) {
            return new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.ACCEPT, userData);
        } else {
            return new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.DECLINE, null);
        }
    }

    @McpElicitation(clients = "interactive-server")
    public McpSchema.ElicitResult handleInteractiveElicitation(McpSchema.ElicitRequest request) {
        Map<String, Object> schema = request.requestedSchema();
        Map<String, Object> userData = new HashMap<>();

        // 检查请求了哪些信息
        if (schema != null && schema.containsKey("properties")) {
            Map<String, Object> properties = (Map<String, Object>) schema.get("properties");

            // 根据模式收集用户输入
            if (properties.containsKey("name")) {
                userData.put("name", "");
            }
            if (properties.containsKey("email")) {
                userData.put("email", "");
            }
            if (properties.containsKey("preferences")) {
                userData.put("preferences", "");
            }
        }

        return new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.ACCEPT, userData);
    }

    @McpElicitation(clients = "interactive-server")
    public Mono<McpSchema.ElicitResult> handleAsyncElicitation(McpSchema.ElicitRequest request) {
        return Mono.fromCallable(() -> {
                    // 异步用户交互
                    Map<String, Object> userData = null;//asyncGatherUserInput(request);
                    return new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.ACCEPT, userData);
                }).timeout(Duration.ofSeconds(30))
                .onErrorReturn(new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.CANCEL, null));
    }
}