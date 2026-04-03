package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpToolListChanged;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @McpToolListChanged 注解用于处理服务器工具列表变更时的通知。
 */
@Component
public class ToolListChangedHandler {

    @McpToolListChanged(clients = "tool-server")
    public void handleToolListChanged(List<McpSchema.Tool> updatedTools) {
        System.out.println("工具列表已更新: 可用工具 " + updatedTools.size());

        // 更新本地工具注册表
        //toolRegistry.updateTools(updatedTools);

        // 记录新工具
        for (McpSchema.Tool tool : updatedTools) {
            System.out.println("  - " + tool.name() + ": " + tool.description());
        }
    }

    @McpToolListChanged(clients = "tool-server")
    public Mono<Void> handleAsyncToolListChanged(List<McpSchema.Tool> updatedTools) {
        return Mono.fromRunnable(() -> {
            // 异步处理工具列表更新
            //processToolListUpdate(updatedTools);

            // 通知感兴趣的组件
            //eventBus.publish(new ToolListUpdatedEvent(updatedTools));
        }).then();
    }

    @McpToolListChanged(clients = "dynamic-server")
    public void handleDynamicServerToolUpdate(List<McpSchema.Tool> updatedTools) {
        // 处理频繁更改工具的特定服务器的工具
        //dynamicToolManager.updateServerTools("dynamic-server", updatedTools);

        // 重新评估工具可用性
        //reevaluateToolCapabilities();
    }
}
