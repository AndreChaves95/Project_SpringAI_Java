package dev.andre.spring_ai_java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioSpeechModelService {

    // Here using the implementation class instead of the interface as Spring does not provide it
    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    public byte[] convertTextToSpeech(String text) {

        TextToSpeechPrompt speechPrompt = new TextToSpeechPrompt(text,
                OpenAiAudioSpeechOptions.builder()
                        .model(OpenAiAudioSpeechOptions.DEFAULT_SPEECH_MODEL)
                        .voice(OpenAiAudioSpeechOptions.Voice.ALLOY)
                        .speed(OpenAiAudioSpeechOptions.DEFAULT_SPEED)
                        .responseFormat(OpenAiAudioSpeechOptions.AudioResponseFormat.MP3)
                        .build());

        TextToSpeechResponse response = openAiAudioSpeechModel.call(speechPrompt);
        return response.getResult().getOutput();
    }
}
