package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.pdf.PdfService;
import bg.stoyank.footballtix.qr.QrService;
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
    private QrService qrService;
    private EmailService emailService;
    private PdfService pdfService;

    public String confirmToken(String token, String fullName) {
        boolean valid = jwtService.validateJwtToken(token, fullName.replace("%20", " "));
        return valid ? "Token is valid" : "Token is invalid";
    }

    public String sendTicket(String email, Order order) {
        try {
            String jwt = jwtService.generateTicketJwtToken(order);
            String address = "http://localhost:8080/api/tickets/confirm?token=" + jwt + "&fullName=" + order.getFullName().replace(" ", "%20");
            qrService.createQrCode(address);
        } catch (Exception e) {
            log.error(e.toString());
        }
        pdfService.createTicket(order);
        emailService.sendTicket(email, "FootballTix order ticket.", order.getId());
        String ticketPath = "/media/stoyank/Elements/University/Semester 3/footballtix/tmp/ticket/Ticket #" + order.getId() + ".pdf";
        try {
            Files.delete(Paths.get(ticketPath));
        } catch (Exception e) {
            log.error(e.toString());
        }
        return "Order sent";
    }
}
