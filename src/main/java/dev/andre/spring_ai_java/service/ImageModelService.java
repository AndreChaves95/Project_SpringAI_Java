package dev.andre.spring_ai_java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageModelService {

    private final ImageModel imageModel;

    public String generateImage(String prompt) {
        ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .build()));

        return Objects.requireNonNull(imageResponse.getResult()).getOutput().getUrl();
    }
}
