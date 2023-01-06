package br.com.adria.forum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.adria.forum.services.DetalheUsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class JWTConfiguracao{
	
	private final JWTAutenticarFilter jwtAutenticarFilter; 
	private final JWTValidarFilter jwtValidarFilter; 
	    
    private final DetalheUsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder, JWTAutenticarFilter jwtAutenticarFilter, JWTValidarFilter jwtValidarFilter) {
        this.jwtAutenticarFilter = jwtAutenticarFilter;
		this.jwtValidarFilter = jwtValidarFilter;
		this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    protected AuthenticationManager configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
        return auth.build();
    }

    @Bean
    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        	.requestMatchers(HttpMethod.POST,"/login").permitAll()
        	.anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(jwtAutenticarFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtValidarFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}











