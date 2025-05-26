 package dev.community.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long EXPIRATION_TIME = 1000 * 60 * 60;

	public String createJwt(String memberEmail) {
		return Jwts.builder()
			.setSubject(memberEmail)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
			.compact();
	}

	public String validateJwt(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody().getSubject();
		} catch (Exception e) {
			return null;
		}
	}
}
