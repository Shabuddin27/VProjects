package com.fitgo.project.security.util;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

@Value("${jwt.secret}")
private String jwtSecret;

@Value("${jwt.expiration}")
private String jwtExpirationMs;

// method to generate JWT Token
public String generateToken(String email) {

long expirationMillis = Long.parseLong(jwtExpirationMs);

Instant now = Instant.now();
Instant expirationTime = now.plusMillis(expirationMillis);

return Jwts.builder()
.setSubject(email)
.setIssuedAt(Date.from(now)) // Convert Instant to Date
.setExpiration(Date.from(expirationTime))
/*How the Signature is Created A JWT consists of three parts: 1.Header 2.Payload 3.Signature
* When signing the token, the Header and Payload are combined and passed through a cryptographic
  along with the secret key (jwtSecret) to generate the signature.
*/
.signWith(SignatureAlgorithm.HS512, jwtSecret)
.compact();
}

// getting userEmail from Token
public String getUserEmailFromToken(String token) {

Claims claims = Jwts.parser() // claims contains sub(email), iat(issuedAt), exp(expiration Date)
.setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
return claims.getSubject(); // gets email

}

public boolean validateToken(String token) {
try {
Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
return true;
} catch (Exception e) {
return false;
}
}



/*Header The header is a JSON object that specifies the type of token and the algorithm used for signing.
Example:
json

{
 "alg": "HS512",
 "typ": "JWT"
}
The header is then Base64Url-encoded into a string.

Create the Payload:

The payload is another JSON object that contains the claims (data) of the token, such as user information or expiration time.
Example:
json
Copy
Edit
{
 "sub": "user@example.com",
 "iat": 1611111111,
 "exp": 1611114711
}
This payload is also Base64Url-encoded.
*
*/
}