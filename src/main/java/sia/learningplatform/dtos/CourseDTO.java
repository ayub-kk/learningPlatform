package sia.learningplatform.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String teacherName;
    private String categoryName;
    private Integer duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private String level;
    private LocalDateTime createdAt;
    private List<ModuleDTO> modules;
    private List<String> tags;

    // Constructors
    public CourseDTO() {}

    public CourseDTO(Long id, String title, String description, String teacherName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherName = teacherName;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<ModuleDTO> getModules() { return modules; }
    public void setModules(List<ModuleDTO> modules) { this.modules = modules; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
