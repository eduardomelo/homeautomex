package br.com.adeusunibratec.bean;

import java.util.ArrayList;

public class UsuarioGson {
	public String Nome;
	public String Login;
	public String Senha;
	public String Telefone;
	public String Celular;
	public String Email;
	public Boolean Desativado;
	public ArrayList<ResidenciaGson> Residencias;

	public UsuarioGson() {
		Residencias = new ArrayList<ResidenciaGson>();
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getCelular() {
		return Celular;
	}

	public void setCelular(String celular) {
		Celular = celular;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Boolean getDesativado() {
		return Desativado;
	}

	public void setDesativado(Boolean desativado) {
		Desativado = desativado;
	}

	public ArrayList<ResidenciaGson> getResidencias() {
		return Residencias;
	}

	public void setResidencias(ArrayList<ResidenciaGson> residencias) {
		Residencias = residencias;
	}
}