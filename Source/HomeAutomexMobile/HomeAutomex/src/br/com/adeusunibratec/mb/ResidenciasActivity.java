package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;



import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.ListaResidenciaAdapter;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.bean.Usuario;
import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import br.com.adeusunibratec.parse.HomeAutomexJSONObject;
import br.com.adeusunibratec.parse.JSONParserManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;

public class ResidenciasActivity extends Activity {

	private String jLoginResult = null;
	private Intent intent;
	private ListView listViewResidencia;

//	private ListaUsuarioAdapter listaUsuarioAdapter;
	private ListaResidenciaAdapter listaResidenciaAdapter;

	private TextView boasVindas;
//
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_residencias);

//		createProgressDialog();
//
		this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("jLoginResult");
//
		if (jLoginResult != null) {
			try {

				Log.e("primeiro", "entrou");
				
				Usuario usuario = new Usuario();
				usuario = JSONParserManager.parseJSONUsuario(this.jLoginResult);
                                
                                ArrayList<Residencia> listaResidencia = new ArrayList<Residencia>();
                                listaResidencia = JSONParserManager.parseJSONResidencia(jLoginResult);

                                this.boasVindas = (TextView) findViewById(R.id.textBoasVindas);
				this.boasVindas.setText("Bem vindo(a) " + usuario.getNome());
                                
                                ListaResidenciaAdapter adapter = new ListaResidenciaAdapter(this, listaResidencia);
                                this.listViewResidencia = (ListView) findViewById(R.id.listView1);
                                this.listViewResidencia.setAdapter(adapter);
                                
                               this.listViewResidencia.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                                        Residencia residencia = (Residencia) listViewResidencia.getItemAtPosition(position);
       
                                        senddispositivosToActivity(residencia);
                                    }
                                });
                                
			} catch (JSONException e) {
				e.printStackTrace();
			}catch (Exception e){
                            e.printStackTrace();
                        }
		} else {

			Intent loginIntent = new Intent(ResidenciasActivity.this,
					LoginActivity.class);
			this.startActivity(loginIntent);
			this.finish();
		}

//		setupViews();

	}

