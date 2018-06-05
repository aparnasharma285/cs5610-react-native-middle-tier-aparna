package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository extends JpaRepository<TrueOrFalseExamQuestion,Integer> {
}
