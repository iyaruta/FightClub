package home.inna.fc.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

@Component
class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.prefix}")
    private String prefix;

    @Value("${security.jwt.header}")
    private String header;

    @Value("${security.jwt.expiration}")
    private int expirationTime;

    String header() {
        return header;
    }

    String create(SignIn signIn, Long heroId, String heroName) {
        Instant expireDate = Instant.now().plusSeconds(expirationTime);
        try {
            return prefix + JWT.create()
                    .withSubject(signIn.getUsername())
                    .withClaim("heroId", heroId)
                    .withClaim("heroName", heroName)
                    .withExpiresAt(Date.from(expireDate))
                    .sign(Algorithm.HMAC256(secret));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Algorithm HMAC256 not supported");
        }
    }

    HeroAuth authorize(HttpServletRequest req) {
        String bearerHeader = req.getHeader(this.header);
        if (bearerHeader == null || !bearerHeader.startsWith(prefix)) {
            return null;
        }

        String token = bearerHeader.replace(prefix, "");
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            Long heroId = jwt.getClaim("heroId").asLong();
            String heroName = jwt.getClaim("heroName").asString();
            return new HeroAuth(heroId, heroName);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Algorithm HMAC256 not supported");
        }
    }

}
