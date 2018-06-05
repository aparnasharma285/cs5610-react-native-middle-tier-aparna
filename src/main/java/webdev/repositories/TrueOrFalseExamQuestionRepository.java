package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.models.TrueOrFalseExamQuestion;

import java.util.List;

public interface TrueOrFalseExamQuestionRepository extends JpaRepository<TrueOrFalseExamQuestion,Integer> {

    @Query("select f from TrueOrFalseExamQuestion f where f.exam=:exam")
    public List<TrueOrFalseExamQuestion> findTrueFalseForExam(@Param("exam") Exam exam);
}
