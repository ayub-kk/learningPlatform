package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByAssignment(Assignment assignment);

    List<Submission> findByStudent(User student);

    Optional<Submission> findByStudentAndAssignment(User student, Assignment assignment);

    @Query("SELECT s FROM Submission s WHERE s.assignment.lesson.module.course.id = :courseId")
    List<Submission> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT s FROM Submission s WHERE s.assignment.id = :assignmentId AND s.score IS NULL")
    List<Submission> findUngradedSubmissionsByAssignmentId(@Param("assignmentId") Long assignmentId);

    @Query("SELECT AVG(s.score) FROM Submission s WHERE s.assignment.id = :assignmentId AND s.score IS NOT NULL")
    Double findAverageScoreByAssignmentId(@Param("assignmentId") Long assignmentId);
}
