package dev.andre.spring_ai_java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;

@Service
@Slf4j
public class ImageModelService {

    private final ImageModel opeanAiImageModel;
    private final ImageModel stabilityAiImageModel;

    // Using Qualifier so Spring knows which model bean should be used
    public ImageModelService(@Qualifier("openAiImageModel") ImageModel opeanAiImageModel,
                             @Qualifier("stabilityAiImageModel") ImageModel stabilityAiImageModel) {
        this.opeanAiImageModel = opeanAiImageModel;
        this.stabilityAiImageModel = stabilityAiImageModel;
    }

    //Here is service for OpenAI which requires a paid subscription
    public byte[] generateOpenAiImage(String prompt) {

        log.info("Generating OpenAI image for prompt: {}", prompt);

        ImageResponse imageResponse = opeanAiImageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .quality("hd")
                        .responseFormat("b64_json")
                        .build()));

        String b64Json = Objects.requireNonNull(imageResponse.getResult()).getOutput().getB64Json();
        return Base64.getDecoder().decode(b64Json);
    }

    //Here is service for Stability AI which is free to use
    public byte[] generateStabilityAiImage(String prompt) {

        log.info("Generating Stability AI image for prompt: {}", prompt);

        ImageResponse imageResponse = stabilityAiImageModel.call(new ImagePrompt(prompt,
                StabilityAiImageOptions.builder()
                        .stylePreset("cinematic")
                        .N(1)
                        .height(512)
                        .width(512)
                        .build()));

        String b64Json = Objects.requireNonNull(imageResponse.getResult()).getOutput().getB64Json();
        return Base64.getDecoder().decode(b64Json);
    }
}
