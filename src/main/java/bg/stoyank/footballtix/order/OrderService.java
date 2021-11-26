package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.order.exception.OrderNotFoundException;
import bg.stoyank.footballtix.pdf.PdfService;
import bg.stoyank.footballtix.ticket.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private OrderRepository orderRepository;
    private PdfService pdfService;
    private EmailService emailService;
    private TicketService ticketService;

    public void createOrder(Order order) {
        orderRepository.save(order);
        pdfService.createReceipt(order);
        emailService.sendReceipt(order.getEmail(), "FootballTix order receipt.", order.getId());
        String receiptPath = "/media/stoyank/Elements/University/Semester 3/footballtix/tmp/receipt/Order #" + order.getId() + ".pdf";
        try {
            Files.delete(Paths.get(receiptPath));
        } catch(Exception e) {
            log.error(e.toString());
        }

        ticketService.sendTicket(order.getEmail(), order);
    }

    public List<Order> getAllOrdersByAccountEmail(String email) {
        return orderRepository.getAllByAccountEmailEqualsOrderByTransactionDateTimeDesc(email);
    }

    public Order getOrderById(int orderId) {
        if (orderExistsById(orderId)) {
            return orderRepository.getById((long) orderId);
        }
        throw new OrderNotFoundException("Could not find order with id: " + orderId + ".");
    }

    private boolean orderExistsById(int orderId) {
        return orderRepository.existsById((long) orderId);
    }
}
