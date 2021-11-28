package bg.stoyank.footballtix.file;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
@AllArgsConstructor
public class FileService {
    private CommonPathsService commonPathsService;

    public void deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
            log.info("Deleted file: " + path);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public void deleteReceipt(Long orderId) {
        deleteFile(commonPathsService.generateReceiptPath(orderId));
    }

    public void deleteTicket(Long orderId) {
        deleteFile(commonPathsService.generateTicketPath(orderId));
    }
}
