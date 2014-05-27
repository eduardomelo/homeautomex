package br.com.adeusunibratec.bean;

import android.os.Parcel;

public class Comparar {

	private int idAmbiente;
	private String nomeAmbiente;
	private String Descricao;
	private String chaveDispositivos;
	private String statusDispositivos;
	private String dataAlteracaoDispositivos;
	private String dataCadastroDispositivos;
	private String chaveAmbiente;
	private String chavePorta;
	private String favorito;

	
	
	
	
	
	

	public Comparar(int idAmbiente, String nomeAmbiente, String descricao,
			String chaveDispositivos, String statusDispositivos,
			String dataAlteracaoDispositivos, String dataCadastroDispositivos,
			String chaveAmbiente, String chavePorta, String favorito) {
		super();
		this.idAmbiente = idAmbiente;
		this.nomeAmbiente = nomeAmbiente;
		Descricao = descricao;
		this.chaveDispositivos = chaveDispositivos;
		this.statusDispositivos = statusDispositivos;
		this.dataAlteracaoDispositivos = dataAlteracaoDispositivos;
		this.dataCadastroDispositivos = dataCadastroDispositivos;
		this.chaveAmbiente = chaveAmbiente;
		this.chavePorta = chavePorta;
		this.favorito = favorito;
	}

	public String getFavorito() {
		return favorito;
	}

	public void setFavorito(String favorito) {
		this.favorito = favorito;
	}

	public String getChaveDispositivos() {
		return chaveDispositivos;
	}

	public void setChaveDispositivos(String chaveDispositivos) {
		this.chaveDispositivos = chaveDispositivos;
	}

	public String getStatusDispositivos() {
		return statusDispositivos;
	}

	public void setStatusDispositivos(String statusDispositivos) {
		this.statusDispositivos = statusDispositivos;
	}

	public String getDataAlteracaoDispositivos() {
		return dataAlteracaoDispositivos;
	}

	public void setDataAlteracaoDispositivos(String dataAlteracaoDispositivos) {
		this.dataAlteracaoDispositivos = dataAlteracaoDispositivos;
	}

	public String getDataCadastroDispositivos() {
		return dataCadastroDispositivos;
	}

	public void setDataCadastroDispositivos(String dataCadastroDispositivos) {
		this.dataCadastroDispositivos = dataCadastroDispositivos;
	}

	public String getChaveAmbiente() {
		return chaveAmbiente;
	}

	public void setChaveAmbiente(String chaveAmbiente) {
		this.chaveAmbiente = chaveAmbiente;
	}

	public String getChavePorta() {
		return chavePorta;
	}

	public void setChavePorta(String chavePorta) {
		this.chavePorta = chavePorta;
	}

	public int getIdAmbiente() {
		return idAmbiente;
	}

	public void setIdAmbiente(int idAmbiente) {
		this.idAmbiente = idAmbiente;
	}

	public String getNomeAmbiente() {
		return nomeAmbiente;
	}

	public void setNomeAmbiente(String nomeAmbiente) {
		this.nomeAmbiente = nomeAmbiente;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		this.Descricao = descricao;
	}

	
	public boolean equals(Object objeto) {
		if (objeto == null)
			return false;

		Ambiente produto = (Ambiente) objeto;
		if (this.getDescricao().equals(produto.getDescricao()))
			return true;

		return false;
	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}
	
	
	
	
	
}
