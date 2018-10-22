package pl.pawellewarski.Kwiaty.configure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.pawellewarski.Kwiaty.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService basicUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/addOnePost").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/posts")
                .loginProcessingUrl("/login-process")
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test").password("$2a$10$lpl2cBeBK8CSJv0HvMg.5..N0VsVstg1d4HV97LXYzRugYRwNFRLm").roles("USER")
//                .and()
//                .withUser("admin").password("$2a$10$ADzephZwqBERwCm7/2MrxuVgaGxJOWNfO/4U1sz8hgmmZzdG.WCGi").roles("USER", "ADMIN");
        auth.userDetailsService(basicUserDetailsService);
    }
}


