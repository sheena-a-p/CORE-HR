package com.core.configuration;

import com.core.security.AccessTokenProcessingFilter;
import com.core.security.AccessTokenUserDetailsService;
import com.core.security.config.SecurityConfig;
import com.core.security.util.TokenGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/error")))
                .addFilter(accessTokenProcessingFilter())
                .authenticationProvider(preAuthenticatedAuthenticationProvider()).exceptionHandling().and().headers()
                .and().sessionManagement().sessionCreationPolicy(STATELESS).and().securityContext().and().anonymous()
                .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users/**")
                .anonymous()
                // .antMatchers("/users/**").anonymous()
//                .antMatchers("/admin/listAllEmployees").hasAnyRole("EMPLOYEE","ADMIN")
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                .antMatchers("/employee/**").access("hasRole('EMPLOYEE')")
//                .antMatchers("/space/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/booking/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/floorDesign/**").hasAnyRole("EMPLOYEE", "ADMIN")
//                .antMatchers("/department/assignDepartment/**","/department/getDepartment","/department/getEmployeeDepartments").hasAnyRole("EMPLOYEE","ADMIN")
//                .antMatchers("/department/**").hasAnyRole( "ADMIN")
                .antMatchers("/users/**").permitAll()
                .antMatchers("/v2/api-docs",
                        "/v3/api-docs",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui/**").permitAll()
                .antMatchers("/google/**").permitAll()
//                .antMatchers("/public/**").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    protected AccessTokenUserDetailsService accessTokenUserDetailsService() {
        return new AccessTokenUserDetailsService();
    }

    @Bean
    protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenUserDetailsService());
        return authProvider;
    }

    @Bean
    protected AccessTokenProcessingFilter accessTokenProcessingFilter() throws Exception {
        AccessTokenProcessingFilter filter = new AccessTokenProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties("app.security")
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }

    @Bean
    @ConfigurationProperties("app.security.configuration")
    public TokenGenerator tokenGenerator(SecurityConfig securityConfig) {
        return new TokenGenerator(securityConfig.getTokenGeneratorPassword(), securityConfig.getTokenGeneratorSalt());
    }
}
