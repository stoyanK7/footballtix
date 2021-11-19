package bg.stoyank.footballtix.security.resettoken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResetTokenService {
    private final ResetTokenRepository resetTokenRepository;

    public void saveResetToken(ResetToken resetToken) {
        resetTokenRepository.save(resetToken);
    }

    public ResetToken getResetToken(String token) {
        if (!tokenExists(token)) {
            throw new ResetTokenNotFoundException("Token {" + token + "} not found.");
        }
        return resetTokenRepository.getByToken(token);
    }

    public void setConfirmedAt(String token) {
        ResetToken resetToken = getResetToken(token);
        resetToken.setConfirmedAt(LocalDateTime.now());
    }

    private boolean tokenExists(String token) {
        return resetTokenRepository.existsByToken(token);
    }
}
