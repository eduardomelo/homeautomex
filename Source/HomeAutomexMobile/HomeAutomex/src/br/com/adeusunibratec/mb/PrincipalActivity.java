package br.com.adeusunibratec.mb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.ha.R;

public class PrincipalActivity extends Activity {
	Button btn;
	ProgressDialog progressDialog;
	List<Object> resultado = new ArrayList<Object>();
	
	String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		
			new Consultar().execute();
		
	}

	class Consultar extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// super.onPreExecute();
			progressDialog = new ProgressDialog(PrincipalActivity.this);
			progressDialog.setMessage("Carregando dados...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			AcessoWSDL wsdl = new AcessoWSDL();
			
			resultado = wsdl.consultarTodosUsuarios();
			for (Object r : resultado) {
				s = r.toString();
			}
			return s;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			progressDialog.dismiss();
			 if(resultado != null || !resultado.isEmpty()){
			 // carregando a menssagem
			  Toast.makeText(PrincipalActivity.this, s,Toast.LENGTH_LONG).show();
			// System.out.println("ok tem retorno");
			 }

		}

	}
}
