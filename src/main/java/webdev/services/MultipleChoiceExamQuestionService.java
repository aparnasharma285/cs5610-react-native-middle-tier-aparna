package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceExamQuestionService {

    private MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository;
    private ExamRepository examRepository;

    @Autowired
    public MultipleChoiceExamQuestionService(MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository,
                                             ExamRepository examRepository){
        this.multipleChoiceExamQuestionRepository = multipleChoiceExamQuestionRepository;
        this.examRepository = examRepository;
    }


    @GetMapping("/api/exam/{eid}/choice")
    public List<MultipleChoiceExamQuestion> findMCQForExam(@PathVariable("eid") int eid){

        Exam exam = examRepository.findById(eid).orElse(null);
        if(exam != null){

            return multipleChoiceExamQuestionRepository.findMCQForExam(exam,"MultipleChoice");
        }

        return null;
    }


    @PostMapping("/api/exam/{eid}/choice")
    public MultipleChoiceExamQuestion createMCQForExam(@RequestBody MultipleChoiceExamQuestion newQuestion,
                                                       @PathVariable("eid") int eid){

        Exam exam = examRepository.findById(eid).orElse(null);

        if(exam != null){

            newQuestion.setExam(exam);
            newQuestion.setTypeOfQuestion("MultipleChoice");
            return multipleChoiceExamQuestionRepository.save(newQuestion);
        }

        return null;
    }


    @DeleteMapping("/api/exam/choice/{cid}")
    public void deleteMCQ (@PathVariable("cid") int cid){

        MultipleChoiceExamQuestion question = multipleChoiceExamQuestionRepository.findById(cid).orElse(null);

        if(question != null){

            multipleChoiceExamQuestionRepository.delete(question);
        }
    }
}
