package com.centling.config;

import com.centling.security.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.centling.security.AuthoritiesConstants;
import com.centling.security.jwt.JWTConfigurer;
import com.centling.security.jwt.TokenProvider;


/*@Configuration
@EnableWebSecurity*/
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/bower_components/**")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/static/**")
            .antMatchers("/swagger-ui/index.html")
            .antMatchers("/test/**")
            .antMatchers("/app/main/**");
            
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/blogUser/**").permitAll()
                .antMatchers("/api/blog/**").permitAll()
                .antMatchers("/api/resource/**").permitAll()

            .antMatchers("/api/logs/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/audits/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/**").authenticated()
            .antMatchers("/metrics/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/health/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/trace/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/dump/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/shutdown/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/beans/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/configprops/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/info/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/autoconfig/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/env/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/mappings/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/liquibase/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/configuration/security").permitAll()
            .antMatchers("/configuration/ui").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/protected/**").authenticated() 
        .and()
            .apply(securityConfigurerAdapter());

    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
