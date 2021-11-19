package bg.stoyank.footballtix.ticket;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token, @RequestParam("fullName") String fullName) {
        return ticketService.confirmToken(token, fullName);
    }

    @PostMapping("/send")
    public String send(@RequestBody SendTicketRequest request) {
        return ticketService.sendTicket(request.getEmail(), request.getOrder());
    }
}
