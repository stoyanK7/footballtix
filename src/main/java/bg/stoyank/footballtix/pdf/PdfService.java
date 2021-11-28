package bg.stoyank.footballtix.pdf;

import bg.stoyank.footballtix.file.CommonPathsService;
import bg.stoyank.footballtix.footballmatch.FootballMatch;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.qr.QrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PdfService {
    private QrService qrService;
    private JwtService jwtService;
    private CommonPathsService commonPathsService;

    public void createReceipt(Order order) {
        try {
            PDDocument pDDocument = PDDocument.load(CommonPathsService.RECEIPT_TEMPLATE);
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
            pDDocument.save(commonPathsService.generateReceiptPath(order.getId()));
            pDDocument.close();
            log.info("Created receipt PDF for order #" + order.getId());
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public void createTicket(Order order) {
        try {
            PDDocument pDDocument = PDDocument.load(CommonPathsService.TICKET_TEMPLATE);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            pDAcroForm.refreshAppearances();

            String jwt = jwtService.generateTicketJwtToken(order);
            String address = "http://localhost:8080/api/tickets/confirm?token=" + jwt + "&fullName=" + order.getFullName().replace(" ", "%20");
            qrService.createQrCode(address);

            PDImageXObject pdImage = PDImageXObject.createFromFile(
                    CommonPathsService.QR_CODE_PATH,
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

            pDAcroForm.getField("tbxDate")
                    .setValue(orderFootballMatch.getStartingDateTime().toString());
            pDAcroForm.getField("tbxStadium")
                    .setValue(orderFootballMatch.getStadium().toUpperCase());

            pDAcroForm.getField("tbxPrice")
                    .setValue("Price: €" + String.format("%.2f", order.getFootballMatch().getPricePerTicket()));

            pDAcroForm.flatten();
            pDDocument.save(commonPathsService.generateTicketPath(order.getId()));
            pDDocument.close();
            log.info("Created ticket PDF for order #" + order.getId());
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
