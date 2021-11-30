package bg.stoyank.footballtix.registration.confirmationtoken;

import bg.stoyank.footballtix.registration.confirmationtoken.exception.ConfirmationTokenNotFoundException;
import bg.stoyank.footballtix.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public String createConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);
        saveConfirmationToken(confirmationToken);
        return token;
    }

    public ConfirmationToken getConfirmationToken(String token)
            throws ConfirmationTokenNotFoundException {
        if (!tokenExists(token))
            throw new ConfirmationTokenNotFoundException(String.format("Token {%s} not found", token));

        return confirmationTokenRepository.getByToken(token);
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
