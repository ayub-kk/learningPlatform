package sia.learningplatform.dtos;

import java.util.List;

public class ModuleDTO {
    private Long id;
    private String title;
    private String description;
    private Integer orderIndex;
    private List<LessonDTO> lessons;
    private QuizDTO quiz;

    // Constructors
    public ModuleDTO() {}

    public ModuleDTO(Long id, String title, String description, Integer orderIndex) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.orderIndex = orderIndex;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }

    public List<LessonDTO> getLessons() { return lessons; }
    public void setLessons(List<LessonDTO> lessons) { this.lessons = lessons; }

    public QuizDTO getQuiz() { return quiz; }
    public void setQuiz(QuizDTO quiz) { this.quiz = quiz; }
}
