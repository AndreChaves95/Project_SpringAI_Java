package dev.andre.spring_ai_java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service                    // Class holds the business logic and should be managed as a Bean
@RequiredArgsConstructor
public class ChatModelService {

    private final ChatModel chatModel;

    public String generateText(String prompt) {
        return chatModel.call(prompt);
    }
}
