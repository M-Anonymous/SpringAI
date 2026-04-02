package com.study.ai.client;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Component
public class CustomMcpSyncClientCustomizer implements McpSyncClientCustomizer {
    @Override
    public void customize(String serverConfigurationName, McpClient.SyncSpec spec) {

        // 自定义请求超时配置
        spec.requestTimeout(Duration.ofSeconds(30));

        // 设置此客户端可以访问的根 URI（用于限制文件系统访问范围）
        spec.roots(new McpSchema.Root("/home","根目录"));

        // 设置自定义采样处理器，用于处理消息创建请求
        spec.sampling((McpSchema.CreateMessageRequest messageRequest) -> {
            // 处理采样逻辑
            McpSchema.CreateMessageResult result = null;
            return result;
        });

        // 设置自定义询问处理器，用于处理询问请求（向用户获取更多信息）
        spec.elicitation((McpSchema.ElicitRequest request) -> {
            // 处理询问逻辑
            return new McpSchema.ElicitResult(McpSchema.ElicitResult.Action.ACCEPT, Map.of("message", request.message()));
        });

        // 添加一个消费者，用于在收到进度通知时接收通知
        spec.progressConsumer((McpSchema.ProgressNotification progress) -> {
            // 处理进度通知
        });

        // 添加一个消费者，用于在可用工具发生变化（如添加或移除）时接收通知
        spec.toolsChangeConsumer((List<McpSchema.Tool> tools) -> {
            // 处理工具变更
        });

        // 添加一个消费者，用于在可用资源发生变化（如添加或移除）时接收通知
        spec.resourcesChangeConsumer((List<McpSchema.Resource> resources) -> {
            // 处理资源变更
        });

        // 添加一个消费者，用于在可用提示词发生变化（如添加或移除）时接收通知
        spec.promptsChangeConsumer((List<McpSchema.Prompt> prompts) -> {
            // 处理提示词变更
        });

        // 添加一个消费者，用于在收到来自服务器的日志消息时接收通知
        spec.loggingConsumer((McpSchema.LoggingMessageNotification log) -> {
            // 处理日志消息
        });
    }
}
