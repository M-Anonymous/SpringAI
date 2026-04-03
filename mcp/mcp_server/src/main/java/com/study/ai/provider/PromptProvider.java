package com.study.ai.provider;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpPrompt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @McpPrompt 注解用于生成用于 AI 交互的提示词消息。
 */
//@Component
public class PromptProvider {

    @McpPrompt(
            name = "greeting",
            description = "Generate a greeting message")
    public McpSchema.GetPromptResult greeting(
            @McpArg(name = "name", description = "User's name", required = true)
            String name) {

        String message = "Hello, " + name + "! How can I help you today?";

        return new McpSchema.GetPromptResult(
                "Greeting",
                List.of(new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent(message)))
        );
    }

    @McpPrompt(
            name = "personalized-message",
            description = "Generate a personalized message")
    public McpSchema.GetPromptResult personalizedMessage(
            @McpArg(name = "name", required = true) String name,
            @McpArg(name = "age", required = false) Integer age,
            @McpArg(name = "interests", required = false) String interests) {

        StringBuilder message = new StringBuilder();
        message.append("Hello, ").append(name).append("!\n\n");

        if (age != null) {
            message.append("At ").append(age).append(" years old, ");
            // Add age-specific content
        }

        if (interests != null && !interests.isEmpty()) {
            message.append("Your interest in ").append(interests);
            // Add interest-specific content
        }

        return new McpSchema.GetPromptResult(
                "Personalized Message",
                List.of(new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent(message.toString())))
        );
    }
}