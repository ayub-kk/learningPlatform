package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByLesson(Lesson lesson);

    @Query("SELECT a FROM Assignment a WHERE a.lesson.module.course.id = :courseId")
    List<Assignment> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT a FROM Assignment a LEFT JOIN FETCH a.submissions WHERE a.id = :id")
    Optional<Assignment> findByIdWithSubmissions(@Param("id") Long id);

    @Query("SELECT a FROM Assignment a WHERE a.dueDate < CURRENT_TIMESTAMP AND a.id NOT IN (SELECT s.assignment.id FROM Submission s WHERE s.student.id = :studentId)")
    List<Assignment> findOverdueAssignmentsForStudent(@Param("studentId") Long studentId);
}
