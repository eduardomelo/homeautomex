package br.com.adeusunibratec.acesso;

import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;

public class ConsultarTodosUsuarios extends AsyncTask<String, String, String>{
	ProgressDialog dialog;
	AcessoWSDL awsdl = new AcessoWSDL();
	
	private static String name_space = "http://tempuri.org/";
	private static String url = "http://192.168.0.103:9090/WS/HomeAutomexWS.asmx";
	private static String method_name = "ConsutarTodosUsuarios";
	private static String soap_action = "http://tempuri.org/ConsutarTodosUsuarios";
	List<Object> resultado = new ArrayList<Object>();
//	public void ConsultarTodosUsuarios(){}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		//super.onPreExecute();
//		dialog = new ProgressDialog(null);
//		dialog.setMessage("Carregando dados...");
//		dialog.setIndeterminate(false);
//		dialog.setCancelable(false);
//		dialog.show();
	}
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		resultado = awsdl.chamandoWS(url, name_space, method_name, soap_action);
		
		if(resultado != null || !resultado.isEmpty()){
			return "ok";
		}else{
			return "err";
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//super.onPostExecute(result);
//		dialog.dismiss();
		if(resultado != null || !resultado.isEmpty()){
		// carregando a menssagem	
//			Toast.makeText(ConsultarTodosUsuarios.this, , Toast.LENGTH_LONG).show();
			System.out.println("ok tem retorno");
		}
	
	}

}
