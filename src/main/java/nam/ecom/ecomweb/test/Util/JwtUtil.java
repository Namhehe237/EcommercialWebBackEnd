package nam.ecom.ecomweb.test.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey; // Changed import from java.security.Key

@Component
public class JwtUtil {

    // Changed type from Key to SecretKey
    // private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    // Alternatively, if you want to use your existing key:
    private final SecretKey secretKey = (SecretKey) Keys.hmacShaKeyFor(
    "be56e66db6cf161ff2069218684c6814a7e633f7c9970534b7f86f728b0262494938b5882e35ae6565f70cf5c426f58df0d3702f80f508837790cbf411b6fea03f877066fc30d626e0a2e9d131681f7150aa2cd4b453b02bf408e1d5b5848633fba3d0b02fb8f441d2455b2dcdd355f0b14ba8e02df18167415f95403866928f02ec5acce109e7454d83a2f59eed4c0e3473d1635855d6d14d8bfde56240650594959005db1f4a0dc356723dcc1a2f03996754467323aee045305ceaf06a4cf5d9c90fc83b5c2ecf0ab4b6514b17de5105eaebc5e5248ae0b75df67a69fed8b1a64fff927087273e69ce63f94aad06140deafd02a794dde03eab135c382543d1"
    .getBytes());

    private final Long JWT_Expiration = 216000000L;

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    public String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_Expiration))
                .signWith(secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}