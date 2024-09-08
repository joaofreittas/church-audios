package joao.app.churchaudios.service;

import joao.app.churchaudios.controller.payload.AudioRequest;
import joao.app.churchaudios.model.Audio;
import joao.app.churchaudios.repository.AudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final S3AudioService s3AudioService;
    private final AudioRepository audioRepository;

    public Audio uploadAudioAndSave(AudioRequest audioRequest, MultipartFile audioFile) throws IOException {
        var key = generateKey(audioRequest);
        var urlAudioFile = s3AudioService.uploadAudio(audioFile, key);
        var audio = Audio.builder()
                .author(audioRequest.author())
                .title(audioRequest.title())
                .date(audioRequest.date())
                .subTitle(audioRequest.subTitle())
                .name(key)
                .key(key)
                .url(urlAudioFile)
                .retreat("")
                .createdAt(OffsetDateTime.now())
                .build();

        return audioRepository.save(audio);
    }

    private String generateKey(AudioRequest audioRequest) {
        final var UNDER_SCORE = "_";
        return UUID
                .randomUUID()
                .toString()
                .concat(audioRequest.title())
                .concat(UNDER_SCORE)
                .concat(audioRequest.author());
    }

}
