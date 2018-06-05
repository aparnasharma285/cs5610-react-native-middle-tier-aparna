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


    @GetMapping("/api/exam/{eid}/truefalse")
    public List<TrueOrFalseExamQuestion> findTrueFalseForExam(@PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);
        if (exam != null) {

            return trueOrFalseExamQuestionRepository.findTrueFalseForExam(exam);
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


    @DeleteMapping("/api/exam/truefalse/{fid}")
    public void deleteTrueFalse(@PathVariable("fid") int fid) {

        TrueOrFalseExamQuestion question = trueOrFalseExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            trueOrFalseExamQuestionRepository.delete(question);
        }
    }
}
