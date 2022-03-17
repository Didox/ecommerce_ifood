package br.com.isidrocorp.ecommerce.services;

import br.com.isidrocorp.ecommerce.dto.UsuarioLoginDTO;
import br.com.isidrocorp.ecommerce.security.Token;

public interface IUsuarioService {
	public Token gerarTokenDeUsuarioLogado(UsuarioLoginDTO dadosLogin);

}