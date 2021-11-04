package bg.stoyank.footballtix.security.auth;

import bg.stoyank.footballtix.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
@AllArgsConstructor
public class AuthenticationController {
    private JwtUtil jwtUtil;
    private UserDetailsService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );
        UserDetails user = userService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateJwtToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
