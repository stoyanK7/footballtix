package bg.stoyank.footballtix.file;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CommonPathsService {
    public static final String PROJECT_PATH =
            new File("").getAbsolutePath();
    public static final File RECEIPT_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Receipt.pdf");
    public static final String RECEIPT_SAVE_PATH =
            PROJECT_PATH + "/fbtxtmp/receipt/";
    public static final File TICKET_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Ticket.pdf");
    public static final String QR_CODE_PATH =
            PROJECT_PATH + "/fbtxtmp/qr/QR.png";
    public static final String TICKET_SAVE_PATH =
            PROJECT_PATH + "/fbtxtmp/ticket/";

    public String generateTicketPath(Long orderId) {
        return TICKET_SAVE_PATH + "Ticket #" + orderId + ".pdf";
    }

    public String generateReceiptPath(Long orderId) {
        return RECEIPT_SAVE_PATH + "Order #" + orderId + ".pdf";
    }
}
