package sia.learningplatform.entities;

import jakarta.persistence.*;
import sia.learningplatform.entities.enums.EnrollmentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    @Column(nullable = false)
    private LocalDateTime enrollDate;

    private LocalDateTime completionDate;
    private Double progress; // 0.0 to 1.0

    @PrePersist
    protected void onCreate() {
        enrollDate = LocalDateTime.now();
        status = EnrollmentStatus.ACTIVE;
        progress = 0.0;
    }

    // Constructors
    public Enrollment() {}

    public Enrollment(User student, Course course) {
        this.student = student;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    public LocalDateTime getEnrollDate() { return enrollDate; }
    public void setEnrollDate(LocalDateTime enrollDate) { this.enrollDate = enrollDate; }

    public LocalDateTime getCompletionDate() { return completionDate; }
    public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate; }

    public Double getProgress() { return progress; }
    public void setProgress(Double progress) { this.progress = progress; }
}
