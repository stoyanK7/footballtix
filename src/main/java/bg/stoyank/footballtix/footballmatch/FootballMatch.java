package bg.stoyank.footballtix.footballmatch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String homeTeam;
    @NotBlank
    private String awayTeam;
    @NotNull
    private Date startingDateTime;
    @NotBlank
    private String stadium;
    @NotBlank
    private String location;
    @NotBlank
    private String league;
    @Min(0)
    private int ticketsAvailable;
    @Min(0)
    @Max(10000)
    private double pricePerTicket;
}
