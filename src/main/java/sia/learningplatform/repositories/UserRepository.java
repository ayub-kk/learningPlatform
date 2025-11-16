package sia.learningplatform.repositories;

import sia.learningplatform.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sia.learningplatform.entities.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    @Query("SELECT u FROM User u JOIN u.enrollments e WHERE e.course.id = :courseId")
    List<User> findStudentsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT u FROM User u JOIN u.coursesTaught c WHERE c.id = :courseId")
    Optional<User> findTeacherByCourseId(@Param("courseId") Long courseId);

    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    Long countByRole(@Param("role") Role role);
}
