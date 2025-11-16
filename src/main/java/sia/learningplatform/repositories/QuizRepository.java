package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sia.learningplatform.entities.Module;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findByModule(Module module);

    @Query("SELECT q FROM Quiz q WHERE q.module.course.id = :courseId")
    List<Quiz> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = :id")
    Optional<Quiz> findByIdWithQuestions(@Param("id") Long id);
}
