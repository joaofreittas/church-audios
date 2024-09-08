package joao.app.churchaudios.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
@Document(value = "audios")
public class Audio {

    @Id
    private String id;
    private String author;
    private String name;
    private String key;
    private String title;
    private String subTitle;
    private String retreat;
    private String url;
    private OffsetDateTime date;
    private OffsetDateTime createdAt;

}
