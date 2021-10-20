package bg.stoyank.footballtix.footballmatch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FootballMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime startingDateTime;
    private String stadium;
    private String location;
    private String league;
    private Integer ticketsAvailable;
    private Double pricePerTicket;
}
