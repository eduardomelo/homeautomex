package br.com.homeautomex.bean;

public class PortaGson {
	public String Identificador;
    public String NumeroPorta;
    /*public ModuloGson Modulo;
    public TipoPortaGson Tipo;*/
    
    public String Chave;

	
    
    public PortaGson(){
    	
    	
    }
    
    public String getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(String identificador) {
		Identificador = identificador;
	}

	public String getNumeroPorta() {
		return NumeroPorta;
	}

	public void setNumeroPorta(String numeroPorta) {
		NumeroPorta = numeroPorta;
	}

	public String getChave() {
		return Chave;
	}

	public void setChave(String chave) {
		Chave = chave;
	}
    
}
