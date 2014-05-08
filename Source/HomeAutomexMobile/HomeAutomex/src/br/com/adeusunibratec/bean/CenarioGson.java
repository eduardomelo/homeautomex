package br.com.adeusunibratec.bean;

public class CenarioGson {
	public String Descricao;
	public Boolean Desativado;
	public int ChaveAmbiente;

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Boolean getDesativado() {
		return Desativado;
	}

	public void setDesativado(Boolean desativado) {
		Desativado = desativado;
	}

	public int getChaveAmbiente() {
		return ChaveAmbiente;
	}

	public void setChaveAmbiente(int chaveAmbiente) {
		ChaveAmbiente = chaveAmbiente;
	}
}
