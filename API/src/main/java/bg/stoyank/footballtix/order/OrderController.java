package bg.stoyank.footballtix.order;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
@Validated
public class OrderController {
    private OrderService orderService;
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkIfOrderIsAlreadyMadeOnName(@RequestBody  Map<String, String> body) {
        String matchId = body.get("matchId");
        String email = body.get("email");
        String fullName = body.get("fullName");
        orderService.checkIfOrderExistsForNameAndEmail(matchId, email, fullName);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @GetMapping
    public List<OrderCardDto> getAllOrdersByAccountEmail(String email) {
        return convertToListOfDto(orderService.getAllOrdersByAccountEmail(email));
    }

    private OrderCardDto convertToDto(Order order) {
        return modelMapper.map(order, OrderCardDto.class);
    }

    private List<OrderCardDto> convertToListOfDto(List<Order> orders) {
        List<OrderCardDto> list = new ArrayList<>();
        for (Order order : orders) {
            list.add(convertToDto(order));
        }
        return list;
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable("orderId") @PositiveOrZero int orderId) {
        return orderService.getOrderById(orderId);
    }
}
