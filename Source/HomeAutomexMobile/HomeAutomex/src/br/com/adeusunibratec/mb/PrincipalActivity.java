/*package br.com.adeusunibratec.mb;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.UsuarioAdapter;
import br.com.adeusunibratec.bean.Usuario;
import br.com.adeusunibratec.util.Util;

public class PrincipalActivity extends ListActivity {
	Button btn;
	ProgressDialog progressDialog;
	List<Usuario> resultado = new ArrayList<Usuario>();
	
	String s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_principal);
		
//		Util.verificaConexao(this);
		
		if(Util.verificaConexao(this)){
			new Consultar().execute();
		}else{
			Toast.makeText(PrincipalActivity.this, "Não há Conexão com a Internet",Toast.LENGTH_LONG).show();
		}
		
	}

	class Consultar extends AsyncTask<Void, Void, List<Usuario>> {

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
		protected List<Usuario> doInBackground(Void... params) {
			AcessoWSDL wsdl = new AcessoWSDL();
//			List<Usuario> resultado = null;
			
			
			resultado = wsdl.consultarTodosUsuarios("");

			
			return resultado;
		}

		@Override
		protected void onPostExecute(List<Usuario> result) {
			progressDialog.dismiss();
			
			
			
			UsuarioAdapter usuAdapter = new UsuarioAdapter(PrincipalActivity.this, resultado);
			setListAdapter(usuAdapter);
			
//			 if(resultado != null || !resultado.isEmpty()){
//			  Toast.makeText(PrincipalActivity.this, s,Toast.LENGTH_LONG).show();
//			 }

		}

	}
}
*/