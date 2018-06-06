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


    @GetMapping("/api/exam/{eid}/blanks")
    public List<FillInTheBlanksExamQuestion> findBlanksForExam(@PathVariable("eid") int eid) {

        Exam exam = examRepository.findById(eid).orElse(null);
        if (exam != null) {

            return fillInTheBlanksExamQuestionRepository.findBlanksForExam(exam,"FillInTheBlanks");
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


    @DeleteMapping("/api/exam/blanks/{fid}")
    public void deleteBlanks(@PathVariable("fid") int fid) {

        FillInTheBlanksExamQuestion question = fillInTheBlanksExamQuestionRepository.findById(fid).orElse(null);

        if (question != null) {

            fillInTheBlanksExamQuestionRepository.delete(question);
        }
    }

}
