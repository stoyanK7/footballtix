package bg.stoyank.footballtix.security.auth;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.security.resettoken.ResetToken;
import bg.stoyank.footballtix.security.resettoken.ResetTokenService;
import bg.stoyank.footballtix.user.UserService;
import bg.stoyank.footballtix.user.exception.PasswordsDoNotMatchException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private EmailService emailService;
    private ResetTokenService resetTokenService;
    private UserService userService;
    private EmailTemplateService emailTemplateService;

    public void sendRecoveryEmail(String email) {
        userService.getUserByEmail(email);
        String token = UUID.randomUUID().toString();
        ResetToken resetToken = new ResetToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                email);
        resetTokenService.saveResetToken(resetToken);
        String link = "http://20.124.233.164:3000/reset-password?token=" + token;
        emailService.send(email,
                "Reset your password.",
                emailTemplateService.buildPasswordRecoveryEmail(email, link));
    }

    public String resetPassword(String token, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword))
            throw new PasswordsDoNotMatchException("Provided passwords do not match");
        String email = resetTokenService.getResetToken(token).getEmail();
        userService.updatePassword(email, newPassword, confirmPassword);
        return "Password reset successfully!";
    }
}
