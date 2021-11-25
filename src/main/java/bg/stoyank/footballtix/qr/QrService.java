package bg.stoyank.footballtix.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

@Service
@Slf4j
public class QrService {
    private final static String QR_CODE_PATH =
            new File("").getAbsolutePath() + "/tmp/qr/QR.png";

    //static function that creates QR Code
    public void generateQrCode(String data, String path, String charset,
                               Map<EncodeHintType, ErrorCorrectionLevel> map,
                               int h, int w) throws WriterException, IOException {
        //the BitMatrix class represents the 2D matrix of bits
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
        BitMatrix matrix = new MultiFormatWriter()
                .encode(new String(data.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToPath(matrix,
                path.substring(path.lastIndexOf('.') + 1), Paths.get(path));
    }

    public void createQrCode(String data) throws IOException, WriterException {
        //Encoding charset to be used
        String charset = "UTF-8";
        EnumMap<EncodeHintType, ErrorCorrectionLevel> hashMap =
                new EnumMap<>(EncodeHintType.class);
        //generates QR code with Low level(L) error correction capability
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //invoking the user-defined method that creates the QR code
        generateQrCode(data, QR_CODE_PATH, charset, hashMap, 330, 330);
        //prints if the QR code is generated
        log.info("Created QR code with data: " + data);
    }
}
