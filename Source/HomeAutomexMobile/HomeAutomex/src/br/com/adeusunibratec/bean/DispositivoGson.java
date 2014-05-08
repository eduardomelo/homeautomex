package br.com.adeusunibratec.bean;

public class DispositivoGson {
	public String Descricao;
	public Boolean Desativado;
	public Boolean Status;
	public Ambiente Ambiente;
	public PortaGson Porta;
	public Boolean Favorito;

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

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public Ambiente getAmbiente() {
		return Ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		Ambiente = ambiente;
	}

	public PortaGson getPorta() {
		return Porta;
	}

	public void setPorta(PortaGson porta) {
		Porta = porta;
	}

	public Boolean getFavorito() {
		return Favorito;
	}

	public void setFavorito(Boolean favorito) {
		Favorito = favorito;
	}
}
