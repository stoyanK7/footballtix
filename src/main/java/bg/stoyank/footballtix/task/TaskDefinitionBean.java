package bg.stoyank.footballtix.task;

import bg.stoyank.footballtix.email.EmailService;
import bg.stoyank.footballtix.email.EmailTemplateService;
import bg.stoyank.footballtix.order.Order;
import bg.stoyank.footballtix.order.OrderService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
@Slf4j
public class TaskDefinitionBean implements Runnable {
    private TaskDefinition taskDefinition;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailTemplateService emailTemplateService;
    private OrderService orderService;

    public TaskDefinitionBean(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        log.info("Running action: " + taskDefinition.getActionType());
        List<Order> listOfOrders =
                orderService.getAllOrdersByFootballMatchId(taskDefinition.getFootballMatchId());
        for (Order order : listOfOrders) {
            String footballMatchRivals = order.getFootballMatch().getHomeTeam()
                    + " vs " + order.getFootballMatch().getAwayTeam();
            emailService.send(order.getEmail(),
                    "Your match is coming soon in 4 hours. Are you ready?",
                    emailTemplateService.buildUpcomingMatchEmail(order.getFullName(),
                            footballMatchRivals));
        }
    }
}
