package com.springbootstarter.config;

import com.springbootstarter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * 
 * @author BytesTree
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String REALM_NAME ="RESTFUL_REALM";
	
	/*@Autowired
	private UserDetailsService userDetailsService;*/
    @Autowired
    LoginService loginService;

    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	           http
                      // .cors()
                       //.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
              //.and()
					   .csrf().disable()
			  .authorizeRequests()
					   //.antMatchers("/switchtouser").access()
			  .antMatchers(HttpMethod.GET, "/topics/**").hasAnyAuthority("USER")
			 .antMatchers(HttpMethod.POST, "/topics/**").hasAnyAuthority("ADMIN")
			  .antMatchers(HttpMethod.PUT, "/topics/**").hasAnyAuthority("ADMIN")
			  .antMatchers(HttpMethod.DELETE, "/topics/**").hasAnyAuthority("ADMIN")
			  .anyRequest().authenticated()
			   .and().httpBasic()
             // .and().formLogin()
             // .loginPage("/login").permitAll()
              //.loginProcessingUrl("/login")
             // .successHandler(authenticationSuccessHandler)
              //.failureUrl("/loginError")
             // .loginProcessingUrl("/login1")
              //.and()
              //.logout()
             // .permitAll()
              //.and()
             // .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
             // .defaultSuccessUrl("/")

			 .realmName(REALM_NAME).authenticationEntryPoint(getBasicAuthEntryPoint())
			 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  		;
	}
	
	
	@Bean
	public BasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		BasicAuthenticationEntryPoint basicAuthEntryPoint = new BasicAuthenticationEntryPoint();
		basicAuthEntryPoint.setRealmName(REALM_NAME);
		return basicAuthEntryPoint;
	}

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);// this line is important it sends only specified domain instead of *
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }*/

}
