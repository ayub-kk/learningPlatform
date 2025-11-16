package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {

    List<QuizSubmission> findByStudent(User student);

    List<QuizSubmission> findByQuiz(Quiz quiz);

    Optional<QuizSubmission> findByStudentAndQuiz(User student, Quiz quiz);

    @Query("SELECT AVG(qs.score) FROM QuizSubmission qs WHERE qs.quiz.id = :quizId")
    Double findAverageScoreByQuizId(@Param("quizId") Long quizId);

    @Query("SELECT COUNT(qs) FROM QuizSubmission qs WHERE qs.quiz.id = :quizId AND qs.score >= qs.quiz.passingScore")
    Long countPassingSubmissionsByQuizId(@Param("quizId") Long quizId);
}
