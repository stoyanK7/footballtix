package bg.stoyank.footballtix.pdf;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import bg.stoyank.footballtix.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class PdfService {
    private final static String PROJECT_PATH =
            new File("").getAbsolutePath();
    private final static File RECEIPT_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Receipt.pdf");
    private final static String RECEIPT_SAVE_PATH =
            PROJECT_PATH + "/tmp/receipt/";
    private final static File TICKET_TEMPLATE =
            new File(PROJECT_PATH + "/src/main/resources/templates/Ticket.pdf");
    private final static String QR_CODE_PATH =
            PROJECT_PATH + "/tmp/qr/QR.png";
    private final static String TICKET_SAVE_PATH =
            PROJECT_PATH + "/tmp/ticket/";

    public void createReceipt(Order order) {
        try {
            PDDocument pDDocument = PDDocument.load(RECEIPT_TEMPLATE);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

            pDAcroForm.refreshAppearances();

            pDAcroForm.getField("tbxOrderId")
                    .setValue(order.getId().toString());
            pDAcroForm.getField("tbxFullName")
                    .setValue(order.getFullName());
            pDAcroForm.getField("tbxEmail")
                    .setValue(order.getEmail());
            pDAcroForm.getField("tbxPhoneNumber")
                    .setValue(order.getMobilePhone());
            pDAcroForm.getField("tbxAddress")
                    .setValue(order.getAddress());
            pDAcroForm.getField("tbxCity")
                    .setValue(order.getCity());
            pDAcroForm.getField("tbxCountry")
                    .setValue(order.getCountry());
            pDAcroForm.getField("tbxPostcode")
                    .setValue(order.getPostcode());
            pDAcroForm.getField("tbxTransactionId")
                    .setValue(order.getTransactionId());
            pDAcroForm.getField("tbxTransactionTime")
                    .setValue(order.getTransactionDateTime().toString());

            FootballMatch orderFootballMatch = order.getFootballMatch();
            pDAcroForm.getField("tbxMatch")
                    .setValue(orderFootballMatch.getHomeTeam() + " vs " + orderFootballMatch.getAwayTeam());
            pDAcroForm.getField("tbxDate")
                    .setValue(orderFootballMatch.getStartingDateTime().toString());
            pDAcroForm.getField("tbxPlace")
                    .setValue(orderFootballMatch.getStadium() + ", " + orderFootballMatch.getLocation());

            pDAcroForm.getField("tbxTotal")
                    .setValue("Total: €" +
                            String.format("%.2f", order.getFootballMatch().getPricePerTicket()));

            pDAcroForm.flatten();
            String fileName = "Order #" + order.getId() + ".pdf";
            pDDocument.save(RECEIPT_SAVE_PATH + fileName);
            pDDocument.close();
            log.info("Created PDF file: " + fileName);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public void createTicket(Order order) {
        try {
            PDDocument pDDocument = PDDocument.load(TICKET_TEMPLATE);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            pDAcroForm.refreshAppearances();

            PDImageXObject pdImage = PDImageXObject.createFromFile(QR_CODE_PATH,
                    pDDocument);
            PDPage page = pDDocument.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(pDDocument,
                    page, PDPageContentStream.AppendMode.APPEND, true, true);
            contentStream.drawImage(pdImage, 0, 30);
            contentStream.close();

            FootballMatch orderFootballMatch = order.getFootballMatch();
            pDAcroForm.getField("tbxHomeTeam")
                    .setValue(orderFootballMatch.getHomeTeam().toUpperCase());
            pDAcroForm.getField("tbxAwayTeam")
                    .setValue(orderFootballMatch.getAwayTeam().toUpperCase());
            pDAcroForm.getField("tbxTicketNumber")
                    .setValue("TICKET NUMBER: " + order.getId().toString());
            pDAcroForm.getField("tbxSeat")
                    .setValue("Seat: " + order.getId().toString());

            // TODO: parse date
            pDAcroForm.getField("tbxDate")
                    .setValue(orderFootballMatch.getStartingDateTime().toString());
            pDAcroForm.getField("tbxStadium")
                    .setValue(orderFootballMatch.getStadium().toUpperCase());

            pDAcroForm.getField("tbxPrice")
                    .setValue("Price: €" + String.format("%.2f", order.getFootballMatch().getPricePerTicket()));

            pDAcroForm.flatten();
            String fileName = "Ticket #" + order.getId() + ".pdf";
            pDDocument.save(TICKET_SAVE_PATH + fileName);
            pDDocument.close();
            log.info("Created PDF file: " + fileName);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
