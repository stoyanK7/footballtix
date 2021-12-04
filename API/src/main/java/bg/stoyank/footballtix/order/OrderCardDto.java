package bg.stoyank.footballtix.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCardDto {
    private Long id;
    private String fullName;
    private OrderCardFootballMatchDto footballMatch;
}
