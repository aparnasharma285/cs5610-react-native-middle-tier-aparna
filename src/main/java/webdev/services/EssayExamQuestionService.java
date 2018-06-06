package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayExamQuestionService {

    private EssayExamQuestionRepository essayExamQuestionRepository;
    private ExamRepository examRepository;

    @Autowired
    public EssayExamQuestionService(EssayExamQuestionRepository essayExamQuestionRepository,
                                    ExamRepository examRepository) {
        this.essayExamQuestionRepository = essayExamQuestionRepository;
        this.examRepository = examRepository;
    }


    @GetMapping("/api/exam/{eid}/essay")
    public List<EssayExamQuestion> findEssayForExam(@PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);
        if (exam != null) {

            return essayExamQuestionRepository.findEssayForExam(exam, "Essay");
        }

        return null;
    }

    @GetMapping("/api/essay/{eid}")
    public EssayExamQuestion findEssayExamQuestionById(@PathVariable("eid") int eid) {

        return essayExamQuestionRepository.findById(eid).orElse(null);
    }


    @PostMapping("/api/exam/{eid}/essay")
    public EssayExamQuestion createEssayForExam(@RequestBody EssayExamQuestion newQuestion,
                                                @PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);

        if (exam != null) {

            newQuestion.setExam(exam);
            newQuestion.setTypeOfQuestion("Essay");
            return essayExamQuestionRepository.save(newQuestion);
        }

        return null;
    }


    @PutMapping("/api/essay/{eid}")
    public EssayExamQuestion updateEssayexamQuestion(@RequestBody EssayExamQuestion updatedQuestion, @PathVariable("eid") int eid) {

        EssayExamQuestion existing = essayExamQuestionRepository.findById(eid).orElse(null);

        if (existing != null) {

            int points = updatedQuestion.getPoints();
            String description = updatedQuestion.getDescription();
            String instructions = updatedQuestion.getInstructions();
            String title = updatedQuestion.getTitle();

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

            return essayExamQuestionRepository.save(existing);
        }

        return null;
    }


    @DeleteMapping("/api/exam/essay/{fid}")
    public void deleteEssay(@PathVariable("fid") int fid) {

        EssayExamQuestion question = essayExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            essayExamQuestionRepository.delete(question);
        }
    }

}
