package dev.andre.spring_ai_java.controller;

import dev.andre.spring_ai_java.service.AudioSpeechModelService;
import dev.andre.spring_ai_java.service.ChatModelService;
import dev.andre.spring_ai_java.service.ImageModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController             // Class handles the request and returns data directly
@RequestMapping("/api")
@RequiredArgsConstructor
public class AiModelController {

    private final ChatModelService chatModelService;
    private final ImageModelService imageModelService;
    private final AudioSpeechModelService audioSpeechModelService;

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

    @PostMapping(value = "/generateAudio", produces = "audio/mpeg")
    public ResponseEntity<byte[]> generateAudioWithAi(@RequestParam String text) {
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("audio/mpeg"))
                .body(audioSpeechModelService.convertTextToSpeech(text));
    }

    // For this request to work, the file provided must have one of the following formats:
    // 'flac', 'm4a', 'mp3', 'mp4', 'mpeg', 'mpga', 'oga', 'ogg', 'wav', 'webm'
    @PostMapping(value = "/generateTextFromAudio")
    public ResponseEntity<String> generateTextFromAudio(@RequestParam("file") MultipartFile audioFile) {
        if (!audioFile.isEmpty()) {
            Resource audioResource = audioFile.getResource();
            return ResponseEntity.ok(audioSpeechModelService.transcribeTextFromAudio(audioResource));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
