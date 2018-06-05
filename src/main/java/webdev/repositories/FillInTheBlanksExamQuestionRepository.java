package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;

import java.util.List;

public interface FillInTheBlanksExamQuestionRepository extends JpaRepository<FillInTheBlanksExamQuestion,Integer> {

    @Query("select f from FillInTheBlanksExamQuestion f where f.exam=:exam")
    public List<FillInTheBlanksExamQuestion> findBlanksForExam(@Param("exam") Exam exam);
}
