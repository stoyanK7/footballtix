package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.footballmatch.FootballMatch;
import bg.stoyank.footballtix.footballmatch.exception.FootballMatchNotFoundException;
import bg.stoyank.footballtix.order.exception.OrderNotFoundException;
import bg.stoyank.footballtix.pdf.PdfService;
import bg.stoyank.footballtix.qr.QrService;
import bg.stoyank.footballtix.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private OrderRepository orderRepository;
    private PdfService pdfService;
    private EmailService emailService;
    private QrService qrService;
    private JwtService jwtService;

    public void createOrder(Order order) {
        orderRepository.save(order);
        pdfService.createReceipt(order);
        emailService.sendReceipt(order.getEmail(), "FootballTix order receipt.", order.getId());
        File receipt = new File("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/receipt/Order #" + order.getId() + ".pdf");
        if (receipt.delete()) log.info("Deleted file: " + receipt.getName());
        else log.error("Failed to delete file: " + receipt.getName());

        try {
            String jwt = jwtService.generateTicketJwtToken(order);
            String address = "http://localhost:8080/api/tickets/confirm?token=" + jwt + "&fullName=" + order.getFullName().replace(" ", "%20");
            qrService.createQRCode(address);
        } catch (Exception e) {
            System.out.println(e);
        }
        pdfService.createTicket(order);
        emailService.sendTicket(order.getEmail(), "FootballTix order ticket.", order.getId());
        File ticket = new File("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/ticket/Ticket #" + order.getId() + ".pdf");
        if (ticket.delete()) log.info("Deleted file: " + ticket.getName());
        else log.error("Failed to delete file: " + ticket.getName());
    }

    public List<Order> getAllOrdersByAccountEmail(String email) {
        return orderRepository.getAllByAccountEmailEquals(email);
    }

    public Order getOrderById(int orderId) {
        if (orderExistsById(orderId)) {
            return orderRepository.getById((long)orderId);
        }
        throw new OrderNotFoundException("Could not find order with id: " + orderId + ".");
    }

    private boolean orderExistsById(int orderId) {
        return orderRepository.existsById((long) orderId);
    }
}
