package bg.stoyank.footballtix.task;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskDefinitionBeanTest {
    @InjectMocks
    private TaskDefinitionBean componentUnderTest;

    @Mock
    private TaskDefinition taskDefinition;
    @Mock
    private EmailService emailService;
    @Mock
    private EmailTemplateService emailTemplateService;
    @Mock
    private OrderService orderService;

    @Test
    void testRun() {
        given(orderService.getAllOrdersByFootballMatchId(any()))
                .willReturn(Arrays.asList(
                        mock(Order.class, RETURNS_DEEP_STUBS),
                        mock(Order.class, RETURNS_DEEP_STUBS)
                ));

        componentUnderTest.run();

        verify(orderService).getAllOrdersByFootballMatchId(any());
        verify(emailService, atLeast(1))
                .send(any(), any(), any());
    }
}