package sprinity.jysprinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprinity.jysprinity.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
