package bg.stoyank.footballtix.security.resettoken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResetTokenService {
    private ResetTokenRepository resetTokenRepository;

    public void saveResetToken(ResetToken resetToken) {
        resetTokenRepository.save(resetToken);
    }

    public ResetToken getResetToken(String token) {
        if (!tokenExists(token))
            throw new ResetTokenNotFoundException("Token {" + token + "} not found.");

        return resetTokenRepository.getByToken(token);
    }

    private boolean tokenExists(String token) {
        return resetTokenRepository.existsByToken(token);
    }
}
