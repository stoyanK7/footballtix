package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.commonpaths.CommonPathsService;
import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.pdf.PdfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    private JwtService jwtService;
    private EmailService emailService;
    private PdfService pdfService;
    private CommonPathsService commonPathsService;

    public String confirmToken(String token, String fullName) {
        boolean valid = jwtService.validateJwtToken(token, fullName.replace("%20", " "));
        return valid ? "Token is valid" : "Token is invalid";
    }

    public String sendTicket(String email, Order order) {
        pdfService.createTicket(order);
        emailService.sendTicket(email, "FootballTix order ticket.", order.getId());
        try {
            Files.delete(Paths.get(commonPathsService.generateTicketPath(order.getId())));
        } catch (Exception e) {
            log.error(e.toString());
        }
        return "Order sent";
    }
}
