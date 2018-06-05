package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Assignment;
import webdev.models.Topic;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {

    @Query("select w from  Widget w where w.topic=:topic and w.widgetType=:widgetType")
    public List<Assignment> findAssignmentsForTopic(@Param("topic") Topic topic, @Param("widgetType") String widgetType);
}
