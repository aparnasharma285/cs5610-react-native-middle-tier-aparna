package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseExamQuestionService {


    private TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;
    private ExamRepository examRepository;

    @Autowired
    public TrueOrFalseExamQuestionService(TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository,
                                          ExamRepository examRepository) {
        this.trueOrFalseExamQuestionRepository = trueOrFalseExamQuestionRepository;
        this.examRepository = examRepository;
    }

    @GetMapping("/api/truefalse/{eid}")
    public TrueOrFalseExamQuestion findTrueOrFalseQuestionById(@PathVariable("eid") int eid) {

        return trueOrFalseExamQuestionRepository.findById(eid).orElse(null);
    }


    @GetMapping("/api/exam/{eid}/truefalse")
    public List<TrueOrFalseExamQuestion> findTrueFalseForExam(@PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);
        if (exam != null) {

            return trueOrFalseExamQuestionRepository.findTrueFalseForExam(exam, "TrueOrFalse");
        }

        return null;
    }


    @PostMapping("/api/exam/{eid}/truefalse")
    public TrueOrFalseExamQuestion createTrueFalseForExam(@RequestBody TrueOrFalseExamQuestion newQuestion,
                                                          @PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);

        if (exam != null) {

            newQuestion.setExam(exam);
            newQuestion.setTypeOfQuestion("TrueOrFalse");
            return trueOrFalseExamQuestionRepository.save(newQuestion);
        }

        return null;
    }


    @PutMapping("/api/truefalse/{eid}")
    public TrueOrFalseExamQuestion updateTrueFalseExamQuestion(@RequestBody TrueOrFalseExamQuestion updatedQuestion, @PathVariable("eid") int eid) {

        TrueOrFalseExamQuestion existing = trueOrFalseExamQuestionRepository.findById(eid).orElse(null);

        if (existing != null) {

            int points = updatedQuestion.getPoints();
            String description = updatedQuestion.getDescription();
            String instructions = updatedQuestion.getInstructions();
            String title = updatedQuestion.getTitle();
            Boolean isTrue = updatedQuestion.getIsTrue();

            if (points > 0) {
                existing.setPoints(points);
            }
            if (description != null) {
                existing.setDescription(description);
            }
            if (instructions != null) {
                existing.setInstructions(instructions);
            }
            if (title != null) {
                existing.setTitle(title);
            }
            existing.setIsTrue(isTrue);


            return trueOrFalseExamQuestionRepository.save(existing);
        }

        return null;
    }


    @DeleteMapping("/api/exam/truefalse/{fid}")
    public void deleteTrueFalse(@PathVariable("fid") int fid) {

        TrueOrFalseExamQuestion question = trueOrFalseExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            trueOrFalseExamQuestionRepository.delete(question);
        }
    }
}
