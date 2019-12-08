package hr.fer.zemris.opp.giger.config.security;

import hr.fer.zemris.opp.giger.config.filter.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;


@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**" ,
                        /*Probably not needed*/ "/swagger.json")
                .permitAll();
        httpSecurity
                .csrf().disable().authorizeRequests()
                .antMatchers(POST, "/authenticate").permitAll()
                .antMatchers(POST, "/register").permitAll()
                .antMatchers(GET, "/register/nickname-available").permitAll()
                .antMatchers(GET, "/register/verification").permitAll()
                .antMatchers(POST, "/register/resend-verification-email").permitAll()
                .anyRequest().authenticated()
        ;

        httpSecurity
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling();

        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
