package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.R;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class FavoritosActivity extends Activity {

	FavoritosViewAdapter listAdapter;
	ExpandableListView expListView;

	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	// CheckBox check;

	private String jLoginResult = null;
	private Intent intent;

	private String result;
	String favorit = "";
	String dispositivo = "";
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritos);

		createProgressDialog();

		intent = getIntent();
		result = intent.getStringExtra("idResidencia");

		Toast.makeText(getApplication(), result, Toast.LENGTH_LONG).show();

		setupViews();

	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();// TODO Auto-generated method stub

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
			AmbienteActivity ambAct = new AmbienteActivity();
			if (jResult != null) {

				
				this.progressDialog.dismiss();
				ArrayList<Ambiente> ambiente = ambAct.criarListaAmbiente(jResult,
						result, chaveA);

				int cont = -0;

				listDataHeader = new ArrayList<String>();
				listDataChild = new HashMap<String, List<String>>();

				// dispo.add("bnm");
				for (Ambiente am : ambiente) {
					cont++;

					listDataHeader.add(am.getDescricao());

					List<String> dispo = new ArrayList<String>();

					ArrayList<DispositivoGson> ambi = ambAct.criarListaDispositivo(
							jResult, result, am.getChaveAmbiente());

					for (DispositivoGson a : ambi) {

						
						 
						 
						String dispositiv ="{\"Chave\":"
									+ a.getChaveDispositivos()
									+ ",\"Favorito\":"
									+ a.getFavorito()
									+",\"Descricao\":\""
									 + a.getDescricao() + "\",\"DataCadastro\":\"" + a.getDataCadastroDispositivos()
									+ "\",\"Ambiente\":{\"Chave\":"
									+ a.getChaveAmbiente()
									+ "},\"Porta\":{\"Chave\":"
									+ a.getChavePorta() + "}}";
						 
						
						dispo.add(dispositiv);

						
					}

					listDataChild.put(listDataHeader.get(cont - 1), dispo);

				}

				listAdapter = new FavoritosViewAdapter(FavoritosActivity.this,
						listDataHeader, listDataChild);

				

				expListView.setAdapter(listAdapter);

				
				
				
				
				
				
							} else {

				// mensagem de erro
			}
		}
	}

	public ArrayList<Residencia> criarListaResidencias(String json) {
		ArrayList<Residencia> residencias = null;
		JSONArray jsonArray = null;

		try {
			jsonArray = new JSONArray(json);

			

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return residencias;
	}

}
