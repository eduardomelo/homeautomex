package br.com.adeusunibratec.ha;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.ListaFavoritosAdapter;
import br.com.adeusunibratec.adapter.ListaResidenciaAdapter;
import br.com.adeusunibratec.adapter.ListarCenariosAdapter;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.mb.AmbienteActivity;
import br.com.adeusunibratec.mb.CenarioActivity;
import br.com.adeusunibratec.mb.FavoritosActivity;
import br.com.adeusunibratec.mb.ListarFavoritos;
import br.com.adeusunibratec.mb.OpcoesUsuariosActivity;
import br.com.adeusunibratec.mb.ProgramacaoActivity;
import br.com.adeusunibratec.mb.ListarFavoritos.ResidenciaTask;
import br.com.adeusunibratec.parse.HomeAutomexJSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarCenariosActivity extends Activity implements OnClickListener {

	private String jLoginResult = null;
	private Intent intent;
	private ListView listaCenarios;
	private String favorito;

	private ListarCenariosAdapter listaCenariosAdapter;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_cenarios);

		createProgressDialog();

		this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("idResidencia");

		setupViews();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {

		case R.id.cadastroFavorito:
			Toast.makeText(getApplication(), "clicou", Toast.LENGTH_LONG)
					.show();

			Intent intent = new Intent(ListarCenariosActivity.this,
					FavoritosActivity.class);

			intent.putExtra("idResidencia", jLoginResult);

			startActivity(intent);

			return true;

		case R.id.ListarAmbiente:
			Intent intentListarA = new Intent(ListarCenariosActivity.this,
					AmbienteActivity.class);

			intentListarA.putExtra("idResidencia", jLoginResult);

			startActivity(intentListarA);
			return true;
		case R.id.cadastrarAgendamento:
			Intent intentCadastroA = new Intent(ListarCenariosActivity.this,
					ProgramacaoActivity.class);

			intentCadastroA.putExtra("idResidencia", jLoginResult);

			startActivity(intentCadastroA);
			return true;

		case R.id.ListarCenario:
			Intent intentListaeCe = new Intent(ListarCenariosActivity.this,
					CenarioActivity.class);

			intentListaeCe.putExtra("idResidencia", jLoginResult);

			startActivity(intentListaeCe);
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

		Log.e("ddd", "entrouuuu");

		this.listaCenarios = (ListView) findViewById(R.id.listViewCenarios);

	

		String chave = null;

		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		// lembrando que é para colocar a string chave dentro do parametro o "2"
		// é só para testar o metodo
		new ResidenciaTask(this.progressDialog).execute(chave);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private void senddispositivosToActivity(Residencia residencia) {

		// teste actionbar

		Intent intent = new Intent(ListarCenariosActivity.this,
				ListarFavoritos.class);

		intent.putExtra("idResidencia", residencia.getChave());

		startActivity(intent);
	}

	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Cenários...");

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

				response = AcessoWSDL.buscarCenarios(params[0]);

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

			Log.e("onPostExecute", jResult);
			if (jResult != null) {

				this.progressDialog.dismiss();

				
				  listaCenariosAdapter = new ListarCenariosAdapter(
				  ListarCenariosActivity.this,
				  ListarCenariosActivity.this.criarListaCenarios(jResult,
				  jLoginResult));
				  
				  listaCenarios.setAdapter(listaCenariosAdapter);
				 

				Log.e("result retorno", jResult);

			} else {
				this.progressDialog.dismiss();
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

			Log.e("chegou bem ", jsonArray.toString());
			
			Ambiente ambiente = null;
			Residencia residencia = null;
			

			for (int i = 0; i < jsonArray.length(); i++) {
				DispositivoGson dispositivo = new DispositivoGson();
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String descricaoCenario = jsonObject.getString("Descricao");
				String cenarioChave = jsonObject.getString("Chave");

				
				
				/*try {
					String dispositivoFavorito = jsonObject
							.getString("Favorito");
					
					dispositivo.setFavorito(dispositivoFavorito);
					
				} catch (Exception e) {

					dispositivo.setFavorito("False");
					
				}*/
				
				
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
