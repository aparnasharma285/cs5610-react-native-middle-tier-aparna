package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;


import java.util.List;

public interface EssayExamQuestionRepository extends JpaRepository <EssayExamQuestion,Integer> {

    @Query("select f from BaseExamQuestion f where f.exam=:exam and f.typeOfQuestion=:qtype")
    public List<EssayExamQuestion> findEssayForExam(@Param("exam") Exam exam,
                                                               @Param("qtype") String qtype);
}
