package sia.learningplatform.services;

import org.springframework.context.annotation.Profile;
import sia.learningplatform.entities.User;
import sia.learningplatform.entities.UserProfile;
import sia.learningplatform.repositories.*;
import sia.learningplatform.entities.enums.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email, Role role) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        User user = new User(name, email, role);
        return userRepository.save(user);
    }

    public User createUserWithProfile(String name, String email, Role role, String bio) {
        User user = createUser(name, email, role);

        UserProfile profile = new UserProfile(user, bio);
        user.setProfile(profile);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public List<User> findAllTeachers() {
        return userRepository.findByRole(Role.TEACHER);
    }

    @Transactional(readOnly = true)
    public List<User> findAllStudents() {
        return userRepository.findByRole(Role.STUDENT);
    }

    @Transactional(readOnly = true)
    public List<User> findStudentsByCourse(Long courseId) {
        return userRepository.findStudentsByCourseId(courseId);
    }

    public User updateUserProfile(Long userId, String bio, String avatarUrl, String phone, String location) {
        User user = findById(userId);
        UserProfile profile = (UserProfile) user.getProfile();

        if (profile == null) {
            profile = new UserProfile(user, bio);
            user.setProfile(profile);
        }

        profile.setBio(bio);
        profile.setAvatarUrl(avatarUrl);
        profile.setPhone(phone);
        profile.setLocation(location);

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public boolean isUserEnrolledInCourse(Long userId, Long courseId) {
        return userRepository.findStudentsByCourseId(courseId).stream()
                .anyMatch(user -> user.getId().equals(userId));
    }
}
