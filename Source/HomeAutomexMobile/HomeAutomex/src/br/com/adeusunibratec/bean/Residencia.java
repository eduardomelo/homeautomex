package br.com.adeusunibratec.bean;

import android.provider.ContactsContract.Contacts.Data;

public class Residencia {
	 
	private long idResidencia;
	public long getIdResidencia() {
		return idResidencia;
	}
	public void setIdResidencia(long idResidencia) {
		this.idResidencia = idResidencia;
	}
	private String Logradouro;
	private String Cidade;
	private String Bairro;
	private String Cep;
	private String Numero;
	private String Complemento;
	private Data DataCadastro;
	private Data DataAlteracao;
	private Data DataExclusao;
	private boolean Desativado;
	
	
	
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
	public Data getDataCadastro() {
		return DataCadastro;
	}
	public void setDataCadastro(Data dataCadastro) {
		DataCadastro = dataCadastro;
	}
	public Data getDataAlteracao() {
		return DataAlteracao;
	}
	public void setDataAlteracao(Data dataAlteracao) {
		DataAlteracao = dataAlteracao;
	}
	public Data getDataExclusao() {
		return DataExclusao;
	}
	public void setDataExclusao(Data dataExclusao) {
		DataExclusao = dataExclusao;
	}
	public boolean isDesativado() {
		return Desativado;
	}
	public void setDesativado(boolean desativado) {
		Desativado = desativado;
	}

}
