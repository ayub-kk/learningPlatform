package sia.learningplatform.services;

import sia.learningplatform.entities.enums.EnrollmentStatus;
import sia.learningplatform.entities.enums.Role;
import sia.learningplatform.repositories.*;
import sia.learningplatform.entities.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Enrollment enrollStudentInCourse(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        // Check if already enrolled
        if (enrollmentRepository.findByStudentAndCourse(student, course).isPresent()) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        // Check if user is a student
        if (student.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("Only students can enroll in courses");
        }

        Enrollment enrollment = new Enrollment(student, course);
        return enrollmentRepository.save(enrollment);
    }

    @Transactional(readOnly = true)
    public Enrollment findEnrollment(Long studentId, Long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findEnrollmentsByStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        return enrollmentRepository.findByStudent(student);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findEnrollmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        return enrollmentRepository.findByCourse(course);
    }

    public Enrollment updateEnrollmentStatus(Long studentId, Long courseId, EnrollmentStatus status) {
        Enrollment enrollment = findEnrollment(studentId, courseId);
        enrollment.setStatus(status);

        if (status == EnrollmentStatus.COMPLETED) {
            enrollment.setCompletionDate(java.time.LocalDateTime.now());
            enrollment.setProgress(1.0);
        }

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateProgress(Long studentId, Long courseId, Double progress) {
        Enrollment enrollment = findEnrollment(studentId, courseId);

        if (progress < 0.0 || progress > 1.0) {
            throw new IllegalArgumentException("Progress must be between 0.0 and 1.0");
        }

        enrollment.setProgress(progress);

        if (progress >= 1.0) {
            enrollment.setStatus(EnrollmentStatus.COMPLETED);
            enrollment.setCompletionDate(java.time.LocalDateTime.now());
        }

        return enrollmentRepository.save(enrollment);
    }

    public void unenrollStudent(Long studentId, Long courseId) {
        Enrollment enrollment = findEnrollment(studentId, courseId);
        enrollmentRepository.delete(enrollment);
    }

    @Transactional(readOnly = true)
    public Long getEnrollmentCountByCourse(Long courseId) {
        return enrollmentRepository.countByCourseIdAndStatus(courseId, EnrollmentStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public boolean isStudentEnrolled(Long studentId, Long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).isPresent();
    }
}