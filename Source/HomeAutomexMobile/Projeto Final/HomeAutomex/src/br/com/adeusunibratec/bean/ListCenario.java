package br.com.adeusunibratec.bean;

public class ListCenario {

	
	public int idDispositivo;
	public String Descricao;
	public String Chave;
	public String DataAlteracao;
	public String DataCadastro;
	public String Status;
	
	public Ambiente Ambiente;
	
	public PortaGson Porta;
	
	/*public Dispositivo(){
		
		//Ambiente = new Ambiente(); 
	}*/
	
	
	

	public Ambiente getAmbiente() {
		return Ambiente;
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
