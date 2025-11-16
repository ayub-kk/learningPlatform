package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sia.learningplatform.entities.Module;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModule(Module module);

    List<Lesson> findByModuleOrderByOrderIndex(Module module);
}
