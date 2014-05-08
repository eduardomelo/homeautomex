package br.com.adeusunibratec.bean;

public class ModuloGson {
	public String IP;
	public String Porta;
	public String Nome;
	public String MAC;
	public ResidenciaGson Residencia;

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getPorta() {
		return Porta;
	}

	public void setPorta(String porta) {
		Porta = porta;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public ResidenciaGson getResidencia() {
		return Residencia;
	}

	public void setResidencia(ResidenciaGson residencia) {
		Residencia = residencia;
	}

}
