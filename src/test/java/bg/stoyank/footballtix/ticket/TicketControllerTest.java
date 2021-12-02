package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {
    @InjectMocks
    private TicketController componentUnderTest;

    @Mock
    TicketService ticketService;

    @Test
    @DisplayName("Ensure confirm() invokes the proper service method and passes the values.")
    void testConfirm() {
        String token = "test token";
        String fullName = "test full name";

        componentUnderTest.confirm(token, fullName);

        ArgumentCaptor<String> tokenArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> fullNameArgumentCaptor
                = ArgumentCaptor.forClass(String.class);

        verify(ticketService)
                .confirmToken(tokenArgumentCaptor.capture(),
                        fullNameArgumentCaptor.capture());

        assertThat(tokenArgumentCaptor.getValue()).isEqualTo(token);
        assertThat(fullNameArgumentCaptor.getValue()).isEqualTo(fullName);
    }

    @Test
    @DisplayName("Ensure send() invokes the proper service method and passes the values.")
    void testSend() {
        TicketSendRequest ticketSendRequest =
                new TicketSendRequest(mock(Order.class, RETURNS_DEEP_STUBS),
                        "test@mail.com");

        componentUnderTest.send(ticketSendRequest);

        ArgumentCaptor<Order> orderArgumentCaptor
                = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<String> emailArgumentCaptor
                = ArgumentCaptor.forClass(String.class);

        verify(ticketService)
                .sendTicket(emailArgumentCaptor.capture(),
                        orderArgumentCaptor.capture());

        assertThat(orderArgumentCaptor.getValue())
                .isEqualTo(ticketSendRequest.getOrder());
        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(ticketSendRequest.getEmail());
    }
}