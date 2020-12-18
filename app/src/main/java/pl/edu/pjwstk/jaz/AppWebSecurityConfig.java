package pl.edu.pjwstk.jaz;

import org.hibernate.criterion.Example;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppWebSecurityConfig {
//metoda tworzaca
@Bean
    public FilterRegistrationBean<ExampleFilter> exampleFilter(UserSession userSession){
    FilterRegistrationBean<ExampleFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new ExampleFilter(userSession));
    registrationBean.addUrlPatterns("/auth0/*");

    return registrationBean;
}

}
