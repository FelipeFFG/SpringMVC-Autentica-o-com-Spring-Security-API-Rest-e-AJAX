package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    //todoas as requisicoes
                .anyRequest().authenticated()  //o uruarios deve estar logado
           .and()
                .formLogin(form -> form   //qual a url que é a pagina de login
                        .loginPage("/login")  //especificacao qual a pagina
                        .permitAll()           //todos tem permissao para acessar esta pagina.
                )
        .logout(logout -> logout.logoutUrl("/logout"));  // quando houver uma requisicao para logout, o usuario sera deslogado da conta.
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()   //Nao é considerado seguro em producao
                        .username("maria")
                        .password("maria")
                        .roles("ADM")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

}
