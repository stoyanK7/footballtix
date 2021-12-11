package bg.stoyank.footballtix.registration;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationToken;
import bg.stoyank.footballtix.registration.confirmationtoken.ConfirmationTokenService;
import bg.stoyank.footballtix.registration.confirmationtoken.exception.ConfirmationTokenExpiredException;
import bg.stoyank.footballtix.registration.confirmationtoken.exception.EmailConfirmedException;
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

        sendConfirmationToken(registrationRequest.getEmail());

        return token;
    }

    public void sendConfirmationToken(String email) {
        User user = userService.getUserByEmail(email);
        String token = confirmationTokenService.createConfirmationToken(user);

        emailService.send(email,
                "Confirm your email",
                emailTemplateService.buildRegistrationConfirmationEmail(
                        user.getFullName(),
                        "http://localhost:3000/confirm-token/" + token));
    }

    @Transactional
    public String confirmToken(String token)
            throws EmailConfirmedException, ConfirmationTokenExpiredException {
        ConfirmationToken confirmationToken
                = confirmationTokenService.getConfirmationToken(token);

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now()))
            throw new ConfirmationTokenExpiredException("Token {" + token + "} has expired.");

        userService.enableUser(confirmationToken.getUser().getEmail());
        confirmationTokenService.deleteToken(token);
        return "Token is confirmed";
    }
}