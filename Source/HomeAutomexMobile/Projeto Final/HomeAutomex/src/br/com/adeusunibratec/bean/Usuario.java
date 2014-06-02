package br.com.homeautomex.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract.Contacts.Data;

public class Usuario implements Parcelable{

	private String nome;
	private String login;
	private String senha;
	private String telefone;
	private String celular;
	private String email;
	private String dataCadastro;
	private String dataAlteracao;
	private String dataExclusao;
	private Boolean desativado;
	
	private int idusuario;
	
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	//private char chave;
	private String chave;
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
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	public Boolean getDesativado() {
		return desativado;
	}
	public void setDesativado(Boolean desativado) {
		this.desativado = desativado;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String string) {
		this.chave = string;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*public char getChave() {
		return chave;
	}
	public void setChave(char chave) {
		this.chave = chave;
	}*/
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
