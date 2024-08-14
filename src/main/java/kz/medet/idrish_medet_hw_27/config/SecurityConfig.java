package kz.medet.idrish_medet_hw_27.config;

import kz.medet.idrish_medet_hw_27.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        http.exceptionHandling(
                exception-> exception.accessDeniedPage("/forbidden")
        );

        http.authorizeRequests(
                authorize -> authorize
                        .requestMatchers("/admin/**").hasAuthority("WRITE_PRIVILEGE")
                        .requestMatchers("/auth_page/**").authenticated()
                        .requestMatchers("/user_info").authenticated()
                        .anyRequest().permitAll()
        ).formLogin(
                login -> login
                        .loginProcessingUrl("/")
                        .defaultSuccessUrl("/user_info")
        ).logout(
                logout -> logout
                        .logoutSuccessUrl("/home")
        ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
