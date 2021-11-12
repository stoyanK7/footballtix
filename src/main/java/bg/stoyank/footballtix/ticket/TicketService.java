package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    public JwtService jwtService;

    public String confirmToken(String token, String fullName) {
        boolean valid = jwtService.validateJwtToken(token, fullName.replace("%20", " "));
        return valid ? "Token is valid" : "Token is invalid";
    }
}
