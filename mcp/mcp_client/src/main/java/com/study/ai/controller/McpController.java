package com.study.ai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class McpController {

    private final ChatClient chatClient;

    public McpController(ChatClient.Builder builder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = builder.defaultToolCallbacks(toolCallbackProvider).build();
    }

    @GetMapping("/ask")
    public Flux<String> ask(@RequestParam String question){
        return chatClient.prompt()
                .user(question)
                .stream()
                .content();
    }
}
