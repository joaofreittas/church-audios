package joao.app.churchaudios.controller.api;

import joao.app.churchaudios.controller.payload.AudioRequest;
import joao.app.churchaudios.model.Audio;
import joao.app.churchaudios.service.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/v1/api/audio")
@RestController
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Audio uploadAudioAndSave(
            @RequestParam(name = "audio") MultipartFile audio,
            @RequestBody AudioRequest audioRequest) throws IOException {
        return audioService.uploadAudioAndSave(audioRequest, audio);
    }

}
