package sia.learningplatform.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String description;
    private Duration timeLimit;
    private Integer passingScore;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizSubmission> submissions = new ArrayList<>();

    // Constructors
    public Quiz() {}

    public Quiz(String title, Module module) {
        this.title = title;
        this.module = module;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Duration getTimeLimit() { return timeLimit; }
    public void setTimeLimit(Duration timeLimit) { this.timeLimit = timeLimit; }

    public Integer getPassingScore() { return passingScore; }
    public void setPassingScore(Integer passingScore) { this.passingScore = passingScore; }

    public Module getModule() { return module; }
    public void setModule(Module module) { this.module = module; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public List<QuizSubmission> getSubmissions() { return submissions; }
    public void setSubmissions(List<QuizSubmission> submissions) { this.submissions = submissions; }
}
