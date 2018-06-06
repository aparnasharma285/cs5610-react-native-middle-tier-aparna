package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseExamQuestionService {

    private BaseExamQuestionRepository baseExamQuestionRepository;
    private ExamRepository examRepository;

    @Autowired
    public BaseExamQuestionService (BaseExamQuestionRepository baseExamQuestionRepository,ExamRepository examRepository){
        this.baseExamQuestionRepository = baseExamQuestionRepository;
        this.examRepository = examRepository;
    }


    @GetMapping("/api/exam/questions")
    public List<BaseExamQuestion> findAllQuestions(){
        return baseExamQuestionRepository.findAll();
    }

    @GetMapping("/api/exam/{eid}/questions")
    public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("eid") int eid){

        Exam exam = examRepository.findById(eid).orElse(null);

        if(exam != null){

            return exam.getQuestions();
        }

        return null;
    }

    @PostMapping("/api/exam/{eid}/questions")
    public BaseExamQuestion createBaseQuestion(@RequestBody BaseExamQuestion baseExamQuestion,
                                               @PathVariable("eid") int eid){

        Exam exam = examRepository.findById(eid).orElse(null);

        if(exam != null){
            baseExamQuestion.setExam(exam);
            baseExamQuestion.setId(Integer.MAX_VALUE);
            return baseExamQuestionRepository.save(baseExamQuestion);
        }

        return null;

    }

    @DeleteMapping("/api/exam/questions/{qid}")
    public void deleteBaseQuestion(@PathVariable("qid") int qid){

        BaseExamQuestion question = baseExamQuestionRepository.findById(qid).orElse(null);

        if(question != null){

            baseExamQuestionRepository.delete(question);
        }
    }

}
