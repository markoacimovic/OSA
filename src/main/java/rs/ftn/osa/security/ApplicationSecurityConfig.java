package rs.ftn.osa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //ako se koristi PreAuthorize anotacija
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    //ovde se sve konfigurise za sec

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {

        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //u ovoj metodi se zabranjuje ili dozvoljava pristup, sta header sme da sadrzi (glavna metoda)
        //antMatchers za odredjeni url pattern dozvoljava ili zabranjuje pristup, redosled je bitan tj. prvo idu zabrane, pa dozvole
        //hasRole samo izabrani tip korisnika moze da pristupi
        //hasAuthority moze da se koristi za odredjen authority koji se dodeli tipu korisnika
        //hasRole i hasAuthority mogu i da se definisu u kontroleru anotacijom PreAuthorize
        //ako ne zelis da disable csrf onda moras na frontendu da uz svaki zahtev za POST, PUT i DELETE
        // saljes polje X-XSRF-TOKEN, token se salje uz GET
        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //The method withHttpOnlyFalse allows client to read XSRF cookie.
        // Make sure that client makes XHR request with withCreddentials flag set to true

        http
                .csrf().disable()
                //Naglasavamo browser-u da ne cache-ira podatke koje dobije u header-ima
                //detaljnije: https://www.baeldung.com/spring-security-cache-control-headers
                .headers().cacheControl().disable()
                .and()
                //Neophodno da ne bi proveravali autentifikaciju kod Preflight zahteva
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login").permitAll()    //redirect na login page
                .defaultSuccessUrl("/", true)  //sta posle prijave
                .and()
                .rememberMe() //produzuje cookie na 2 nedelje ako se izabere i kreira se novi cookie
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // ako je csrf enabled ovo se brise i logout metoda mora biti post
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me", "JWT")
                .logoutSuccessUrl("/login");
    }
}
