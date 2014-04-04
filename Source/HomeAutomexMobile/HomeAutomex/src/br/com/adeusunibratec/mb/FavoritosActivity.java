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
import br.com.adeusunibratec.adapter.FavoritosViewAdapter;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.R;
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

	// private TextView boasVindas;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritos);

		createProgressDialog();

		/*this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("jLoginResult");

		if (jLoginResult != null) {
			try {

				Log.e("primeiro", "entrou");
				JSONParserManager
						.parseJSONSapiensFirstAccess(this.jLoginResult);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {

			Intent loginIntent = new Intent(FavoritosActivity.this,
					LoginActivity.class);
			this.startActivity(loginIntent);
			this.finish();
		}
*/
		setupViews();

	}

	private void setupViews() {

		/*this.boasVindas = (TextView) findViewById(R.id.textBoasVindas);

		String sBoasVindasFormat = getResources()
				.getString(R.string.boasvindas);

		String sBoasVindas = null;
		sBoasVindas = String.format(sBoasVindasFormat, "",
				HomeAutomexJSONObject.getInstance().getUsuario().getNome());
		this.boasVindas.setText(sBoasVindas);*/

		
		String chave = null;
		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		new ResidenciaTask(this.progressDialog).execute("2");

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				Toast.makeText(getApplicationContext(), "Group Clicked ",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

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
			this.progressDialog.setMessage("Carregando Residencias...");

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

			Log.e("onPostExecute", jResult);
			if (jResult != null) {

				this.progressDialog.dismiss();

				List<Residencia> resultado = null;

				resultado = new ArrayList<Residencia>();

				try {
					JSONArray jsonArray = new JSONArray(jResult.toString());

					listDataHeader = new ArrayList<String>();
					listDataChild = new HashMap<String, List<String>>();

					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						Residencia residencia = new Residencia();

						residencia.setCidade((String) jsonObject
								.getString("Cidade"));
						residencia.setBairro((String) jsonObject
								.getString("Bairro"));
						residencia.setCep((String) jsonObject.getString("Cep"));

						residencia.setCidade(jsonArray.getJSONObject(i)
								.getString("Cidade"));
						String a = residencia.getCidade();

						Log.e("testeeeeeee", a);

						resultado.add(residencia);

						// for(int b1 = 0; b1 < 2; b1++) {

						// String a = residencia.getCidade();
						String b = residencia.getBairro();

						String f = jsonArray.getJSONObject(i).getString(
								"Cidade");

						// Log.e("tasssssssssss", f);
						listDataHeader.add(f);

						// Adding child data
						List<String> dispositivos = new ArrayList<String>();
						dispositivos.add(residencia.getBairro());
						dispositivos.add(residencia.getCep());
						dispositivos.add("teste sub item");

						listDataChild.put(listDataHeader.get(i), dispositivos); // Header,
																				// Child
																				// data
						// }

						// residencia.setCidade((String)
						// jsonObject.getString("Cidade"));

						Log.e("cidade", residencia.toString());
						// resultado.add(usuario);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				listAdapter = new FavoritosViewAdapter(FavoritosActivity.this,
						listDataHeader, listDataChild);

				Log.e("listAdapter", listAdapter.toString());

				expListView.setAdapter(listAdapter);

				// listAdapter = new TesteListViewAdapter(this, listDataHeader,
				// listDataChild);

				// Toast.makeText(getApplicationContext(),"retorno da lista de usuario"+jResult,Toast.LENGTH_LONG).show();
				Log.e("result retorno", jResult);
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

			residencias = JSONParserManager.parserArrayResidencias(jsonArray);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return residencias;
	}



}
