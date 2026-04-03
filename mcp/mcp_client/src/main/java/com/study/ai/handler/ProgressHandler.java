package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpProgress;
import org.springframework.stereotype.Component;

/**
 * @McpProgress 注解用于处理长时间运行操作的进度通知。
 */
@Component
public class ProgressHandler {

    @McpProgress(clients = "my-mcp-server")
    public void handleProgressNotification(McpSchema.ProgressNotification notification) {
        double percentage = notification.progress() * 100;
        System.out.println(String.format("进度: %.2f%% - %s",
                percentage, notification.message()));
    }

    @McpProgress(clients = "my-mcp-server")
    public void handleProgressWithDetails(
            String progressToken,
            double progress,
            Double total,
            String message) {

        if (total != null) {
            System.out.println(String.format("[%s] %.0f/%.0f - %s",
                    progressToken, progress, total, message));
        } else {
            System.out.println(String.format("[%s] %.2f%% - %s",
                    progressToken, progress * 100, message));
        }

        // 更新 UI 进度条
        //updateProgressBar(progressToken, progress);
    }

    @McpProgress(clients = "long-running-server")
    public void handleLongRunningProgress(McpSchema.ProgressNotification notification) {
        // 跟踪特定服务器的进度
        //progressTracker.update("long-running-server", notification);

        // 如果需要，发送通知
        if (notification.progress() >= 1.0) {
            //notifyCompletion(notification.progressToken());
        }
    }
}