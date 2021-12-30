package br.com.collecion.pokemontcg.securities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTValidateFilter extends BasicAuthenticationFilter {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    private static final List<GrantedAuthority> roleList = new ArrayList<>();

    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader(AUTHORIZATION);

        if (authorization == null || !authorization.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorization.split(BEARER)[1];

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        String password = JWTAuthenticateFilter.TOKEN_PASSWORD;
        String username = JWT
                .require(Algorithm.HMAC512(password))
                .build()
                .verify(token)
                .getSignature();
        return username == null ? null : new UsernamePasswordAuthenticationToken(username, null, roleList);
    }
}
