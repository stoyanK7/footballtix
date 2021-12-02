package bg.stoyank.footballtix.ticket;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.file.CommonPathsService;
import bg.stoyank.footballtix.file.FileService;
import bg.stoyank.footballtix.jwt.JwtService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.pdf.PdfService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @InjectMocks
    private TicketService componentUnderTest;

    @Mock
    private JwtService jwtService;
    @Mock
    private EmailService emailService;
    @Mock
    private PdfService pdfService;
    @Mock
    private FileService fileService;
    @Mock
    private CommonPathsService commonPathsService;
    @Mock
    private EmailTemplateService emailTemplateService;

    @Test
    @DisplayName("Ensure confirmToken() invokes the proper repository method and passes the values.")
    void testConfirmToken() {
        String token = "test token";
        String fullName = "test full name";

        componentUnderTest.confirmToken(token, fullName);

        ArgumentCaptor<String> tokenArgumentCaptor
                = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> fullNameArgumentCaptor
                = ArgumentCaptor.forClass(String.class);

        verify(jwtService)
                .validateJwtToken(tokenArgumentCaptor.capture(),
                        fullNameArgumentCaptor.capture());

        assertThat(tokenArgumentCaptor.getValue()).isEqualTo(token);
        assertThat(fullNameArgumentCaptor.getValue()).isEqualTo(fullName);
    }

    @Test
    @DisplayName("Ensure sendTicket() invokes the proper service method and passes the values.")
    void testSendTicket() {
        Order order = mock(Order.class, RETURNS_DEEP_STUBS);
        String email = "test@mail.com";

        given(commonPathsService.generateTicketPath(any()))
                .willReturn("");

        componentUnderTest.sendTicket(email, order);

        ArgumentCaptor<Order> orderArgumentCaptor
                = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<String> emailArgumentCaptor
                = ArgumentCaptor.forClass(String.class);

        verify(pdfService).createTicket(orderArgumentCaptor.capture());
        verify(emailService)
                .sendWithAttachment(emailArgumentCaptor.capture(),
                        any(), any(), any());
        verify(fileService).deleteTicket(any());
        verify(fileService).deleteQr();

        assertThat(orderArgumentCaptor.getValue())
                .isEqualTo(order);
        assertThat(emailArgumentCaptor.getValue())
                .isEqualTo(email);
    }
}