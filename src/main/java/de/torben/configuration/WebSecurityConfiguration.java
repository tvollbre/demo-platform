package de.torben.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfiguration {


    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable).formLogin(withDefaults())
                .authorizeExchange((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .pathMatchers("/**").hasRole("USER")
                );
        return http.build();
    }

}