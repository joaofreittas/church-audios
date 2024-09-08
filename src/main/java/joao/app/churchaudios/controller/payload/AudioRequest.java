package joao.app.churchaudios.controller.payload;

import java.io.File;
import java.time.OffsetDateTime;

public record AudioRequest(
        String author,
        String title,
        String subTitle,
        OffsetDateTime date
) {
}
