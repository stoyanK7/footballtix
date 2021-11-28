package bg.stoyank.footballtix.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private static final String EMAIL_ENCODING = "utf-8";
    private static final String EMAIL_FROM = "noreply@footballtix.com";

    @Async
    public void send(String to, String subject, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, EMAIL_ENCODING);

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(EMAIL_FROM);

            mailSender.send(mimeMessage);
            log.info(String.format("Email \"%s\" sent to %s", subject, to));
        } catch (MessagingException e) {
            log.error(String.format("Failed sending \"%s\" to %s", subject, to), e);
        }
    }

    @Async
    public void sendWithAttachment(String to, String subject,
                                   String email, File attachment) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage,
                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(EMAIL_FROM);
            helper.addAttachment(attachment.getName(), attachment);

            mailSender.send(mimeMessage);
            log.info(String.format("Email \"%s\" sent to %s", subject, to));
        } catch (Exception e) {
            log.error(String.format("Failed sending \"%s\" to %s", subject, to), e);
        }
    }
}
