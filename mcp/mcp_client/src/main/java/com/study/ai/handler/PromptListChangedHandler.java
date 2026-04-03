package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpPromptListChanged;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @McpPromptListChanged 注解用于处理服务器提示词列表变更时的通知。
 */
@Component
public class PromptListChangedHandler {

    @McpPromptListChanged(clients = "prompt-server")
    public void handlePromptListChanged(List<McpSchema.Prompt> updatedPrompts) {
        System.out.println("提示词已更新: " + updatedPrompts.size());

        // 更新提示词目录
        //promptCatalog.updatePrompts(updatedPrompts);

        // 如果需要，刷新 UI
//        if (uiController != null) {
//            uiController.refreshPromptList(updatedPrompts);
//        }
    }

    @McpPromptListChanged(clients = "prompt-server")
    public Mono<Void> handleAsyncPromptUpdate(List<McpSchema.Prompt> updatedPrompts) {
        return Flux.fromIterable(updatedPrompts)
                //.flatMap(prompt -> validatePrompt(prompt))
                .collectList()
                .doOnNext(validPrompts -> {
                    //promptRepository.saveAll(validPrompts);
                })
                .then();
    }
}