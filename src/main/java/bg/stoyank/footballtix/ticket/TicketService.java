package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.file.CommonPathsService;
import bg.stoyank.footballtix.file.FileService;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.pdf.PdfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    private JwtService jwtService;
    private EmailService emailService;
    private PdfService pdfService;
    private FileService fileService;
    private CommonPathsService commonPathsService;
    private EmailTemplateService emailTemplateService;

    public String confirmToken(String token, String fullName) {
        boolean valid = jwtService.validateJwtToken(token,
                fullName.replace("%20", " "));
        return valid ? "Token is valid" : "Token is invalid";
    }

    public String sendTicket(String email, Order order) {
        pdfService.createTicket(order);
        emailService.sendWithAttachment(email,
                "FootballTix order ticket.",
                emailTemplateService.buildTicketEmail(email),
                new File(commonPathsService.generateTicketPath(order.getId())));
        fileService.deleteTicket(order.getId());
        return "Order sent";
    }
}
