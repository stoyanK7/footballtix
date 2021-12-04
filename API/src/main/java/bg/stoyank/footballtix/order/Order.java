package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String accountEmail;
    @ManyToOne
    private FootballMatch footballMatch;
    @NotBlank
    private String fullName;
    @NotBlank
    private String email;
    @NotBlank
    private String mobilePhone;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotBlank
    private String postcode;
    @NotBlank
    private String transactionId;
    @NotNull
    private Date transactionDateTime;
}
