package bg.stoyank.footballtix.security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
}
