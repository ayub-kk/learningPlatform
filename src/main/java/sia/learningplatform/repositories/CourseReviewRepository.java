package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    List<CourseReview> findByCourse(Course course);

    List<CourseReview> findByStudent(User student);

    Optional<CourseReview> findByStudentAndCourse(User student, Course course);

    @Query("SELECT AVG(cr.rating) FROM CourseReview cr WHERE cr.course.id = :courseId")
    Double findAverageRatingByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT COUNT(cr) FROM CourseReview cr WHERE cr.course.id = :courseId")
    Long countByCourseId(@Param("courseId") Long courseId);
}
