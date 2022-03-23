package br.com.isidrocorp.ecommerce.security.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.isidrocorp.ecommerce.security.TokenUtil;

// essa definição indica que o filtro será executado a cada nova requisição (ou seja, não haverá nenhum tipo de "cache"
// de autenticações para isso)
public class EcommerceFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	// aqui vem a lógica de autorizar ou não
		System.out.println("Requisicao Passando pelo filtro...");
		
		// o que eu preciso fazer num filtro?
		// validar minha lógica de autrização

		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (request.getHeader("Authorization") != null && !path.equals("/login")) {
			Authentication auth = TokenUtil.validate(request);   // verifico se é válida
			// se for válida, insiro um cabeçalho do tipo Authentication indicando que a requisição é autenticada
			SecurityContextHolder.getContext().setAuthentication(auth);			
		}
		
		// encaminhar a requisição pra frente
		filterChain.doFilter(request, response);		
	}
	
	

}
