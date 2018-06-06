package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksExamQuestionService {

    private FillInTheBlanksExamQuestionRepository fillInTheBlanksExamQuestionRepository;
    private ExamRepository examRepository;

    @Autowired
    public FillInTheBlanksExamQuestionService(FillInTheBlanksExamQuestionRepository fillInTheBlanksExamQuestionRepository,
                                              ExamRepository examRepository) {
        this.fillInTheBlanksExamQuestionRepository = fillInTheBlanksExamQuestionRepository;
        this.examRepository = examRepository;
    }

    @GetMapping("/api/blanks/{eid}")
    public FillInTheBlanksExamQuestion findFillInTheBlankQuestionById(@PathVariable("eid") int eid) {

        return fillInTheBlanksExamQuestionRepository.findById(eid).orElse(null);
    }

    @GetMapping("/api/exam/{eid}/blanks")
    public List<FillInTheBlanksExamQuestion> findBlanksForExam(@PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);
        if (exam != null) {

            return fillInTheBlanksExamQuestionRepository.findBlanksForExam(exam, "FillInTheBlanks");
        }

        return null;
    }


    @PostMapping("/api/exam/{eid}/blanks")
    public FillInTheBlanksExamQuestion createBlanksForExam(@RequestBody FillInTheBlanksExamQuestion newQuestion,
                                                           @PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);

        if (exam != null) {

            newQuestion.setExam(exam);
            newQuestion.setTypeOfQuestion("FillInTheBlanks");
            return fillInTheBlanksExamQuestionRepository.save(newQuestion);
        }

        return null;
    }

    @PutMapping("/api/blanks/{eid}")
    public FillInTheBlanksExamQuestion updateBlankExamQuestion(@RequestBody FillInTheBlanksExamQuestion updatedQuestion, @PathVariable("eid") int eid) {

        FillInTheBlanksExamQuestion existing = fillInTheBlanksExamQuestionRepository.findById(eid).orElse(null);

        if (existing != null) {

            int points = updatedQuestion.getPoints();
            String description = updatedQuestion.getDescription();
            String instructions = updatedQuestion.getInstructions();
            String title = updatedQuestion.getTitle();
            String variables = updatedQuestion.getVariables();

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
            if (variables != null) {
                existing.setVariables(variables);
            }

            return fillInTheBlanksExamQuestionRepository.save(existing);
        }

        return null;
    }


    @DeleteMapping("/api/exam/blanks/{fid}")
    public void deleteBlanks(@PathVariable("fid") int fid) {

        FillInTheBlanksExamQuestion question = fillInTheBlanksExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            fillInTheBlanksExamQuestionRepository.delete(question);
        }
    }

}
