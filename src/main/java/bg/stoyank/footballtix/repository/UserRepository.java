package bg.stoyank.footballtix.repository;

import bg.stoyank.footballtix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
