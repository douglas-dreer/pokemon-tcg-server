package br.com.collecion.pokemontcg.securities;

import br.com.collecion.pokemontcg.services.UserService;
import br.com.collecion.pokemontcg.utils.PasswordEncoderManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class JWTConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoderManager passwordEncoderManager;

    public JWTConfiguration(UserService userService, PasswordEncoderManager passwordEncoderManager) {
        this.userService = userService;
        this.passwordEncoderManager = passwordEncoderManager;
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoderManager.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthenticateFilter jwtAuthenticateFilter = new JWTAuthenticateFilter(authenticationManager());
        JWTValidateFilter jwtValidateFilter = new JWTValidateFilter(authenticationManager());
        http
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .and()
                .addFilter(jwtAuthenticateFilter)
                .addFilter(jwtValidateFilter)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
