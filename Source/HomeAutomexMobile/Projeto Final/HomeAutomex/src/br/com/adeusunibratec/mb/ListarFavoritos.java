package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.AmbientesAdapter;
import br.com.adeusunibratec.adapter.ListaFavoritosAdapter;
import br.com.adeusunibratec.adapter.ListaResidenciaAdapter;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.bean.Usuario;

import br.com.adeusunibratec.mb.AmbienteActivity.ResidenciaTask;
import br.com.adeusunibratec.parse.HomeAutomexJSONObject;
import br.com.adeusunibratec.parse.JSONParserManager;
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
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ListarFavoritos extends Activity implements OnClickListener {

	private String jLoginResult = null;
	private Intent intent;
	private ListView listaResidencias;
	private String favorito;

	private ListaResidenciaAdapter listaResidenciaAdapter;
	private ListaFavoritosAdapter listaFavoritosAdapter;

	private TextView boasVindas;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_favoritos);

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

		case R.id.opcaoUsuario:
			Toast.makeText(getApplication(), "clicou", Toast.LENGTH_LONG)
					.show();

			Intent intent = new Intent(ListarFavoritos.this,
					OpcoesUsuariosActivity.class);

			intent.putExtra("idResidencia", jLoginResult);

			startActivity(intent);

			return true;
			
			

		case R.id.programacao:
			Intent intentListarA = new Intent(ListarFavoritos.this,
					ConfiguracoesActivity.class);

			intentListarA.putExtra("idResidencia", jLoginResult);

			startActivity(intentListarA);
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

		

		this.listaResidencias = (ListView) findViewById(R.id.listViewFavoritos);

		/*
		 * this.listaResidencias.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) {
		 * 
		 * 
		 * 
		 * Residencia residencia = (Residencia) listaResidencias
		 * .getItemAtPosition(position);
		 * 
		 * 
		 * 
		 * senddispositivosToActivity(residencia); } });
		 */

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

		Intent intent = new Intent(ListarFavoritos.this, ListarFavoritos.class);

		intent.putExtra("idResidencia", residencia.getChave());

		startActivity(intent);
	}

	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Favoritos...");

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

				response = AcessoWSDL.buscarAmbientes(params[0]);

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

				listaFavoritosAdapter = new ListaFavoritosAdapter(
						ListarFavoritos.this,
						ListarFavoritos.this.criarListaFavoritos(jResult,
								jLoginResult));

				listaResidencias.setAdapter(listaFavoritosAdapter);

				

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public ArrayList<DispositivoGson> criarListaFavoritos(String json,
			String Chave) {

		JSONArray jsonArray = null;

		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		try {
			jsonArray = new JSONArray(json);

			Ambiente ambiente = null;
			Residencia residencia = null;
			DispositivoGson dispositivo = null;

			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jsonObject = jsonArray.getJSONObject(i);

				ambiente = new Ambiente();
				residencia = new Residencia();
				dispositivo = new DispositivoGson();

				String ambient = jsonObject.getString("Ambiente");

				JSONObject chaveAmbient = new JSONObject(ambient);
				String chaveResidenci = chaveAmbient.getString("Residencia");
				JSONObject chav = new JSONObject(chaveResidenci);
				residencia.setChave(chav.getString("Chave"));

				if (chav.getString("Chave").equals(Chave)) {

					String descricaoDispositivo = jsonObject
							.getString("Descricao");
					String dispositivoChave = jsonObject.getString("Chave");
					String dataCadastroDispositivo = jsonObject
							.getString("DataCadastro");

					try {
						String statusDispositivo = jsonObject
								.getString("Status");
						dispositivo.setStatusDispositivos(statusDispositivo);

					} catch (Exception e) {

						dispositivo.setStatusDispositivos("False");
						
					}

					try {
						String dispositivoFavorito = jsonObject
								.getString("Favorito");
						dispositivo.setFavorito(dispositivoFavorito);

					} catch (Exception e) {

						dispositivo.setFavorito("False");
						
					}

					dispositivo.setDescricao(descricaoDispositivo);
					dispositivo.setChaveDispositivos(dispositivoChave);
					dispositivo
							.setDataCadastroDispositivos(dataCadastroDispositivo);
					

					String ambien = jsonObject.getString("Ambiente");
					JSONObject chaveAmbiente = new JSONObject(ambien);

					String chaveResidencia = chaveAmbiente
							.getString("Residencia");
					JSONObject chave = new JSONObject(chaveResidencia);
					residencia.setChave(chave.getString("Chave"));

					String c = jsonObject.getString("Ambiente");
					JSONObject ate = new JSONObject(c);
					ambiente.setDescricao(ate.getString("Descricao"));
					ambiente.setChaveAmbiente(ate.getString("Chave"));
					dispositivo.setChaveAmbiente(ambiente.getChaveAmbiente());

					String porta = jsonObject.getString("Porta");
					JSONObject portas = new JSONObject(porta);
					dispositivo.setChavePorta(portas.getString("Chave"));

					if (dispositivo.getFavorito().equals("true")) {

						favorito = "true";

						dispositivos.add(dispositivo);

						
					}
				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (favorito != "true") {

			Toast.makeText(
					getApplication(),
					"VOCÊ NÃO TEM DISPOSITOS\nFAVORITOS\nCADASTRE PARA FACILITAR SUA NAVEGAÇÃO",
					Toast.LENGTH_LONG).show();

			Intent intent = new Intent(ListarFavoritos.this,
					OpcoesUsuariosActivity.class);

			intent.putExtra("idResidencia", jLoginResult);

			startActivity(intent);

		}
		return dispositivos;

	}

}
