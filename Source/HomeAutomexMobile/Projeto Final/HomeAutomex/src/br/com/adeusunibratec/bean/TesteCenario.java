package br.com.adeusunibratec.bean;

import java.util.ArrayList;
import java.util.List;

public class TesteCenario {

	
	public int id;
	public String Descricao;
	public String Chave;

	private List<ListCenario> listcenario;

	//public Ambiente Ambiente;

	
	public TesteCenario() {

	//	amb = new Ambiente();
		listcenario = new ArrayList<ListCenario>();

	}

	
	public List<ListCenario> getListcenario() {
		return listcenario;
	}








	public void setListcenario(List<ListCenario> listcenario) {
		this.listcenario = listcenario;
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
