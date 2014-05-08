package br.com.adeusunibratec.bean;

public class AmbienteGson {
	public String Descricao;
	public Boolean Desativado;
	public ResidenciaGson Residencia;
	
	public AmbienteGson() {
		
	}

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

	public ResidenciaGson getResidencia() {
		return Residencia;
	}

	public void setResidencia(ResidenciaGson residencia) {
		Residencia = residencia;
	}
}
