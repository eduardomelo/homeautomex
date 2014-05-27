package br.com.adeusunibratec.bean;

import java.util.ArrayList;
import java.util.List;

public class Cenario {

	public int id;
	public String Descricao;
	public String Chave;

	private List<Dispositivo> Dispositivo;

	//public Ambiente Ambiente;

	public Cenario() {

	//	amb = new Ambiente();
		Dispositivo = new ArrayList<Dispositivo>();

	}

	

	/*public Ambiente getAmbiente() {
		return Ambiente;
	}



	public void setAmbiente(Ambiente ambiente) {
		Ambiente = ambiente;
	}*/



	public List<Dispositivo> getDispositivo() {
		return Dispositivo;
	}

	public void setDispositivo(List<Dispositivo> dispositivo) {
		Dispositivo = dispositivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getChave() {
		return Chave;
	}

	public void setChave(String chave) {
		Chave = chave;
	}

}
