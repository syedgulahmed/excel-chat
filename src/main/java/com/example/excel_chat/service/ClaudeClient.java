package com.example.excel_chat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ClaudeClient {

    private final ChatClient chatClient;

    public ClaudeClient(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    
    public String send(String input){
        return chatClient.prompt(input).call().content();
    }

}
