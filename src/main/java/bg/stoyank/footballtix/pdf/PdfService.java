package bg.stoyank.footballtix.pdf;

import bg.stoyank.footballtix.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PdfService {
    public void createReceipt(Order order) {
        try {
            // TODO: make relative path
            PDDocument pDDocument = PDDocument.load(new File("/media/stoyank/Elements/University/Semester 3/footballtix/src/main/resources/templates/Receipt.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

            pDAcroForm.refreshAppearances();

            pDAcroForm.getField("tbxOrderId").setValue(order.getId().toString());
            pDAcroForm.getField("tbxFullName").setValue(order.getFullName());
            pDAcroForm.getField("tbxEmail").setValue(order.getEmail());
            pDAcroForm.getField("tbxPhoneNumber").setValue(order.getMobilePhone());
            pDAcroForm.getField("tbxAddress").setValue(order.getAddress());
            pDAcroForm.getField("tbxCity").setValue(order.getCity());
            pDAcroForm.getField("tbxCountry").setValue(order.getCountry());
            pDAcroForm.getField("tbxPostcode").setValue(order.getPostcode());
            pDAcroForm.getField("tbxTransactionId").setValue(order.getTransactionId());
            // TODO: parse date
            pDAcroForm.getField("tbxTransactionTime").setValue(order.getTransactionDateTime().toString());

            pDAcroForm.getField("tbxMatch").setValue(order.getFootballMatch().getHomeTeam() + " vs " + order.getFootballMatch().getAwayTeam());
            // TODO: parse date
            pDAcroForm.getField("tbxDate").setValue(order.getFootballMatch().getStartingDateTime().toString());
            pDAcroForm.getField("tbxPlace").setValue(order.getFootballMatch().getStadium() + ", " + order.getFootballMatch().getLocation());

            pDAcroForm.getField("tbxTotal").setValue("Total: €" + String.format("%.2f", order.getFootballMatch().getPricePerTicket()));

            pDAcroForm.flatten();
            String fileName = "Order #" + order.getId() + ".pdf";
            // TODO: make relative path
            pDDocument.save("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/receipt/" + fileName);
            pDDocument.close();
            log.info("Created PDF file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTicket(Order order) {
        try {
            // TODO: make relative path
            PDDocument pDDocument = PDDocument.load(new File("/media/stoyank/Elements/University/Semester 3/footballtix/src/main/resources/templates/Ticket.pdf"));
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            pDAcroForm.refreshAppearances();

            PDImageXObject pdImage = PDImageXObject.createFromFile("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/qr/QR.png", pDDocument);
            PDPage page = pDDocument.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(pDDocument, page, PDPageContentStream.AppendMode.APPEND,true, true);
            contentStream.drawImage(pdImage, 0, 30);
            contentStream.close();

            pDAcroForm.getField("tbxHomeTeam").setValue(order.getFootballMatch().getHomeTeam().toUpperCase());
            pDAcroForm.getField("tbxAwayTeam").setValue(order.getFootballMatch().getAwayTeam().toUpperCase());
            pDAcroForm.getField("tbxTicketNumber").setValue("TICKET NUMBER: " + order.getId().toString());
            pDAcroForm.getField("tbxSeat").setValue("Seat: " + order.getId().toString());

            // TODO: parse date
            pDAcroForm.getField("tbxDate").setValue(order.getFootballMatch().getStartingDateTime().toString());
            pDAcroForm.getField("tbxStadium").setValue(order.getFootballMatch().getStadium().toUpperCase());

            pDAcroForm.getField("tbxPrice").setValue("Price: €" + String.format("%.2f", order.getFootballMatch().getPricePerTicket()));

            pDAcroForm.flatten();
            // TODO: make relative path
            String fileName = "Ticket #" + order.getId() + ".pdf";
            pDDocument.save("/media/stoyank/Elements/University/Semester 3/footballtix/tmp/ticket/" + fileName);
            pDDocument.close();
            log.info("Created PDF file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
