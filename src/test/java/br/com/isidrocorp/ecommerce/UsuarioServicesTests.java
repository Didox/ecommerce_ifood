package br.com.isidrocorp.ecommerce;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.isidrocorp.ecommerce.model.Usuario;
import br.com.isidrocorp.ecommerce.services.IUsuarioService;

@SpringBootTest
public class UsuarioServicesTests {
	@Autowired
	private IUsuarioService service;
	
	@Test // eu testo só se o serviço existe
	public void shouldExistUsuarioService() {
		Assertions.assertTrue(service.recuperarTodos() == null || service.recuperarTodos() != null);
	}
	
	@Test
	public void shouldReturnListaDeUsuario() {
		Assertions.assertTrue(service.recuperarTodos().size() >= 0);		
	}
	
	@Test
	public void shouldExistServicoDetalhes() {
		Assertions.assertTrue(service.recuperarDetalhes(1) == null || service.recuperarDetalhes(1) != null);
	}
	
	@Test
	public void shouldReturnUsuarioExistente() {
		Assertions.assertInstanceOf(Usuario.class, service.recuperarDetalhes(1));
	}
	
	@Test
	public void shouldReturnUsuarioInexistente() {
		Assertions.assertEquals(null, service.recuperarDetalhes(1000));
	}
	
	@Test
	public void shouldThrowExceptionDeIdInvalido() {
		Assertions.assertThrows(java.lang.RuntimeException.class, () -> {
			service.recuperarDetalhes(-1);	
		});
	}

}
