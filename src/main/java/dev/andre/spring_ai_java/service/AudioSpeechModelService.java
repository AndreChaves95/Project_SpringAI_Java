package dev.andre.spring_ai_java.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioSpeechModelService {

    // Here using the implementation class instead of the interface as Spring does not provide it
    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;
    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

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

    public String transcribeTextFromAudio(Resource audioResource) {

        AudioTranscriptionPrompt transcriptionPrompt = new AudioTranscriptionPrompt(audioResource,
                OpenAiAudioTranscriptionOptions.builder()
                        .responseFormat(OpenAiAudioTranscriptionOptions.DEFAULT_RESPONSE_FORMAT)
                        .model(OpenAiAudioTranscriptionOptions.DEFAULT_TRANSCRIPTION_MODEL)
                        .temperature(0f)
                        .language("en")  // Here we can select which language we want and it translates the audio to text in that language
                        .build());

        AudioTranscriptionResponse response = openAiAudioTranscriptionModel.call(transcriptionPrompt);
        return response.getResult().getOutput();
    }

}
