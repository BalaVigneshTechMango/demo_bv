package com.jwt.videostream.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	@Value("${jwt.tokenvalidity}")
	private int TOKEN_VALIDITY;

//	long expirationTimeMillis = System.currentTimeMillis() + 1000;
//	Date expirationDate = new Date(expirationTimeMillis);

	public String generateToken(UserDetails userDetails) {
		long milliTime = System.currentTimeMillis();
//		long expiryTime = milliTime + expiryDuration * 1000;
		long ex = milliTime + 6000;
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(ex);
//		Claims claims = Jwts.claims().setIssuer(idString).setIssuedAt(issuedAt).setExpiration(expiryAt);
//		// optional claims

		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(issuedAt)
				.setExpiration(expiryAt).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public Claims verify(String auth) throws Exception {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(auth).getBody();
			return claims;
		} catch (ExpiredJwtException e) {
			throw new Exception("Token Expired");
		}
	}

}
