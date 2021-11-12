package bg.stoyank.footballtix.jwt;

import bg.stoyank.footballtix.order.Order;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "footballtix";

    public String extractUsername(String JwtToken) {
        return extractClaim(JwtToken, Claims::getSubject);
    }

    public Date extractExpiration(String JwtToken) {
        return extractClaim(JwtToken, Claims::getExpiration);
    }

    public <T> T extractClaim(String JwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(JwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String JwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(JwtToken).getBody();
    }

    private Boolean isJwtTokenExpired(String JwtToken) {
        return extractExpiration(JwtToken).before(new Date());
    }

    public String generateJwtToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getAuthorities().toArray()[0].toString());
        return createJwtToken(claims, user.getUsername(), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6));
    }

    public String generateTicketJwtToken(Order order) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", order.getEmail());
        return createJwtToken(claims, order.getFullName(), order.getFootballMatch().getStartingDateTime());
    }

    private String createJwtToken(Map<String, Object> claims, String subject, Date expiry) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public boolean validateJwtToken(String JwtToken, UserDetails user) {
        final String username = extractUsername(JwtToken);
        return (username.equals(user.getUsername()) && !isJwtTokenExpired(JwtToken));
    }

    public boolean validateJwtToken(String JwtToken, String fullName) {
        final String username = extractUsername(JwtToken);
        return (username.equals(fullName) && !isJwtTokenExpired(JwtToken));
    }
}
