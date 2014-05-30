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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.AmbientesAdapter;
import br.com.adeusunibratec.adapter.CadastroDispositivosCenarioAdapter;
import br.com.adeusunibratec.adapter.ListarCenariosAdapter.AtivaCenario;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.AmbienteGson;
import br.com.adeusunibratec.bean.Cenario;
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.ListCenario;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.bean.TesteCenario;

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

public class CadastroDispositivosCenarioActivity extends Activity {

	CadastroDispositivosCenarioAdapter listAdapter;
	ExpandableListView expListView;

	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	// CheckBox check;
	ArrayList<Dispositivo>  dispositivos;
	
	private String jLoginResult = null;
	private Intent intent;

	private String result;

	private ProgressDialog progressDialog;

	String chave = null;
	
	  String chaveCenario = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_dispositivos_cenario);

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

		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		// lembrando a qui vai o result da activity anterior ou seja algum id
		// coloquei "2" só pra testar
		new ResidenciaTask(this.progressDialog).execute(chave);
		// ----------------------------------------------------------------------------//
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandableListViewCadastroDispositivo);

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
			this.progressDialog.setMessage("Carregando Ambientes...");

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

			this.progressDialog.dismiss();
			
			
			
			JSONArray jsonArray = null;

			 dispositivos = new ArrayList<Dispositivo>();

			//ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
			ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
			try {
				jsonArray = new JSONArray(jResult);

				Ambiente ambiente = null;
				Residencia residencia = null;
				Dispositivo dispositivo = null;

				HashSet lista = new HashSet();

				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonObject = jsonArray.getJSONObject(i);

					ambiente = new Ambiente();
					residencia = new Residencia();
					dispositivo = new Dispositivo();

					String ambient = jsonObject.getString("Ambiente");

					JSONObject chaveAmbient = new JSONObject(ambient);
					String chaveResidenci = chaveAmbient.getString("Residencia");
					JSONObject chav = new JSONObject(chaveResidenci);
					residencia.setChave(chav.getString("Chave"));

					if (chav.getString("Chave").equals(result)) {

						String a = jsonObject.getString("Descricao");
						String b = jsonObject.getString("Chave");
						String d = jsonObject.getString("DataCadastro");

						        dispositivo.setDescricao(a);
						        dispositivo.setChave(b);
						
						    dispositivos.add(dispositivo);
						
						//Log.e("olha aqui dispo", dispositivo.getDescricao()+"chave"+dispositivo.getChave());
/*
						String ambien = jsonObject.getString("Ambiente");
						JSONObject chaveAmbiente = new JSONObject(ambien);

						String chaveResidencia = chaveAmbiente
								.getString("Residencia");
						JSONObject chave = new JSONObject(chaveResidencia);
						residencia.setChave(chave.getString("Chave"));

						String c = jsonObject.getString("Ambiente");
						JSONObject ate = new JSONObject(c);
						ambiente.setDescricao(ate.getString("Descricao"));
						ambiente.setChaveAmbiente(ate.getString("Chave"));*/

						//dispositivos.add(ambiente);

						//lista.add(ambiente);

					}

				}

				

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			Gson gson = new Gson();

			Type cenariosetListType = new TypeToken<Collection<Dispositivo>>() {
			}.getType();

			List<Dispositivo> cenariosets = gson.fromJson(jResult,
					cenariosetListType);

			HashSet lista = new HashSet();

			ArrayList<Ambiente> cenario = new ArrayList<Ambiente>();

			ArrayList<Dispositivo> dispositivo = new ArrayList<Dispositivo>();

			Ambiente ambiente = null;
			Dispositivo dispo = null;
			
			int cont = -0;

			listDataHeader = new ArrayList<String>();
			listDataChild = new HashMap<String, List<String>>();
			List<String> descri = new ArrayList<String>();
			
			for (Dispositivo cenarioset : cenariosets) {

				
				
				
				

				//Log.e("contador", Integer.toString(cont-1));
				
				for (Cenario dat : cenarioset.getCenario()) {
					
					ambiente = new Ambiente();

					ambiente.setDescricao(dat.getDescricao());
					
					ambiente.setChaveAmbiente(dat.getChave());
					
					lista.add(ambiente);
					
					chaveCenario = dat.getChave();

					//Log.e("bbbbb", dat.getChave());
				}

				/*dispo = new Dispositivo();

				   dispo.setDescricao(cenarioset.getDescricao());
				dispo.setChave(cenarioset.getChave());
				
				descri.add("\"Descricao\":\""+dispo.getDescricao()+"\",\"ChaveDispositivo\":"+dispo.getChave()+",");
				
				Log.e("aaaaaaa", cenarioset.getDescricao());*/

			}

			for (Iterator it = lista.iterator(); it.hasNext();) {
				cont++;
				Object objeto = it.next();

				Ambiente descricaoCenario = (Ambiente) objeto;

				cenario.add(descricaoCenario);

				listDataHeader.add(descricaoCenario.getDescricao());
				
				//Log.e("cccccc", descricaoCenario.getChaveAmbiente());
				
				List<String> descr = new ArrayList<String>();
				
		for (Dispositivo  abg : dispositivos ) {
					
					
			
					  
				 descr.add("{\"Descricao\":\""+abg.getDescricao()+"\",\"ChaveDispositivo\":"+abg.getChave()+",\"Usuario\":"+chave+",\"chaveCenario\":"+descricaoCenario.getChaveAmbiente()+"}");
					
				 
				//{"Usuario":1,"Dispositivo":[{"Chave":3}],"Chave":3}

				 
					    
				}
			
				listDataChild.put(listDataHeader.get(cont - 1), descr);
			}
			
			//Log.e("cccccc", Integer.toString(cont - 1));
			
			
		
			
			
			
			listAdapter = new CadastroDispositivosCenarioAdapter(CadastroDispositivosCenarioActivity.this,
					listDataHeader, listDataChild);

			

			expListView.setAdapter(listAdapter);


		}
	}

	public class BuscarDispositivoTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		// private int perfil;

		public BuscarDispositivoTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Carregando Ambientes...");

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

			this.progressDialog.dismiss();

			Gson gson = new Gson();

			Type cenariosetListType = new TypeToken<Collection<Cenario>>() {
			}.getType();

			List<Cenario> cenariosets = gson.fromJson(jResult,
					cenariosetListType);

			/*
			 * for (Cenario cenarioset : cenariosets) {
			 * 
			 * cenarioset.getDescricao();
			 * 
			 * cenarioset.getDispositivo();
			 * 
			 * Log.e("aaaaaaa", cenarioset.getDescricao());
			 * 
			 * new BuscarDispositivoTask(this.progressDialog).execute(chave);
			 * 
			 * 
			 * ArrayList<AmbienteGson> af = new ArrayList<AmbienteGson>();
			 * 
			 * for (Dispositivo dat : cenarioset.getDispositivo()) {
			 * 
			 * dat.getAmbiente(); dat.getPorta();
			 * 
			 * }
			 * 
			 * 
			 * }
			 */

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
					// String s = jsonObject.getString("Status"); //
					// Log.e("aaaateste",s);

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
