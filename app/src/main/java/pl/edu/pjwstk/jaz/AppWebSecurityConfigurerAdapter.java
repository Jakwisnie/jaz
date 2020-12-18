package pl.edu.pjwstk.jaz;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.context.annotation.Configuration;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http ) throws  Exception{
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/auth0/**").permitAll()
                .antMatchers("/admin").hasRole("admin")//by wejsc cos co ma admin wymaga roli admin
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
