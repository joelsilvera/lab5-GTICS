package com.pucp.lab5gtics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfiguracionSpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/ventanaLogin").loginProcessingUrl("/procesarLoginForm")
                .defaultSuccessUrl("/redireccionarPorRol", true);

        http.authorizeRequests()
                .antMatchers("/empleado","/empleado/**").hasAuthority("EMPLOYEE")

                .anyRequest().permitAll();
        http.logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);//cuando cierre sesion,borra la cookie e invalida sesion
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT email, password,enabled FROM hr.employees where email=?")
                .authoritiesByUsernameQuery("SELECT correo,r.titulo FROM hr.employees u inner join roles r on (u.rol = r.idroles) where email=? and u.enabled=1;");

    }
}