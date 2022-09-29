package com.cdac.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cdac.filter.CustomAuthenticationFilter;
import com.cdac.filter.CustomAuthorizationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
				authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/login");
		http.csrf().disable();
		http
        // by default uses a Bean by the name of corsConfigurationSource
        .cors().and();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/api/register/customer").permitAll();
		http.authorizeRequests().antMatchers("/api/userName/**").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("customer");
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/customer/**").hasAnyAuthority("customer");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/customer/**").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("admin");
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/customer/**").hasAnyAuthority("admin");
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority("employee");
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/customer/**").hasAnyAuthority("employee");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/employee/components/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/register/employee**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/customerOrders/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/employee/customers/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/employee/orders/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/employee/feedbacks/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/employee/employees/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/employee/updateemployee/{id}/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/employee/updateComponent/{id}/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/employee/updateorder/{id}/**").hasAnyAuthority("admin","employee");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/employee/deleteOrder/{id}/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/employee/addComponent/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/employee/componentDelete/{id}/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/employee/deleteEmployee/{id}/**").hasAnyAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/employee/deleteCustomer/{id}/**").hasAnyAuthority("admin");
		//http.authorizeRequests().anyRequest().authenticated();
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll(); //added line
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	

}
