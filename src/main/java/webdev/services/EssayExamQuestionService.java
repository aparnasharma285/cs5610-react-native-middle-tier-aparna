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

            return essayExamQuestionRepository.findEssayForExam(exam);
        }

        return null;
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


    @DeleteMapping("/api/exam/essay/{fid}")
    public void deleteEssay(@PathVariable("fid") int fid) {

        EssayExamQuestion question = essayExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            essayExamQuestionRepository.delete(question);
        }
    }

}
