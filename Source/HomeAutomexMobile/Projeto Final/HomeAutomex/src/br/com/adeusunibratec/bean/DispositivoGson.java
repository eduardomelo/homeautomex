package br.com.homeautomex.bean;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;

public class DispositivoGson {
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
	private String descricaoCenario;
	private String chaveUsuario;
	
	public String getChaveUsuario() {
		return chaveUsuario;
	}

	public void setChaveUsuario(String chaveUsuario) {
		this.chaveUsuario = chaveUsuario;
	}

	public String getDescricaoCenario() {
		return descricaoCenario;
	}

	public void setDescricaoCenario(String descricaoCenario) {
		this.descricaoCenario = descricaoCenario;
	}

	public String getChaveCenario() {
		return chaveCenario;
	}

	public void setChaveCenario(String chaveCenario) {
		this.chaveCenario = chaveCenario;
	}

	private String chaveCenario;

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

}
