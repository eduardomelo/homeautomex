package br.com.homeautomex.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Util {

	/* Fun��o para verificar exist�ncia de conex�o com a internet 
	*/  
	public static  boolean verificaConexao(Context conte) {  
	    boolean conectado;  
	    ConnectivityManager conectivtyManager = (ConnectivityManager) conte.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    if (conectivtyManager.getActiveNetworkInfo() != null  
	            && conectivtyManager.getActiveNetworkInfo().isAvailable()  
	            && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
	        conectado = true;  
	    } else {  
	        conectado = false;  
	    }  
	    return conectado;  
	}  
}
