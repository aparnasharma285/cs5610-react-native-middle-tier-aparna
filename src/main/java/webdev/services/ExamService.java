package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {

    ExamRepository examRepository;
    TopicRepository topicRepository;

    @Autowired
    public ExamService (ExamRepository examRepository,TopicRepository topicRepository){
        this.examRepository = examRepository;
        this.topicRepository = topicRepository;
    }


    @GetMapping("/api/exam")
    public List<Exam> findAllExams(){

        return examRepository.findAll();
    }


    @GetMapping("/api/exam/{eid}")
    public Exam findExamByid(@PathVariable("eid") int eid){

        return examRepository.findById(eid).orElse(null);
    }

    @GetMapping("/api/topic/{tid}/exam")
    public List<Exam> findExamByTopic(@PathVariable("tid") int tid){

        Topic topic = topicRepository.findById(tid).orElse(null);

        if(topic != null){

            return examRepository.findExamsForTopic(topic,"Exam");
        }

        return null;
    }


    @PostMapping("/api/topic/{tid}/exam")
    public Exam createExam(@RequestBody Exam newExam, @PathVariable("tid") int tid){

        int examId = newExam.getId();
        Exam existing = examRepository.findById(examId).orElse(null);
        Topic topic = topicRepository.findById(tid).orElse(null);

        if ((existing == null) && (topic != null)) {


            newExam.setTopic(topic);
            newExam.setWidgetType("Exam");
            return examRepository.save(newExam);
        }

        return existing;
    }


    @DeleteMapping("/api/exam/{eid}")
    public void deleteExam(@PathVariable("eid") int eid) {

        Exam existing = examRepository.findById(eid).orElse(null);

        if (existing != null) {
            examRepository.delete(existing);
        }
    }


    @PutMapping("/api/exam/{eid}")
    public Exam updateExam(@RequestBody Exam newExam, @PathVariable("eid") int eid){

        Exam existing = examRepository.findById(eid).orElse(null);

        String name = newExam.getName();
        String widgetType = newExam.getWidgetType();
        Topic topic = newExam.getTopic();
        List<BaseExamQuestion> questions = newExam.getQuestions();

        if(existing != null){

            if( name!= null){
                existing.setName(name);
            }

            if(widgetType != null){
                existing.setWidgetType(widgetType);
            }

            if (topic != null){
                existing.setTopic(topic);
            }

            existing.setQuestions(questions);

            return examRepository.save(existing);
        }

        return null;
    }

}
