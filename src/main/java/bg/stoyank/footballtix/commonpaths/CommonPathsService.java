package bg.stoyank.footballtix.commonpaths;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CommonPathsService {
    public final static String PROJECT_PATH =
            new File("").getAbsolutePath();
    public final static File RECEIPT_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Receipt.pdf");
    public final static String RECEIPT_SAVE_PATH =
            PROJECT_PATH + "/tmp/receipt/";
    public final static File TICKET_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Ticket.pdf");
    public final static String QR_CODE_PATH =
            PROJECT_PATH + "/tmp/qr/QR.png";
    public final static String TICKET_SAVE_PATH =
            PROJECT_PATH + "/tmp/ticket/";

    public String generateTicketPath(Long orderId) {
        return TICKET_SAVE_PATH + "Ticket #" + orderId + ".pdf";
    }

    public String generateReceiptPath(Long orderId) {
        return RECEIPT_SAVE_PATH + "Order #" + orderId + ".pdf";
    }
}
