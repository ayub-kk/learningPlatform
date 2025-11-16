package sia.learningplatform.services;

import sia.learningplatform.entities.*;
import sia.learningplatform.entities.enums.Role;
import sia.learningplatform.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Course createCourse(String title, String description, Long teacherId,
                               Long categoryId, Integer duration, LocalDate startDate,
                               LocalDate endDate, Double price, String level) {

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));

        if (teacher.getRole() != Role.TEACHER && teacher.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("User must be a teacher to create courses");
        }

        Category category = null;
        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        }

        Course course = new Course(title, description, teacher);
        course.setCategory(category);
        course.setDuration(duration);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setPrice(price);
        course.setLevel(level);

        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Course findByIdWithModules(Long id) {
        return courseRepository.findByIdWithModules(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Course> findCoursesByTeacher(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
        return courseRepository.findByTeacher(teacher);
    }

    @Transactional(readOnly = true)
    public List<Course> findCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Course> searchCourses(String keyword) {
        return courseRepository.searchByKeyword(keyword);
    }

    @Transactional(readOnly = true)
    public List<Course> findCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }

    public Course updateCourse(Long courseId, String title, String description,
                               Integer duration, LocalDate startDate, LocalDate endDate,
                               Double price, String level) {

        Course course = findById(courseId);
        course.setTitle(title);
        course.setDescription(description);
        course.setDuration(duration);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setPrice(price);
        course.setLevel(level);

        return courseRepository.save(course);
    }

    public void addTagToCourse(Long courseId, Tag tag) {
        Course course = findById(courseId);
        course.getTags().add(tag);
        courseRepository.save(course);
    }

    public void removeTagFromCourse(Long courseId, Tag tag) {
        Course course = findById(courseId);
        course.getTags().remove(tag);
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        Course course = findById(courseId);
        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public boolean isCourseActive(Long courseId) {
        Course course = findById(courseId);
        LocalDate now = LocalDate.now();
        return course.getStartDate() != null && course.getEndDate() != null &&
                !now.isBefore(course.getStartDate()) && !now.isAfter(course.getEndDate());
    }
}
