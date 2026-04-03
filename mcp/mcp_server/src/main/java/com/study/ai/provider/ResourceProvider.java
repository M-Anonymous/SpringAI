package com.study.ai.provider;

import io.modelcontextprotocol.spec.McpSchema;
import org.springaicommunity.mcp.annotation.McpResource;
import org.springaicommunity.mcp.context.McpSyncRequestContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @McpResource 注解通过 URI 模板提供对资源的访问。
 */
//@Component
public class ResourceProvider {

    @McpResource(
            uri = "config://{key}",
            name = "Configuration",
            description = "Provides configuration data")
    public String getConfig(String key) {
        return null;//configData.get(key);
    }

    @McpResource(
            uri = "user-profile://{username}",
            name = "User Profile",
            description = "Provides user profile information")
    public McpSchema.ReadResourceResult getUserProfile(String username) {
        String profileData = null;//loadUserProfile(username);

        return new McpSchema.ReadResourceResult(List.of(
                new McpSchema.TextResourceContents(
                        "user-profile://" + username,
                        "application/json",
                        profileData)
        ));
    }

    @McpResource(
            uri = "data://{id}",
            name = "Data Resource",
            description = "Resource with request context")
    public McpSchema.ReadResourceResult getData(
            McpSyncRequestContext context,
            String id) {

        // Send logging notification using convenient method
        context.info("Accessing resource: " + id);

        // Ping the client
        context.ping();

        String data = null;//fetchData(id);

        return new McpSchema.ReadResourceResult(List.of(
                new McpSchema.TextResourceContents("data://" + id, "text/plain", data)
        ));
    }
}