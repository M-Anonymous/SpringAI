package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpLogging;
import org.springaicommunity.mcp.annotation.McpProgress;
import org.springaicommunity.mcp.annotation.McpSampling;
import org.springaicommunity.mcp.annotation.McpToolListChanged;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MCP 客户端启动器会自动检测并注册带有注解的方法，用于处理各种 MCP 客户端操作：
 * @McpLogging - 处理来自 MCP 服务器的日志消息通知
 * @McpSampling - 处理来自 MCP 服务器的采样请求（通常用于 LLM 补全）
 * @McpElicitation - 处理诱导请求，用于从用户那里收集额外信息
 * @McpProgress - 处理长时间运行操作的进度通知
 * @McpToolListChanged - 处理服务器工具列表变更的通知
 * @McpResourceListChanged - 处理服务器资源列表变更的通知
 * @McpPromptListChanged - 处理服务器提示词列表变更的通知
 */
@Component
public class McpClientHandlers {

    // 监听特定服务器 ("server1") 的日志消息
    @McpLogging(clients = "server1")
    public void handleLoggingMessage(McpSchema.LoggingMessageNotification notification) {
        System.out.println("收到日志: " + notification.level() +
                " - " + notification.data());
    }

    // 处理采样请求并返回 LLM 响应
    @McpSampling(clients = "server1")
    public McpSchema.CreateMessageResult handleSamplingRequest(McpSchema.CreateMessageRequest request) {
        // 处理请求并生成响应
        String response = "";//generateLLMResponse(request);

        return McpSchema.CreateMessageResult.builder()
                .role(McpSchema.Role.ASSISTANT)
                .content(new McpSchema.TextContent(response))
                .model("gpt-4")
                .build();
    }

    // 监听进度通知
    @McpProgress(clients = "server1")
    public void handleProgressNotification(McpSchema.ProgressNotification notification) {
        double percentage = notification.progress() * 100;
        System.out.println(String.format("进度: %.2f%% - %s",
                percentage, notification.message()));
    }

    // 监听工具列表更新
    @McpToolListChanged(clients = "server1")
    public void handleToolListChanged(List<McpSchema.Tool> updatedTools) {
        System.out.println("工具列表已更新: " + updatedTools.size() + " 个工具可用");
        // 更新本地工具注册表
        //toolRegistry.updateTools(updatedTools);
    }
}
