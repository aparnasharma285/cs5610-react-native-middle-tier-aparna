package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.Topic;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Integer> {


    @Query("select w from  Widget w where w.topic=:topic and w.widgetType=:widgetType")
    public List<Exam> findExamsForTopic(@Param("topic") Topic topic, @Param("widgetType") String widgetType);
}
