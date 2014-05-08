package br.com.adeusunibratec.bean;

import java.util.Date;

public class AgendamentoGson {
	public String Descricao;
	public Boolean Ativo;
	public Date DataAgendamento = new Date();
	public Boolean Desativado;
	public int Usuario;
	public int Cenario;

	public AgendamentoGson() {
		
	}
	
	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Boolean getAtivo() {
		return Ativo;
	}

	public void setAtivo(Boolean ativo) {
		Ativo = ativo;
	}

	public Date getDataAgendamento() {
		return DataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		DataAgendamento = dataAgendamento;
	}

	public Boolean getDesativado() {
		return Desativado;
	}

	public void setDesativado(Boolean desativado) {
		Desativado = desativado;
	}

	public int getUsuario() {
		return Usuario;
	}

	public void setUsuario(int usuario) {
		Usuario = usuario;
	}

	public int getCenario() {
		return Cenario;
	}

	public void setCenario(int cenario) {
		Cenario = cenario;
	}
}
