package com.study.ai.provider;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpComplete;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @McpComplete 注解为提示词（Prompts）提供了自动补全功能。
 */
//@Component
public class CompletionProvider {

    @McpComplete(prompt = "city-search")
    public List<String> completeCityName(String prefix) {
//        return cities.stream()
//                .filter(city -> city.toLowerCase().startsWith(prefix.toLowerCase()))
//                .limit(10)
//                .toList();
        return null;
    }

    @McpComplete(prompt = "travel-planner")
    public List<String> completeTravelDestination(McpSchema.CompleteRequest.CompleteArgument argument) {
        String prefix = argument.value().toLowerCase();
        String argumentName = argument.name();

        // Different completions based on argument name
        if ("city".equals(argumentName)) {
            return null;//completeCities(prefix);
        } else if ("country".equals(argumentName)) {
            return null;// completeCountries(prefix);
        }

        return List.of();
    }

    @McpComplete(prompt = "code-completion")
    public McpSchema.CompleteResult completeCode(String prefix) {
        List<String> completions = null;//generateCodeCompletions(prefix);

        return new McpSchema.CompleteResult(
                new McpSchema.CompleteResult.CompleteCompletion(
                        completions,
                        completions.size(),  // total
                        null//hasMoreCompletions   // hasMore flag
                )
        );
    }
}
