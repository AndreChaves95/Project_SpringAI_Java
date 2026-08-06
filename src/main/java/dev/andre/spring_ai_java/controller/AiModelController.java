package dev.andre.spring_ai_java.controller;

import dev.andre.spring_ai_java.service.ChatModelService;
import dev.andre.spring_ai_java.service.ImageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    // Request for OpenAI which requires a paid subscription
    @PostMapping(value = "/generate-image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateOpenAiImage(@RequestParam String prompt) {
        return ResponseEntity.ok(imageModelService.generateOpenAiImage(prompt));
    }

    // Request for StabilityAI which has a free subscription
    @PostMapping(value = "/generateAiImage", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateStabilityAiImage(@RequestParam String prompt) {
        return ResponseEntity.ok(imageModelService.generateStabilityAiImage(prompt));
    }
}
