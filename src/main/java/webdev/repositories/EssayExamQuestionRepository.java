package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.EssayExamQuestion;

public interface EssayExamQuestionRepository extends JpaRepository <EssayExamQuestion,Integer> {
}
