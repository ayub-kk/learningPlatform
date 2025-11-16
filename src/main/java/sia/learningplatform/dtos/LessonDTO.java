package sia.learningplatform.dtos;

import java.util.List;

public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private String videoUrl;
    private Integer duration;
    private Integer orderIndex;
    private List<AssignmentDTO> assignments;

    // Constructors
    public LessonDTO() {}

    public LessonDTO(Long id, String title, String content, Integer orderIndex) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.orderIndex = orderIndex;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }

    public List<AssignmentDTO> getAssignments() { return assignments; }
    public void setAssignments(List<AssignmentDTO> assignments) { this.assignments = assignments; }
}
