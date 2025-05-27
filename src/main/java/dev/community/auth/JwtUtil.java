 package dev.community.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String createJwt(String memberEmail) {
		return Jwts.builder()
			.setSubject(memberEmail)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + TokenExpiration.ACCESS_TOKEN.getExpirationTime()))
			.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
			.compact();
	}

	public String validateJwt(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		} catch (JwtException | IllegalArgumentException e) {
			// 유효하지 않은 토큰
			return null;
		}
	}
}
