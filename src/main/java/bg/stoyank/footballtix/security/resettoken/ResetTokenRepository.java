package bg.stoyank.footballtix.security.resettoken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {
    ResetToken getByToken(String token);

    boolean existsByToken(String token);
}
