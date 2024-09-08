package joao.app.churchaudios.repository;

import joao.app.churchaudios.model.Audio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AudioRepository extends MongoRepository<Audio, String> {
}
