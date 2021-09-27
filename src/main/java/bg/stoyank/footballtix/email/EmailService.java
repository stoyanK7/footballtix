package bg.stoyank.footballtix.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {
    private final JavaMailSender mailSender;
    private static final String EMAIL_ENCODING = "utf-8";
    private static final String EMAIL_FROM = "noreply@footballtix.com";

    @Override
    @Async
    public void send(String to, String subject, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, EMAIL_ENCODING);

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(EMAIL_FROM);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("failed to send email ", e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
