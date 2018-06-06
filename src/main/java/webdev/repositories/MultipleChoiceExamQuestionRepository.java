package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.MultipleChoiceExamQuestion;

import java.util.List;

public interface MultipleChoiceExamQuestionRepository extends JpaRepository<MultipleChoiceExamQuestion, Integer> {

    @Query("select f from BaseExamQuestion f where f.exam=:exam and f.typeOfQuestion=:qtype")
    public List<MultipleChoiceExamQuestion> findMCQForExam(@Param("exam") Exam exam,
                                                    @Param("qtype") String qtype);
}
