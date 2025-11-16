package sia.learningplatform.dtos;

public class AnswerOptionDTO {
    private Long id;
    private String text;
    private Boolean isCorrect;

    // Constructors
    public AnswerOptionDTO() {}

    public AnswerOptionDTO(Long id, String text, Boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
}
