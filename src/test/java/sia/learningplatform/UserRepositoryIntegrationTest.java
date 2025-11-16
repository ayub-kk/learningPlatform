package sia.learningplatform;

import sia.learningplatform.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import sia.learningplatform.entities.enums.Role;
import sia.learningplatform.repositories.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndFindUser() {
        // Given
        User user = new User("John Doe", "john@example.com", Role.STUDENT);

        // When
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("john@example.com", foundUser.get().getEmail());
        assertEquals(Role.STUDENT, foundUser.get().getRole());
    }

    @Test
    public void testFindByEmail() {
        // Given
        User user = new User("Jane Smith", "jane@example.com", Role.TEACHER);
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByEmail("jane@example.com");

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("Jane Smith", foundUser.get().getName());
    }

    @Test
    public void testFindByRole() {
        // Given
        userRepository.save(new User("Student1", "student1@example.com", Role.STUDENT));
        userRepository.save(new User("Teacher1", "teacher1@example.com", Role.TEACHER));
        userRepository.save(new User("Student2", "student2@example.com", Role.STUDENT));

        // When
        List<User> students = userRepository.findByRole(Role.STUDENT);
        List<User> teachers = userRepository.findByRole(Role.TEACHER);

        // Then
        assertEquals(2, students.size());
        assertEquals(1, teachers.size());
    }
}
