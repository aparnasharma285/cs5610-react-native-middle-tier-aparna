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

    @GetMapping("/api/choice/{eid}")
    public MultipleChoiceExamQuestion findMultipleChoiceQuestionById(@PathVariable("eid") int eid) {

        return multipleChoiceExamQuestionRepository.findById(eid).orElse(null);
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


    @PutMapping("/api/choice/{eid}")
    public MultipleChoiceExamQuestion updateChoicesExamQuestion(@RequestBody MultipleChoiceExamQuestion updatedQuestion, @PathVariable("eid") int eid) {

        MultipleChoiceExamQuestion existing = multipleChoiceExamQuestionRepository.findById(eid).orElse(null);

        if (existing != null) {

            int points = updatedQuestion.getPoints();
            String description = updatedQuestion.getDescription();
            String instructions = updatedQuestion.getInstructions();
            String title = updatedQuestion.getTitle();
            String options = updatedQuestion.getOptions();
            int correctOption = updatedQuestion.getCorrectOption();

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

            if (options != null) {
                existing.setOptions(options);
            }

            if (correctOption != existing.getCorrectOption()) {
                existing.setCorrectOption(correctOption);
            }

            return multipleChoiceExamQuestionRepository.save(existing);
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
