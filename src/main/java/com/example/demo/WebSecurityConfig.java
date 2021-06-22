package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
           .antMatchers("/home/**")
                .permitAll()          //uma exceção para que todas os usuarios possam ter acesso a home , nao somente usuarios logados.
           .anyRequest().
                authenticated()  //o usuario deve estar logado
           .and()
                .formLogin(form -> form   //qual a url que é a pagina de login
                        .loginPage("/login")  //especificacao qual a pagina
                        .defaultSuccessUrl("/usuario/pedido",true)  //foça o spring a direcionar para a /home, ja que por padrao o spring redireciona, para a ultima requisicao de url feita.
                        .permitAll()           //todos tem permissao para acessar esta pagina.
                )
        .logout(logout -> {
            logout.logoutUrl("/logout")         // quando houver uma requisicao para logout, o usuario sera deslogado da conta.
                    .logoutSuccessUrl("/home"); //Assim que o usuario se desloga, ele é direcionado para a home
        });

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


/*        UserDetails user =
                User.builder()   //Nao é considerado seguro em producao
                        .username("joao")
                        .password(encoder.encode("joao"))
                        .roles("ADM")
                        .build();*/

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder);
               /* .withUser(user);*/
    }


}
