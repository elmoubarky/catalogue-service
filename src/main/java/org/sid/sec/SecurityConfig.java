package org.sid.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	//cetait un exemple car on ne fera aps cette gestion ici
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { //utilisation de la methode pour crypter BCryptPasswordEncoder
	 * bcpe = getBCE();
	 * auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234"))
	 * .roles("ADMIN","USER");
	 * auth.inMemoryAuthentication().withUser("user1").password(bcpe.encode("1234"))
	 * .roles("USER"); }
	 */
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//desactiver le systeme de securite spring en commentant la ligne ci dessous
		//super.configure(http);
		
		//desactiver le csrf token
		http.csrf().disable();
		
		//indiquer a spring security de plus utiliser les sessions
		//desactivation des sessions et donc par la suite on utilisera les token JWT
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//autoriser les requetes utilisateur envoye avec Get pour la consultation
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/categories/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/products/**").permitAll();
		
		//autorisation sur les requetes
		http.authorizeRequests().antMatchers("/categories/**").hasAnyAuthority("ADMIN"); //etre admin pour acceder a cette requete
		http.authorizeRequests().antMatchers("/products/**").hasAnyAuthority("USER"); //etre user pour acceder a cette requete
		http.authorizeRequests().anyRequest().authenticated(); //toutes les requetes necessitent une authentification
		
		//ajouter les filtres
		http.addFilterBefore(new JWTAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
		
		
	}
	
	/*
	 * //methode pour crypter le password
	 * 
	 * @Bean public BCryptPasswordEncoder getBCE() { return new
	 * BCryptPasswordEncoder();
	 * 
	 * }
	 */

}
