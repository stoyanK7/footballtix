package bg.stoyank.footballtix.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);

    boolean existsByEmail(String email);

    UserFullName getFullNameByEmail(String email);

    UserPassword getPasswordByEmail(String email);
}
