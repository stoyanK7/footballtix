package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.order.Order;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SendTicketRequest {
    private Order order;
    private String email;
}
