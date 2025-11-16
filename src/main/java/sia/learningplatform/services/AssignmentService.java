package sia.learningplatform.services;

import sia.learningplatform.repositories.*;
import sia.learningplatform.entities.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    public Assignment createAssignment(String title, String description, Long lessonId,
                                       LocalDateTime dueDate, Integer maxScore) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));

        Assignment assignment = new Assignment(title, description, lesson, dueDate, maxScore);
        return assignmentRepository.save(assignment);
    }

    public Submission submitAssignment(Long assignmentId, Long studentId, String content, String fileUrl) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id: " + assignmentId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        // Check if already submitted
        if (submissionRepository.findByStudentAndAssignment(student, assignment).isPresent()) {
            throw new IllegalArgumentException("Assignment already submitted by this student");
        }

        // Check if due date has passed
        if (assignment.getDueDate() != null && LocalDateTime.now().isAfter(assignment.getDueDate())) {
            throw new IllegalArgumentException("Cannot submit assignment after due date");
        }

        Submission submission = new Submission(content, assignment, student);
        submission.setFileUrl(fileUrl);

        return submissionRepository.save(submission);
    }

    public Submission gradeSubmission(Long submissionId, Integer score, String feedback) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found with id: " + submissionId));

        if (score < 0 || score > submission.getAssignment().getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and " + submission.getAssignment().getMaxScore());
        }

        submission.setScore(score);
        submission.setFeedback(feedback);

        return submissionRepository.save(submission);
    }

    @Transactional(readOnly = true)
    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with id: " + assignmentId));
        return submissionRepository.findByAssignment(assignment);
    }

    @Transactional(readOnly = true)
    public List<Submission> getUngradedSubmissions(Long assignmentId) {
        return submissionRepository.findUngradedSubmissionsByAssignmentId(assignmentId);
    }

    @Transactional(readOnly = true)
    public Double getAverageScoreForAssignment(Long assignmentId) {
        return submissionRepository.findAverageScoreByAssignmentId(assignmentId);
    }
}
