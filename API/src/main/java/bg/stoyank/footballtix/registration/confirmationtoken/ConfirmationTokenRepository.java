package bg.stoyank.footballtix.registration.confirmationtoken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken getByToken(String token);

    boolean existsByToken(String token);

    void deleteByToken(String token);

    void deleteAllByUserEmail(String email);
}
