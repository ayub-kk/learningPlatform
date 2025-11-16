package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sia.learningplatform.entities.Module;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByCourse(Course course);

    List<Module> findByCourseOrderByOrderIndex(Course course);
}
