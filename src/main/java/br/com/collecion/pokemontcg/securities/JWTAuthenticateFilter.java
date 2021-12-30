package br.com.collecion.pokemontcg.securities;

import br.com.collecion.pokemontcg.dtos.UserDetailsDTO;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
TODO: Enviar os parametros TOKEN_TIMEOUT e TOKEN_PASSWORD para o arquivo de configuração
 */
public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final List<GrantedAuthority> roleList = new ArrayList<>();
    public static final int TOKEN_TIMEOUT = 600_000;
    public static final String TOKEN_PASSWORD = "7ef0043c-3c74-48c2-8c46-d26fb90a2f3a";

    public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), roleList);
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(MessagesEnum.FAIL_AUTHENTICATE.getText(), e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsDTO userDetails = (UserDetailsDTO) authResult.getPrincipal();
        Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_TIMEOUT);

        String token = JWT
                .create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
