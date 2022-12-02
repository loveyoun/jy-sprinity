package sprinity.jysprinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprinity.jysprinity.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    //Database에 저장 조회 기능구현 끝! 이 자체가 API임
    User findByUserId(String userId);
}
