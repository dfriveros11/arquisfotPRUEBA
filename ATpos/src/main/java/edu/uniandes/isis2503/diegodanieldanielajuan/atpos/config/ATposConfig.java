package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;


@EnableWebSecurity
@Configuration
public class ATposConfig extends WebSecurityConfigurerAdapter {

	 	@Value(value = "${auth0.apiAudience}")
	    private String apiAudience;
	    @Value(value = "${auth0.issuer}")
	    private String issuer;

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
	        configuration.setAllowCredentials(true);
	        configuration.addAllowedHeader("Authorization");
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors();
	        JwtWebSecurityConfigurer
	                .forRS256(apiAudience, issuer)
	                .configure(http)
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/home").permitAll()
	                .antMatchers(HttpMethod.GET, "/api/public").permitAll()
	                .antMatchers(HttpMethod.GET, "/api/private").authenticated()
	                .antMatchers(HttpMethod.GET, "/api/private-scoped").hasAuthority("read:messages")
	                .antMatchers(HttpMethod.GET, "/**").hasAnyAuthority("read:product", "read:products")
	                .antMatchers(HttpMethod.POST, "/**").hasAuthority("write:product")
	                .antMatchers(HttpMethod.PUT, "/**").hasAuthority("write:product")
	                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("delete:product");
	    }


}
