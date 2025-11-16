package sia.learningplatform.dtos;

import sia.learningplatform.entities.enums.QuestionType;

import java.util.List;

public class QuestionDTO {
    private Long id;
    private String text;
    private QuestionType type;
    private List<AnswerOptionDTO> options;

    // Constructors
    public QuestionDTO() {}

    public QuestionDTO(Long id, String text, QuestionType type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public QuestionType getType() { return type; }
    public void setType(QuestionType type) { this.type = type; }

    public List<AnswerOptionDTO> getOptions() { return options; }
    public void setOptions(List<AnswerOptionDTO> options) { this.options = options; }
}
