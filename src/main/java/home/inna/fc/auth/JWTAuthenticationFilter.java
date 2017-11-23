package home.inna.fc.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.inna.fc.entity.Account;
import home.inna.fc.entity.Hero;
import home.inna.fc.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HeroService heroService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            Account account = new ObjectMapper().readValue(req.getInputStream(), Account.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            account.getEmail(),
                            account.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        SignIn signIn = (SignIn) auth.getPrincipal();
        Hero hero = heroService.getHero(signIn.getAccountId());
        if (hero == null) {
            throw new IllegalStateException("No players exists for account " + signIn.getAccountId());
        }
        String token = jwtUtil.create(signIn, hero.getId(), hero.getName());
        res.addHeader(jwtUtil.header(), token);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}