package br.com.adeusunibratec.bean;

public class SensorGson {
	public DispositivoGson dispositivo;
	public String descricao;
	public int valor;

	public DispositivoGson getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoGson dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
