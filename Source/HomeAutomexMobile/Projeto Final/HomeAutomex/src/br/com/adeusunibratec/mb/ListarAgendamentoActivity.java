package br.com.homeautomex.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.mb.R;
import br.com.homeautomex.acesso.AcessoWSDL;
import br.com.homeautomex.adapter.ListarAgendamentoAdapter;
import br.com.homeautomex.adapter.ListarCenariosAdapter;
import br.com.homeautomex.bean.Ambiente;
import br.com.homeautomex.bean.DispositivoGson;
import br.com.homeautomex.bean.Residencia;
import br.com.homeautomex.dao.UsuarioDAO;
import br.com.homeautomex.parse.HomeAutomexJSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ListarAgendamentoActivity extends Activity {


	private String jLoginResult = null;
	private Intent intent;
	private ListView listaCenarios;
	private String favorito;

	private ListarAgendamentoAdapter listarAgendamentoAdapter;

	private ProgressDialog progressDialog;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_agendamento);
		
		createProgressDialog();

		this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("idResidencia");

		setupViews();
	
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

			intent.putExtra("idResidencia", jLoginResult);

			startActivity(intent);
		
			
			this.finish();

			return true;
			
			

		case R.id.programacao:
			Intent intentListarA = new Intent(this,
					ConfiguracoesActivity.class);

			intentListarA.putExtra("idResidencia", jLoginResult);

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

	private void setupViews() {

		

		this.listaCenarios = (ListView) findViewById(R.id.listViewAgendamento);

	

		String chave = null;

		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		// lembrando que � para colocar a string chave dentro do parametro o "2"
		// � s� para testar o metodo
		new ResidenciaTask(this.progressDialog).execute(chave);

	}

	
	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Agendamentos...");

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

				response = AcessoWSDL.buscarAgendamento(params[0]);

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

			
			if (jResult != null) {

				this.progressDialog.dismiss();

				
				listarAgendamentoAdapter = new ListarAgendamentoAdapter(
				  ListarAgendamentoActivity.this,
				  ListarAgendamentoActivity.this.criarListaCenarios(jResult,
				  jLoginResult));
				  
				  listaCenarios.setAdapter(listarAgendamentoAdapter);
				 

				

			} else {
				this.progressDialog.dismiss();
				
				Toast.makeText(getApplication(), "Voc� n�o tem Agendamento\nCadastrado", Toast.LENGTH_LONG).show();
				// mensagem de erro
			}
		}
	}
	
	
	
	public ArrayList<DispositivoGson> criarListaCenarios(String json,
			String Chave) {

		JSONArray jsonArray = null;

		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		try {
			jsonArray = new JSONArray(json);

			
			
			Ambiente ambiente = null;
			Residencia residencia = null;
			

			for (int i = 0; i < jsonArray.length(); i++) {
				DispositivoGson dispositivo = new DispositivoGson();
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String descricaoCenario = jsonObject.getString("Descricao");
				String cenarioChave = jsonObject.getString("Chave");

				dispositivo.setDescricaoCenario(descricaoCenario);
				dispositivo.setChaveCenario(cenarioChave);
				
				dispositivo.setChaveUsuario(jLoginResult);
				
				
				dispositivos.add(dispositivo);
				

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dispositivos;

	}
	
	
}
