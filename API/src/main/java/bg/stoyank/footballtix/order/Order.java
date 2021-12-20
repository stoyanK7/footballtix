package bg.stoyank.footballtix.order;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
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
