package dev.andre.spring_ai_java.controller;

import dev.andre.spring_ai_java.service.ChatModelService;
import dev.andre.spring_ai_java.service.ImageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController             // Class handles the request and returns data directly
@RequestMapping("/api")
@RequiredArgsConstructor
public class AiModelController {

    private final ChatModelService chatModelService;

    private final ImageModelService imageModelService;

    @GetMapping("/generateText")
    public String generateAiText(@RequestParam String prompt) {
        return chatModelService.generateText(prompt);
    }

    @PostMapping("/generateImage")
    public String generateAiImage(@RequestParam String prompt) {
        return imageModelService.generateImage(prompt);
    }
}
