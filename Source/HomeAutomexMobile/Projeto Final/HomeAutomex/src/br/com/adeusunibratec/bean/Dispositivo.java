package br.com.homeautomex.bean;

import java.util.ArrayList;
import java.util.List;

public class Dispositivo {

	public int idDispositivo;
	public String Descricao;
	public String Chave;
	public String DataAlteracao;
	public String DataCadastro;
	public String Status;
	public String Favorito;
	
	public Ambiente Ambiente;
	
	public PortaGson Porta;
	
	
	private List<Cenario> Cenario;
	


	public Dispositivo(){
		
		//Ambiente = new Ambiente(); 
		Cenario = new ArrayList<Cenario>();
	}
	
	
	
	public List<Cenario> getCenario() {
		return Cenario;
	}




	public void setCenario(List<Cenario> cenario) {
		Cenario = cenario;
	}

	
	

	public Ambiente getAmbiente() {
		return Ambiente;
	}




	
	
	public String getFavorito() {
		return Favorito;
	}



	public void setFavorito(String favorito) {
		Favorito = favorito;
	}



	public String getStatus() {
		return Status;
	}




	public void setStatus(String status) {
		Status = status;
	}




	public PortaGson getPorta() {
		return Porta;
	}




	public void setPorta(PortaGson porta) {
		Porta = porta;
	}




	public void setAmbiente(Ambiente ambiente) {
		Ambiente = ambiente;
	}




	public String getChave() {
		return Chave;
	}




	public void setChave(String chave) {
		Chave = chave;
	}
	public String getDataAlteracao() {
		return DataAlteracao;
	}
	public void setDataAlteracao(String dataAlteracao) {
		DataAlteracao = dataAlteracao;
	}
	public String getDataCadastro() {
		return DataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		DataCadastro = dataCadastro;
	}
	public int getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	
	
	
	
}
