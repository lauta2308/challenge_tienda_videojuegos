package com.tiendavideojuegos.challenge_tienda_videojuegos.configurations;



import com.tiendavideojuegos.challenge_tienda_videojuegos.models.Client;
import com.tiendavideojuegos.challenge_tienda_videojuegos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientRepository userRepository;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Client user = userRepository.findByEmail(inputName);

            if (user != null) {
                if (user.getName().contains("admin")){

                    return new User(user.getEmail(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList ("ADMIN"));
                }
                else {


                    return new User(user.getEmail(), user.getPassword(),AuthorityUtils.createAuthorityList("client"));}

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}