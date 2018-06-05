package webdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webdev.models.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
}
