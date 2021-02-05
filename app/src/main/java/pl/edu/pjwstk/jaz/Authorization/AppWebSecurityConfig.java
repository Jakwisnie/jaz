package pl.edu.pjwstk.jaz.Authorization;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pjwstk.jaz.Example.ExampleFilter;


@Configuration
public class AppWebSecurityConfig {

    @Bean
    public FilterRegistrationBean<ExampleFilter> exampleFilter(UserSession userSession){
        FilterRegistrationBean<ExampleFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ExampleFilter(userSession));
        registrationBean.addUrlPatterns("/auth0/*");

        return  registrationBean;
    }

}