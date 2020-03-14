package com.graham.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.graham.security.JwtAuthEntryPoint;
import com.graham.security.JwtAuthTokenFilter;
import com.graham.security.JwtSecurityConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .configurationSource(this.corsConfigurationSource())
            .and()
            	/*
            	 SpringBoot Securityのデフォルトでは、アクセス権限（ROLE）設定したページに未認証状態でアクセスすると403を返す
            	 そのため403エラーを返却するようにカスタマイズする
            	 */
            	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            	// 403エラー時にResponseBodyを返却しないように設定
            	.exceptionHandling().accessDeniedHandler((req, res, ex) -> res.setStatus(HttpServletResponse.SC_FORBIDDEN))
        	.and()
            	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            	.logout()
            	.logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
            	.invalidateHttpSession(true)
            .and()
            	.csrf().disable()
        	.authorizeRequests()
        		.antMatchers("/api/v1/auth/**").permitAll()
        		.anyRequest().authenticated();

        ;
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Vue.jsを起動しているlocalhost:1024からのCORSを有効化
    private CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // jwt用のhttpヘッダーを登録
        corsConfiguration.addExposedHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME);
        corsConfiguration.addAllowedOrigin("http://localhost");
        corsConfiguration.addAllowedOrigin("http://localhost:8081");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }
}
