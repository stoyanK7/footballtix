package bg.stoyank.footballtix.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    boolean existsByEmail(String email);
}
