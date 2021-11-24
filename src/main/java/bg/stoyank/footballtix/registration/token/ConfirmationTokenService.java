package bg.stoyank.footballtix.registration.token;

import bg.stoyank.footballtix.registration.token.exception.ConfirmationTokenNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken getConfirmationToken(String token) throws ConfirmationTokenNotFoundException {
        if (!tokenExists(token)) {
            throw new ConfirmationTokenNotFoundException("Token {" + token + "} not found.");
        }
        return confirmationTokenRepository.getByToken(token);
    }

    public void setConfirmedAt(String token) {
        ConfirmationToken confirmationToken = getConfirmationToken(token);
        confirmationToken.setConfirmedAt(LocalDateTime.now());
    }

    private boolean tokenExists(String token) {
        return confirmationTokenRepository.existsByToken(token);
    }

    public void deleteToken(String token) {
        confirmationTokenRepository.deleteByToken(token);
    }

    public void deleteTokenByEmail(String email) {
        confirmationTokenRepository.deleteAllByUserEmail(email);
    }
}
