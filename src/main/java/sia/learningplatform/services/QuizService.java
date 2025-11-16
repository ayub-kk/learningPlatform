package sia.learningplatform.services;

import sia.learningplatform.entities.*;
import sia.learningplatform.entities.Module;
import sia.learningplatform.entities.enums.QuestionType;
import sia.learningplatform.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public Quiz createQuiz(String title, String description, Long moduleId, Integer timeLimit, Integer passingScore) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException("Module not found with id: " + moduleId));

        Quiz quiz = new Quiz(title, module);
        quiz.setDescription(description);
        quiz.setTimeLimit(java.time.Duration.ofMinutes(timeLimit));
        quiz.setPassingScore(passingScore);

        return quizRepository.save(quiz);
    }

    public Question addQuestionToQuiz(Long quizId, String text, QuestionType type) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        Question question = new Question(text, type, quiz);
        quiz.getQuestions().add(question);

        quizRepository.save(quiz);
        return question;
    }

    public AnswerOption addAnswerOptionToQuestion(Long questionId, String text, Boolean isCorrect) {
        // Implementation would require QuestionRepository
        // This is simplified for demonstration
        return null;
    }

    public QuizSubmission submitQuiz(Long quizId, Long studentId, Integer score, Integer correctAnswers, Integer totalQuestions) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        // Check if already submitted
        if (quizSubmissionRepository.findByStudentAndQuiz(student, quiz).isPresent()) {
            throw new IllegalArgumentException("Quiz already submitted by this student");
        }

        QuizSubmission submission = new QuizSubmission(quiz, student);
        submission.setScore(score);
        submission.setCorrectAnswers(correctAnswers);
        submission.setTotalQuestions(totalQuestions);

        return quizSubmissionRepository.save(submission);
    }

    @Transactional(readOnly = true)
    public List<QuizSubmission> getQuizSubmissionsByStudent(Long studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        return quizSubmissionRepository.findByStudent(student);
    }

    @Transactional(readOnly = true)
    public List<QuizSubmission> getQuizSubmissionsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));
        return quizSubmissionRepository.findByQuiz(quiz);
    }
}
