package dev.andre.spring_ai_java.controller;

import dev.andre.spring_ai_java.service.ChatModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController             // Class handles the request and returns data directly
@RequestMapping("/api")
@RequiredArgsConstructor
public class AiModelController {

    private final ChatModelService chatModelService;

    @GetMapping("/model/generateText")
    public String generateAiText(@RequestParam String prompt) {
        return chatModelService.generateText(prompt);
    }
}
