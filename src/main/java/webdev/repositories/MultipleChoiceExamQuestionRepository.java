package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository extends JpaRepository<MultipleChoiceExamQuestion, Integer> {
}
