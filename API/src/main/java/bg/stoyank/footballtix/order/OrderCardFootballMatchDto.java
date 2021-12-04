package bg.stoyank.footballtix.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderCardFootballMatchDto {
    private Double pricePerTicket;
    private Date startingDateTime;
    private String homeTeam;
    private String awayTeam;
}
