package br.com.adeusunibratec.mb;

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
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.bean.Usuario;


import br.com.adeusunibratec.dao.UsuarioDAO;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ResidenciasActivity extends Activity implements OnClickListener {

	private String jLoginResult = null;
	private Intent intent;
	private ListView listaResidencias;

	private ListaResidenciaAdapter listaResidenciaAdapter;

	private TextView boasVindas;

	private ProgressDialog progressDialog;

	private String ChaveResidencia = null;
	boolean favoritos = false;

	String chave = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_residencias);

		createProgressDialog();

		this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("jLoginResult");
		
		

		if (jLoginResult != null) {
			try {

				Usuario usuario = JSONParserManager.parseJSONSapiensFirstAccess(this.jLoginResult);
				
				UsuarioDAO dao = new UsuarioDAO(this);
				dao.inserir(usuario);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {

			Intent loginIntent = new Intent(ResidenciasActivity.this,
					LoginActivity.class);
			this.startActivity(loginIntent);
			this.finish();
		}

		setupViews();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.residencias, menu);
		return true;
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

		this.boasVindas = (TextView) findViewById(R.id.textBoasVindas);
		this.listaResidencias = (ListView) findViewById(R.id.listView1);

		this.listaResidencias.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Residencia residencia = (Residencia) listaResidencias
						.getItemAtPosition(position);

				ChaveResidencia = residencia.getChave();

				senddispositivosToActivity(residencia);
			}
		});

		String sBoasVindasFormat = getResources()
				.getString(R.string.boasvindas);
		String sBoasVindas = null;

		sBoasVindas = String.format(sBoasVindasFormat, "",
				HomeAutomexJSONObject.getInstance().getUsuario().getNome());

		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		this.boasVindas.setText(sBoasVindas);

		// lembrando que ï¿½ para colocar a string chave dentro do parametro o "2"
		// ï¿½ sï¿½ para testar o metodo

		new ResidenciaTask(this.progressDialog).execute(chave);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	private void senddispositivosToActivity(Residencia residencia) {

		new ConsultarFavoritos(this.progressDialog).execute(chave);

		// Toast.makeText(getApplication(), "eu to aqui",
		// Toast.LENGTH_LONG).show();

		/*
		 * if(favoritos){
		 * 
		 * Toast.makeText(getApplication(), "Chegou no 1",
		 * Toast.LENGTH_LONG).show();
		 * 
		 * Intent intent = new Intent(ResidenciasActivity.this,
		 * ListarFavoritos.class);
		 * 
		 * intent.putExtra("idResidencia", residencia.getChave());
		 * 
		 * startActivity(intent);
		 * 
		 * }
		 * 
		 * else{ Toast.makeText(getApplication(), "chegou no 2",
		 * Toast.LENGTH_LONG).show(); Intent intent = new
		 * Intent(ResidenciasActivity.this, OpcoesUsuariosActivity.class);
		 * 
		 * intent.putExtra("idResidencia", residencia.getChave());
		 * 
		 * startActivity(intent);
		 * 
		 * }
		 */

	}

	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Residencias...");

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

				response = AcessoWSDL.buscarResidencia(params[0]);

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

			// favoritos = true;

			if (jResult != null) {

				this.progressDialog.dismiss();

				listaResidenciaAdapter = new ListaResidenciaAdapter(
						ResidenciasActivity.this,
						ResidenciasActivity.this.criarListaResidencias(jResult));
				listaResidencias.setAdapter(listaResidenciaAdapter);

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public class ConsultarFavoritos extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ConsultarFavoritos(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog
					.setMessage("Aguarde Enquanto\nCarregamos Suas\nConfigurações...");

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

				
				criarListaFavoritos(jResult, ChaveResidencia);

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public void criarListaFavoritos(String json, String Chave) {

		

		JSONArray jsonArray = null;

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

				if (chav.getString("Chave").equals(ChaveResidencia)) {

					// Toast.makeText(getApplication(), "verade",
					// Toast.LENGTH_LONG).show();

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

					if (dispositivo.getFavorito().equals("true")) {

						favoritos = true;
						
						//break;
					}

					

				}

			}
			

			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (favoritos == false) {

		// Toast.makeText(getApplication(), "ele ï¿½ false",
		// Toast.LENGTH_LONG).show();

			Intent intentFav = new Intent(this,
					OpcoesUsuariosActivity.class);

			intentFav.putExtra("idResidencia", ChaveResidencia);

			startActivity(intentFav);
	}
		
		else{
			
			Intent intentFav = new Intent(ResidenciasActivity.this,
					ListarFavoritos.class);

			intentFav.putExtra("idResidencia", chave);

			startActivity(intentFav);
			
		}

		
		

	}

	public ArrayList<Residencia> criarListaResidencias(String json) {
		ArrayList<Residencia> residencias = null;

		JSONObject jsonArra = null;

		try {

			jsonArra = new JSONObject(json);
			residencias = JSONParserManager.parserArrayResidencias(jsonArra);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return residencias;
	}

	public ArrayList<Usuario> criarListaUsuarios(String json) {
		ArrayList<Usuario> usuarios = null;
		JSONArray jsonArray = null;

		try {

			jsonArray = new JSONArray(json);
			usuarios = JSONParserManager.parserArrayUsuario(jsonArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return usuarios;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {

		case R.id.opcaoUsuario:
			

			Intent intent = new Intent(ResidenciasActivity.this,
					OpcoesUsuariosActivity.class);

			intent.putExtra("idResidencia", jLoginResult);

			startActivity(intent);
		
			
			this.finish();

			return true;
			
			

		case R.id.programacao:
			Intent intentListarA = new Intent(ResidenciasActivity.this,
					ConfiguracoesActivity.class);

			intentListarA.putExtra("idResidencia", jLoginResult);

			startActivity(intentListarA);
			this.finish();
			return true;
			
		case R.id.trocarUsuario:
			Intent intentTrocaUsuario = new Intent(ResidenciasActivity.this,
					LoginActivity.class);
			
			UsuarioDAO dao = new UsuarioDAO(this);
			dao.excluir();

			startActivity(intentTrocaUsuario);
			this.finish();
			return true;
		
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
