package com.study.ai.client;


import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.json.McpJsonMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class MyMcpClient {

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean(MyMcpClient.class)
    public McpSyncClient mcpClient() {
        ServerParameters stdioParams;

        if (isWindows()) {
            // Windows: 使用 cmd.exe /c npx 的方式
            var winArgs = new ArrayList<>(Arrays.asList(
                    "/c", "npx", "-y", "@modelcontextprotocol/server-filesystem", "target"));
            stdioParams = ServerParameters.builder("cmd.exe")
                    .args(winArgs)
                    .build();
        } else {
            // Linux/Mac: 直接使用 npx 的方式
            stdioParams = ServerParameters.builder("npx")
                    .args("-y", "@modelcontextprotocol/server-filesystem", "target")
                    .build();
        }

        return McpClient.sync(new StdioClientTransport(stdioParams, McpJsonMapper.createDefault()))
                .requestTimeout(Duration.ofSeconds(10))
                .build();
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
