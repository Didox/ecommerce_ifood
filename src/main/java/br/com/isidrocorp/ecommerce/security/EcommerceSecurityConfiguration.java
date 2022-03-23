package br.com.isidrocorp.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration 			// indico que é uma classe de configuração (e não um arquivo tipo o properties)
@EnableWebSecurity      // nesta classe indico que a minha configuração vai interferir na config do projeto Web
public class EcommerceSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private EcommerceEntryPoint entryPoint;  // injeto o meu entryPoint que defini e vai gerar um 401 ao invés de 403
	
	
	public void configure(HttpSecurity httpSec) throws Exception{
		System.out.println("--> SETUP da configuração de segurança...");
		
		// inicialmente eu desabilito o CSRF (para evitar que outros usuários se passem por usuários do meu sistema)
		httpSec.csrf().disable()
					  .exceptionHandling().authenticationEntryPoint(entryPoint)   // estou colocando um tratador de exceções
					  .and()
					  .authorizeRequests() 
					  // quais são as requisições que eu quero permitir
					  // .antMatchers(HttpMethod.GET, "/produtos").permitAll()
					  // .antMatchers(HttpMethod.GET, "/produtos/*").permitAll()
					  .antMatchers(HttpMethod.POST, "/login").permitAll()
					  // .antMatchers(HttpMethod.GET,"/usuarios").permitAll()
					  // .antMatchers(HttpMethod.GET,"/usuarios/*").permitAll()
					  // .antMatchers(HttpMethod.POST, "/usuarios*").permitAll()
					  // .antMatchers(HttpMethod.PUT, "/usuarios/*").permitAll()
					  
					  // qualquer outra requisição que "foge" aos padrões especificados, precisa ser autenticada
					  .anyRequest().authenticated().and().cors();
		
		// agora eu preciso indicar qual o filtro que eu quero que a requisição passe e como esse filtro trata a requisição
		httpSec.addFilterBefore(new EcommerceFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
}
