package br.com.homeautomex.bean;

import java.util.ArrayList;

public class ResidenciaGson {
	public String Nome;
	public String Logradouro;
	public String Cidade;
	public String Bairro;
	public String Cep;
	public String Numero;
	public String Complemento;
	public Boolean Desativado;
	public ArrayList<UsuarioGson> Usuarios;

	public ResidenciaGson() {
		Usuarios = new ArrayList<UsuarioGson>();
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public Boolean getDesativado() {
		return Desativado;
	}

	public void setDesativado(Boolean desativado) {
		Desativado = desativado;
	}

	public ArrayList<UsuarioGson> getUsuariosGson() {
		return Usuarios;
	}

	public void setUsuariosGson(ArrayList<UsuarioGson> usuariosGson) {
		Usuarios = usuariosGson;
	}
}