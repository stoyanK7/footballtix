package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.file.CommonPathsService;
import bg.stoyank.footballtix.file.FileService;
import bg.stoyank.footballtix.footballmatch.FootballMatchService;
import bg.stoyank.footballtix.order.exception.OrderAlreadyExistsException;
import bg.stoyank.footballtix.order.exception.OrderNotFoundException;
import bg.stoyank.footballtix.pdf.PdfService;
import bg.stoyank.footballtix.ticket.TicketService;
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
    private EmailTemplateService emailTemplateService;
    private TicketService ticketService;
    private FileService fileService;
    private FootballMatchService footballMatchService;
    private CommonPathsService commonPathsService;


    public void createOrder(Order order) {
        orderRepository.save(order);
        footballMatchService
                .subtractTicketsAvailableByOne(order.getFootballMatch().getId());
        sendOrder(order);
        ticketService.sendTicket(order.getEmail(), order);
    }

    public void sendOrder(Order order) {
        pdfService.createReceipt(order);
        emailService.sendWithAttachment(order.getEmail(),
                "FootballTix order receipt.",
                emailTemplateService.buildReceiptEmail(order.getEmail()),
                new File(commonPathsService.generateReceiptPath(order.getId())));
        fileService.deleteReceipt(order.getId());
    }

    public List<Order> getAllOrdersByAccountEmail(String email) {
        return orderRepository.getAllByAccountEmailEqualsOrderByTransactionDateTimeDesc(email);
    }

    public Order getOrderById(int orderId) {
        if (!orderExistsById(orderId))
            throw new OrderNotFoundException(Integer.toString(orderId));

        return orderRepository.getById((long) orderId);
    }

    public List<Order> getAllOrdersByFootballMatchId(Long footballMatchId) {
        return orderRepository.getAllByFootballMatchId(footballMatchId);
    }

    private boolean orderExistsById(int orderId) {
        return orderRepository.existsById((long) orderId);
    }

    public void checkIfOrderExistsForNameAndEmail(String matchId, String email, String fullName) {
        if (orderRepository.existsByFootballMatchIdEqualsAndAccountEmailEqualsAndFullNameEquals(Long.parseLong(matchId), email, fullName))
            throw new OrderAlreadyExistsException("with name" + fullName);
    }
}
