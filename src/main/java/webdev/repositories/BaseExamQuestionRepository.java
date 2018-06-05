package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.BaseExamQuestion;

public interface BaseExamQuestionRepository extends JpaRepository <BaseExamQuestion,Integer> {
}
