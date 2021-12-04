package bg.stoyank.footballtix.security.resettoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ResetToken {
    @Id
    @SequenceGenerator(
            name = "reset_token_sequence",
            sequenceName = "reset_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reset_token_sequence"
    )
    private int id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    private String email;

    public ResetToken(String token,
                      LocalDateTime createdAt,
                      LocalDateTime expiredAt,
                      String email) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.email = email;
    }
}
