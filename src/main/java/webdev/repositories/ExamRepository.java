package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.Exam;

public interface ExamRepository extends JpaRepository<Exam,Integer> {
}
