package joao.app.churchaudios.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class S3AudioService {

    private static final String BUCKET_NAME = "upload-audios";
    private final AmazonS3 s3Client;

    public String uploadAudio(MultipartFile audioFile, String key) {
        var file = convertMultipartFileToFile(audioFile);
        var fileName = System.currentTimeMillis() + "_" + file.getName();

        s3Client.putObject(BUCKET_NAME, key, file);

        return s3Client.getUrl(BUCKET_NAME, fileName).toString();
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        try {
            File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
            fos.close();
            return convertedFile;
        } catch (IOException e) {
            throw new RuntimeException("error for transform audio into a file");
        }
    }

}
