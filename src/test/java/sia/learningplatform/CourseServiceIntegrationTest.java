package sia.learningplatform;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import sia.learningplatform.entities.*;
import sia.learningplatform.entities.enums.*;
import sia.learningplatform.repositories.*;
import sia.learningplatform.services.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class CourseServiceIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    private User teacher;

    @BeforeEach
    public void setUp() {
        teacher = new User("Dr. Smith", "smith@example.com", Role.TEACHER);
        userRepository.save(teacher);
    }

    @Test
    public void testCreateCourse() {
        // When
        Course course = courseService.createCourse(
                "Java Programming",
                "Learn Java from scratch",
                teacher.getId(),
                null,
                40,
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                99.99,
                "BEGINNER"
        );

        // Then
        assertNotNull(course.getId());
        assertEquals("Java Programming", course.getTitle());
        assertEquals(teacher.getId(), course.getTeacher().getId());
    }

    @Test
    public void testFindCoursesByTeacher() {
        // Given
        courseService.createCourse("Course 1", "Description 1", teacher.getId(), null,
                10, LocalDate.now(), LocalDate.now().plusMonths(1), 50.0, "BEGINNER");
        courseService.createCourse("Course 2", "Description 2", teacher.getId(), null,
                20, LocalDate.now(), LocalDate.now().plusMonths(2), 75.0, "INTERMEDIATE");

        // When
        List<Course> courses = courseService.findCoursesByTeacher(teacher.getId());

        // Then
        assertEquals(2, courses.size());
    }
}
