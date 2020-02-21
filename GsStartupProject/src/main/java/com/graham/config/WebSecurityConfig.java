package com.graham.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.graham.security.JWTAuthenticationFilter;
import com.graham.security.JWTAuthorizationFilter;
import com.graham.security.JwtAuthEntryPoint;
import com.graham.security.JwtSecurityConstants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .configurationSource(this.corsConfigurationSource())
            .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and().authorizeRequests()
            .antMatchers("/auth/public", JwtSecurityConstants.SIGNUP_URL, JwtSecurityConstants.SIGNIN_URL).permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Vue.jsを起動しているlocalhost:8081からのCORSを有効化
    private CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // jwt用のhttpヘッダーを登録
        corsConfiguration.addExposedHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME);
        corsConfiguration.addAllowedOrigin("http://localhost:8081");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }
}
