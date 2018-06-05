package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository extends JpaRepository<FillInTheBlanksExamQuestion,Integer> {
}
