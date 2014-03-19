package br.com.adeusunibratec.bean;

import android.provider.ContactsContract.Contacts.Data;

public class Usuario {

	private String nome;
	private String login;
	private String senha;
	private String telefone;
	private String celular;
	private String email;
	private Data dataCadastro;
	private Data dataAlteracao;
	private Data dataExclusao;
	private Boolean desativado;
	private char chave;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Data getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Data dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Data getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Data dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Data getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(Data dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	public Boolean getDesativado() {
		return desativado;
	}
	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}
	public char getChave() {
		return chave;
	}
	public void setChave(char chave) {
		this.chave = chave;
	}
	
	
	
	
}
