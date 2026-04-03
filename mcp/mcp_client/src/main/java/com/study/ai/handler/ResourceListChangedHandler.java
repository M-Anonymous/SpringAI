package com.study.ai.handler;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpResourceListChanged;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @McpResourceListChanged 注解用于处理服务器资源列表变更时的通知。
 */
@Component
public class ResourceListChangedHandler {

    @McpResourceListChanged(clients = "resource-server")
    public void handleResourceListChanged(List<McpSchema.Resource> updatedResources) {
        System.out.println("资源已更新: " + updatedResources.size());

        // 更新资源缓存
        //resourceCache.clear();
        for (McpSchema.Resource resource : updatedResources) {
            //resourceCache.register(resource);
        }
    }

    @McpResourceListChanged(clients = "resource-server")
    public void analyzeResourceChanges(List<McpSchema.Resource> updatedResources) {
        // 分析变更内容
        Set<String> newUris = updatedResources.stream()
                .map(McpSchema.Resource::uri)
                .collect(Collectors.toSet());

        Set<String> removedUris = new HashSet<>();
//        Set<String> removedUris = previousUris.stream()
//                .filter(uri -> !newUris.contains(uri))
//                .collect(Collectors.toSet());

        if (!removedUris.isEmpty()) {
            //handleRemovedResources(removedUris);
        }

        // 更新跟踪
        //previousUris = newUris;
    }
}
