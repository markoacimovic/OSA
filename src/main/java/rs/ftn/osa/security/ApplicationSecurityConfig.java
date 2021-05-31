package rs.ftn.osa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //ako se koristi PreAuthorize anotacija
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    //ovde se sve konfigurise za sec

    @Autowired
    private UserDetailsService userDetailsService;

    //Podesavanje autentifikacije
    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //Koji se encoder koristi
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //Setuje se token filter
    @Bean
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
                //Naglasavamo browser-u da ne cache-ira podatke koje dobije u header-ima
                //detaljnije: https://www.baeldung.com/spring-security-cache-control-headers
                .headers().cacheControl().disable()
                .and()
                //Neophodno da ne bi proveravali autentifikaciju kod Preflight zahteva
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/prodavnica/auth/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/prodavnica/auth/login").permitAll()
                .anyRequest().permitAll()
                //.authenticated()
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//                .formLogin()
//                .loginPage().permitAll()    redirect na login page
//                .defaultSuccessUrl("/", true)  sta posle prijave
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // ako je csrf enabled ovo se brise i logout metoda mora biti post
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "JWT")
//                .logoutSuccessUrl("/login");
    }
}
