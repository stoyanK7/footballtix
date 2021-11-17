package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
@Validated
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping
    public List<Order> getAllOrdersByAccountEmail(String email) {
        return orderService.getAllOrdersByAccountEmail(email);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable("orderId") @PositiveOrZero int orderId) {
        return orderService.getOrderById(orderId);
    }
}
