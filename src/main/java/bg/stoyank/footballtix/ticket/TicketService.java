package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.pdf.PdfService;
import bg.stoyank.footballtix.qr.QrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

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
            qrService.createQRCode(address);
        } catch (Exception e) {
            System.out.println(e);
        }
        pdfService.createTicket(order);
        emailService.sendTicket(email, "FootballTix order ticket.", order.getId());
        File ticket = new File("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/ticket/Ticket #" + order.getId() + ".pdf");
        if (ticket.delete()) log.info("Deleted file: " + ticket.getName());
        else log.error("Failed to delete file: " + ticket.getName());
        return "Order sent";
    }
}
