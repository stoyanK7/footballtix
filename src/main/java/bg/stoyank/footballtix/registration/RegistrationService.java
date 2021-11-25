package bg.stoyank.footballtix.registration;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.registration.token.ConfirmationToken;
import bg.stoyank.footballtix.registration.token.ConfirmationTokenService;
import bg.stoyank.footballtix.registration.token.exception.ConfirmationTokenExpiredException;
import bg.stoyank.footballtix.registration.token.exception.EmailConfirmedException;
import bg.stoyank.footballtix.user.User;
import bg.stoyank.footballtix.user.UserRole;
import bg.stoyank.footballtix.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;
    private EmailService emailService;
    private EmailTemplateService emailTemplateService;

    public String register(RegistrationRequest registrationRequest) {
        String token = userService.createUser(new User(
                registrationRequest.getFullName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                UserRole.USER
        ));

        emailService.send(registrationRequest.getEmail(),
                "Confirm your email",
                emailTemplateService.buildRegistrationConfirmationEmail(
                        registrationRequest.getFullName(),
                        "http://localhost:3000/confirm-token/" + token));

        return token;
    }

    @Transactional
    public String confirmToken(String token) throws EmailConfirmedException, ConfirmationTokenExpiredException {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailConfirmedException("Email " + confirmationToken.getUser().getEmail() + " is already confirmed on " + confirmationToken.getConfirmedAt() + ".");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenExpiredException("Token {" + token + "} has expired.");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "Token is confirmed";
    }
}
