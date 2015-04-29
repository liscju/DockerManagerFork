package pl.edu.agh.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Import({ WebMVCConfigurator.class })
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {


    
	@Autowired
	private DataSource dataSource;
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select NAME,PASSWORD, ENABLED from users where name=?")
		.authoritiesByUsernameQuery(
			"select NAME, ROLE from users where name=?");
	}	
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home",true)
                 .and()
            .httpBasic();
    }
}