//	@Override
//	protected void onPause() {
//		super.onPause();
//		if (progressDialog != null && progressDialog.isShowing()) {
//			progressDialog.cancel();
//		}
//	}
//
//	@Override
//	protected void onStop() {
//		super.onStop();
//		if (progressDialog != null && progressDialog.isShowing()) {
//			progressDialog.cancel();
//		}
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		if (progressDialog != null && progressDialog.isShowing()) {
//			progressDialog.cancel();
//		}
//	}
//
//	private void createProgressDialog() {
//		this.progressDialog = new ProgressDialog(this);
//		this.progressDialog.setCancelable(false);
//	}
//
//	private void setupViews() {
//
//		Log.e("ddd", "entrouuuu");
//		this.boasVindas = (TextView) findViewById(R.id.textBoasVindas);
//		this.listaResidencias = (ListView) findViewById(R.id.listView1);
//
//		this.listaResidencias.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//
//			
//				
//				Residencia residencia = (Residencia) listaResidencias
//						.getItemAtPosition(position);
//				
//				
//
//				senddispositivosToActivity(residencia);
//			}
//		});
//
//		String sBoasVindasFormat = getResources()
//				.getString(R.string.boasvindas);
//		String sBoasVindas = null;
//		String chave = null;
//
//		sBoasVindas = String.format(sBoasVindasFormat, "",
//				HomeAutomexJSONObject.getInstance().getUsuario().getNome());
//
//		 chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();
//
//		this.boasVindas.setText(sBoasVindas);
//
//		//lembrando que � para colocar a string chave dentro do parametro o "2" � s� para testar o metodo
//		new ResidenciaTask(this.progressDialog).execute("2");
//
//	}
//
//	@Override
//	public void onClick(View arg0) {
//		// TODO Auto-generated method stub
//
//	}

	private void senddispositivosToActivity(Residencia residencia) {
		// Bundle value = createResidenciaBundle(residencia);

		Intent intent = new Intent(ResidenciasActivity.this,
				OpcoesUsuariosActivity.class);

		intent.putExtra("idResidencia", residencia.getNumero());
		
		

		startActivity(intent);
	}

	

//	public class ResidenciaTask extends AsyncTask<String, Void, String> {
//
//		private ProgressDialog progressDialog;
//
//		
//		public ResidenciaTask(ProgressDialog params) {
//
//			this.progressDialog = params;
//			this.progressDialog.setMessage("Carregando Residencias...");
//
//		}
//
//		@Override
//		protected void onCancelled() {
//			super.onCancelled();
//			this.progressDialog.dismiss();
//		}
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			this.progressDialog.show();
//		}
//
//		@Override
//		protected String doInBackground(String... params) {
//
//			String response = null;
//			try {
//
//				response = AcessoWSDL.buscarResidencia(params[0]);
//
//			} catch (ClientProtocolException e) {
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (XmlPullParserException e) {
//				e.printStackTrace();
//			}
//
//			return response;
//		}
//
//		@Override
//		protected void onPostExecute(String jResult) {
//			super.onPostExecute(jResult);
//
//			Log.e("onPostExecute", jResult);
//			if (jResult != null) {
//
//				this.progressDialog.dismiss();
//
//				/*
//				 * listaUsuarioAdapter = new ListaUsuarioAdapter(
//				 * TesteLoginActivity.this,
//				 * TesteLoginActivity.this.criarListaUsuarios(jResult));
//				 * listaResidencias.setAdapter(listaUsuarioAdapter);
//				 */
//
//				listaResidenciaAdapter = new ListaResidenciaAdapter(
//						ResidenciasActivity.this,
//						ResidenciasActivity.this.criarListaResidencias(jResult));
//				listaResidencias.setAdapter(listaResidenciaAdapter);
//
//				// Toast.makeText(getApplicationContext(),"retorno da lista de usuario"+jResult,Toast.LENGTH_LONG).show();
//				Log.e("result retorno", jResult);
//				/*
//				 * try { JSONObject checkErro = new JSONObject(jResult); if
//				 * (checkErro.has(JSONFields.ERRO)) {
//				 * this.progressDialog.dismiss();
//				 * Toast.makeText(getApplicationContext(),
//				 * checkErro.getString(JSONFields.ERRO),
//				 * Toast.LENGTH_SHORT).show(); } else {
//				 * this.progressDialog.dismiss();
//				 * 
//				 * listaUsuarioAdapter = new ListaUsuarioAdapter(
//				 * TesteLoginActivity.this,
//				 * TesteLoginActivity.this.criarListaUsuarios(jResult));
//				 * 
//				 * Toast.makeText(getApplication(), "chegou no ponto" + jResult,
//				 * Toast.LENGTH_LONG).show();
//				 * 
//				 * 
//				 * } }
//				 *//*
//					 * catch (JSONException e) { e.printStackTrace(); }
//					 */
//			} else {
//				this.progressDialog.dismiss();
//				// mensagem de erro
//			}
//		}
//	}
//
//	public ArrayList<Residencia> criarListaResidencias(String json) {
//		ArrayList<Residencia> residencias = null;
//		JSONArray jsonArray = null;
//
//		try {
//			jsonArray = new JSONArray(json);
//
//			residencias = JSONParserManager.parserArrayResidencias(jsonArray);
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return residencias;
//	}
//
//	public ArrayList<Usuario> criarListaUsuarios(String json) {
//		ArrayList<Usuario> usuarios = null;
//		JSONArray jsonArray = null;
//		Log.e("entrando em criar lista", json);
//		try {
//
//			jsonArray = new JSONArray(json);
//			usuarios = JSONParserManager.parserArrayUsuario(jsonArray);
//			Log.e("criando lista", usuarios.toString());
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return usuarios;
//	}

}
