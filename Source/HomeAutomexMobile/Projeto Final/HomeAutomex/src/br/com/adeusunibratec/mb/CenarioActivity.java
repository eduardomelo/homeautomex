package br.com.homeautomex.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.mb.R;
import br.com.homeautomex.acesso.AcessoWSDL;
import br.com.homeautomex.adapter.AmbientesAdapter;
import br.com.homeautomex.bean.Ambiente;
import br.com.homeautomex.bean.DispositivoGson;
import br.com.homeautomex.dao.UsuarioDAO;
import br.com.homeautomex.mb.AmbienteActivity.ResidenciaTask;
import br.com.homeautomex.parse.HomeAutomexJSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class CenarioActivity extends Activity implements OnClickListener {

	private Intent intent;
	String result ;
	private ProgressDialog progressDialog;
	private EditText descricaoCenario;
	private Button cadastroCenario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cenario);
		
		createProgressDialog();

		intent = getIntent();
		result = intent.getStringExtra("idResidencia");

		//Toast.makeText(getApplication(), result, Toast.LENGTH_LONG).show();
		
		this.descricaoCenario = (EditText)findViewById(R.id.editDescricaoCenario);
		
		this.cadastroCenario = (Button)findViewById(R.id.btnCdastroCenario);
		this.cadastroCenario.setOnClickListener(this);
		
		//setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {

		case R.id.opcaoUsuario:
			

			Intent intent = new Intent(this,
					OpcoesUsuariosActivity.class);

			intent.putExtra("idResidencia", result);

			startActivity(intent);
		
			
			this.finish();

			return true;
			
			

		case R.id.programacao:
			Intent intentListarA = new Intent(this,
					ConfiguracoesActivity.class);

			intentListarA.putExtra("idResidencia", result);

			startActivity(intentListarA);
			return true;
		
			
		case R.id.trocarUsuario:
			Intent intentTrocaUsuario = new Intent(this, LoginActivity.class);

			UsuarioDAO dao = new UsuarioDAO(this);
			dao.excluir();

			startActivity(intentTrocaUsuario);
			this.finish();
			return true;
			
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	
	
	private void setupViews() {

		/*String chave = null;
		
		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();*/

	
		
	}
	
	

	@Override
	protected void onPause() {
		super.onPause();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.cancel();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.cancel();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.cancel();
		}
	}

	private void createProgressDialog() {
		this.progressDialog = new ProgressDialog(this);
		this.progressDialog.setCancelable(false);
	}
	
	
	
	public class CadastroCenarioTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		// private int perfil;

		public CadastroCenarioTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Cadastro Cen�rio...");

		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String response = null;
			try {

				response = AcessoWSDL.CadastroCenario(params[0],params[1]);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return response;
		}

		@Override
		protected void onPostExecute(String jResult) {
			super.onPostExecute(jResult);

			//String chaveA = "";

			this.progressDialog.dismiss();
			
			Toast.makeText(getApplication(), "Cen�rio Cadastrado", Toast.LENGTH_LONG).show();
			
			/*if (jResult != null) {

				

			}*/

			
		}
	}



	@Override
	public void onClick(View arg0) {
		
		
		
         
		String chave = null;
		
		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();  
		
		if(descricaoCenario.getText().toString().equals(null)){
			
			Toast.makeText(getApplication(), "Informe uma Descri��o\n Para o Cen�rio ", Toast.LENGTH_LONG).show();
		}
		else{
			//Toast.makeText(getApplication(), "entrou no else", Toast.LENGTH_LONG).show();
		
			this.cadastrarCenario(descricaoCenario.getText(),chave);
		}
		
		/*if(descricaoCenario.getText().equals("")){
		
			Toast.makeText(getApplication(), "Voc� tem que inserir \numa descri��o do cen�rio", Toast.LENGTH_LONG).show();
		}*/
		/*else{
			
			
			
			
			
		
		}*/
		
		
	}

	private void cadastrarCenario(Editable text,String chave) {
		
		
		new CadastroCenarioTask(this.progressDialog).execute(text.toString(),chave);	
			
			
		
		
		
		
	}
	
	

}
