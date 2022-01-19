-package bg.stoyank.footballtix.qr;

import bg.stoyank.footballtix.file.CommonPathsService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
@Slf4j
public class QrService {
    //static function that creates QR Code
    public void generateQrCode(String data, String path, String charset,
                               int h, int w) throws WriterException, IOException {
        //the BitMatrix class represents the 2D matrix of bits
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
        BitMatrix matrix = new MultiFormatWriter()
                .encode(new String(data.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToPath(matrix,
                path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
    }

    public void createQrCode(String data) {
        try {
            //Encoding charset to be used
            String charset = "UTF-8";
            //invoking the user-defined method that creates the QR code
            generateQrCode(data, CommonPathsService.QR_CODE_PATH, charset, 330, 330);
            //prints if the QR code is generated
            log.info("Created QR code with data: " + data);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
