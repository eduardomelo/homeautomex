package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
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
import br.com.adeusunibratec.adapter.FavoritosViewAdapter;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.AmbienteGson;
import br.com.adeusunibratec.bean.DispositivoGson;

import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.bean.ResidenciaGson;
import br.com.adeusunibratec.bean.User;
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
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class AmbienteActivity extends Activity {

	AmbientesAdapter listAdapter;
	ExpandableListView expListView;

	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	// CheckBox check;

	private String jLoginResult = null;
	private Intent intent;

	private String result;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ambiente);

		createProgressDialog();

		intent = getIntent();
		result = intent.getStringExtra("idResidencia");

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

			Intent intent = new Intent(this, OpcoesUsuariosActivity.class);

			intent.putExtra("idResidencia", result);

			startActivity(intent);

			this.finish();

			return true;

		case R.id.programacao:
			Intent intentListarA = new Intent(this, ConfiguracoesActivity.class);

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
			
			
		case R.id.sair:
			Intent intentSair = new Intent(Intent.ACTION_MAIN); finish();
			
			this.finish();
			return true;
			

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void setupViews() {

		String chave = null;
		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		// lembrando a qui vai o result da activity anterior ou seja algum id
		// coloquei "2" só pra testar
		new ResidenciaTask(this.progressDialog).execute(chave);
		// ----------------------------------------------------------------------------//
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				/*
				 * Toast.makeText(getApplicationContext(), "Group Clicked ",
				 * Toast.LENGTH_SHORT).show();
				 */
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				/*
				 * Toast.makeText(getApplicationContext(),
				 * listDataHeader.get(groupPosition) + " Expanded",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				/*
				 * Toast.makeText(getApplicationContext(),
				 * listDataHeader.get(groupPosition) + " Collapsed",
				 * Toast.LENGTH_SHORT).show();
				 */

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				/*
				 * Toast.makeText( getApplicationContext(),
				 * listDataHeader.get(groupPosition) + " : " +
				 * listDataChild.get( listDataHeader.get(groupPosition)).get(
				 * childPosition), Toast.LENGTH_SHORT)
				 */
				// .show();// TODO Auto-generated method stub

				return false;
			}
		});

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

	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		// private int perfil;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Ambientes...");

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

			String chaveA = "";

			if (jResult != null) {

				this.progressDialog.dismiss();
				ArrayList<Ambiente> ambiente = criarListaAmbiente(jResult,
						result, chaveA);

				int cont = -0;

				listDataHeader = new ArrayList<String>();
				listDataChild = new HashMap<String, List<String>>();

				// dispo.add("bnm");
				for (Ambiente am : ambiente) {
					cont++;

					listDataHeader.add(am.getDescricao());

					List<String> dispo = new ArrayList<String>();

					ArrayList<DispositivoGson> ambi = criarListaDispositivo(
							jResult, result, am.getChaveAmbiente());

					for (DispositivoGson a : ambi) {

						String dispositiv = "{\"Chave\":"
								+ a.getChaveDispositivos() + ",\"Status\":"
								+ a.getStatusDispositivos() + ",\"Favorito\":"
								+ a.getFavorito() + ",\"Descricao\":\""
								+ a.getDescricao() + "\",\"DataCadastro\":\""
								+ a.getDataCadastroDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ a.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":" + a.getChavePorta()
								+ "}}";

						// Log.e("tstando log favorito","aqui"+dispositiv );

						dispo.add(dispositiv);

					}

					listDataChild.put(listDataHeader.get(cont - 1), dispo);

				}

				listAdapter = new AmbientesAdapter(AmbienteActivity.this,
						listDataHeader, listDataChild);

				expListView.setAdapter(listAdapter);

			}

			else {

				// mensagem de erro
			}
		}
	}

	public ArrayList<Ambiente> criarListaAmbiente(String json, String Chave,
			String ChaveA) {
		JSONArray jsonArray = null;

		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		try {
			jsonArray = new JSONArray(json);

			Ambiente ambiente = null;
			Residencia residencia = null;
			DispositivoGson dispositivo = null;

			HashSet lista = new HashSet();

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

					String a = jsonObject.getString("Descricao");
					String b = jsonObject.getString("Chave");
					String d = jsonObject.getString("DataCadastro");

					dispositivo.setDescricao(a);

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

					ambientes.add(ambiente);

					lista.add(ambiente);

				}

			}

			for (Iterator it = lista.iterator(); it.hasNext();) {

				Object objeto = it.next();

				Ambiente producto = (Ambiente) objeto;

				hg.add(producto);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hg;
	}

	public ArrayList<DispositivoGson> criarListaDispositivo(String json,
			String Chave, String ChaveA) {
		JSONArray jsonArray = null;

		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();

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

					if (ambiente.getChaveAmbiente().equals(ChaveA)) {

						dispositivos.add(dispositivo);

					}
				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dispositivos;
	}

	public ArrayList<Residencia> criarListaResidencias(String json) {
		ArrayList<Residencia> residencias = null;
		JSONObject jsonArray = null;

		try {
			jsonArray = new JSONObject(json);

			residencias = JSONParserManager.parserArrayResidencias(jsonArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return residencias;
	}

}
