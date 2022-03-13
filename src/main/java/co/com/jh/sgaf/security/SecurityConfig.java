/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jh.sgaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author jsherreram
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll() // Permitimos el acceso a /login a cualquiera.
//                .antMatchers(HttpMethod.GET, "/activosFijos").hasAnyRole("ADMIN", "USER", "GUEST")
//                .antMatchers(HttpMethod.POST, "/activosFijos").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.PUT, "/activosFijos").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.DELETE, "/activosFijos").hasRole("ADMIN")
//                .antMatchers("/agregar", "/editar/**", "/eliminar").hasRole("ADMIN")
//                .antMatchers("/agregar", "/editar/**").hasRole("USER")
//                .antMatchers("/activosFijos").hasAnyRole("ADMIN", "USER", "GUEST")
                .antMatchers("/v2/api-docs/**", "/webjars/**", "/swagger-resources/**", "/*.html").permitAll()
                .anyRequest().authenticated() // Cualquier otra peticion requiere autenticacion.
                .and()
                // Las peticiones /login pasaran previamente por este filtro.
                .addFilterBefore(new LoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // Las demás peticiones pasarán por este filtro para validar el token.
                .addFilterBefore(new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

}
