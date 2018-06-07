package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Topic;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {

    private AssignmentRepository assignmentRepository;
    private TopicRepository topicRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, TopicRepository topicRepository) {

        this.assignmentRepository = assignmentRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/api/assignment")
    public List<Assignment> findAllAssignments() {

        return assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{aid}")
    public Assignment findAssignmentById(@PathVariable("aid") int aid) {

        return assignmentRepository.findById(aid).orElse(null);
    }

    @GetMapping("/api/topic/{tid}/assignment")
    public List<Assignment> findAssignmentForTopic(@PathVariable("tid") int tid) {

        Topic topic = topicRepository.findById(tid).orElse(null);

        if (topic != null) {

            return assignmentRepository.findAssignmentsForTopic(topic, "Assignment");
        }
        return null;
    }


    @PostMapping("/api/topic/{tid}/assignment")
    public Assignment createAssignment(@RequestBody Assignment newAssignment, @PathVariable("tid") int tid) {
        Topic topic = topicRepository.findById(tid).orElse(null);
        if (topic != null) {
            newAssignment.setWidgetType("Assignment");
            newAssignment.setTopic(topic);
            newAssignment.setId(Integer.MAX_VALUE);
            return assignmentRepository.save(newAssignment);
        }
        return null;
    }


    @DeleteMapping("/api/assignment/{aid}")
    public void deleteAssignment(@PathVariable("aid") int aid) {

        Assignment existing = assignmentRepository.findById(aid).orElse(null);

        if (existing != null) {
            assignmentRepository.delete(existing);
        }
    }


    @PutMapping("/api/assignment/{aid}")
    public Assignment updateAssignment(@RequestBody Assignment updatedAssignment, @PathVariable("aid") int aid){

        Assignment existing = assignmentRepository.findById(aid).orElse(null);

        if(existing !=null){

            int points = updatedAssignment.getPoints();
            String description = updatedAssignment.getDescription();
            String name = updatedAssignment.getName();
            String title = updatedAssignment.getTitle();

            if(points >= 0){
                existing.setPoints(points);
            }
            if(description != null){
                existing.setDescription(description);
            }
            if(name!= null){
                existing.setName(name);
            }
            if(title != null){
                existing.setTitle(title);
            }

            return assignmentRepository.save(existing);
        }

        return null;
    }

}
