package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;

import java.util.List;

public interface MultipleChoiceExamQuestionRepository extends JpaRepository<MultipleChoiceExamQuestion, Integer> {


    @Query("select m from MultipleChoiceExamQuestion m where m.exam=:exam")
    public List<MultipleChoiceExamQuestion> findMCQForExam(@Param("exam") Exam exam);
}
