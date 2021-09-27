package bg.stoyank.footballtix.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken getByToken(String token);

    boolean existsByToken(String token);
}
