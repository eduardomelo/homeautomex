package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.dao.UsuarioDAO;
import br.com.adeusunibratec.mb.R;
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
	
	DispositivoGson dispositivo;

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

			
			if (jResult != null) {

				this.progressDialog.dismiss();

				
				  listaCenariosAdapter = new ListarCenariosAdapter(
				  ListarCenariosActivity.this,
				  ListarCenariosActivity.this.criarListaCenarios(jResult,
				  jLoginResult));
				  
				  listaCenarios.setAdapter(listaCenariosAdapter);
				 

				

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public ArrayList<DispositivoGson> criarListaCenarios(String json,
			String Chave) {

		JSONArray jsonArray = null;
		JSONArray jsonDispositivo = null;
		
		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		try {
			jsonArray = new JSONArray(json);
			
			
			HashSet lista = new HashSet();

			Ambiente ambiente = null;
			Residencia residencia = null;
			

			for (int i = 0; i < jsonArray.length(); i++) {
				
				residencia = new Residencia();
				 dispositivo = new DispositivoGson();
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String descricaoCenario = jsonObject.getString("Descricao");
				String cenarioChave = jsonObject.getString("Chave");

				 String dispoteste = jsonObject.getString("Dispositivo");
					
				 
				 jsonDispositivo = new JSONArray(dispoteste);
				
				  
				  
			       for (int k = 0; k < jsonDispositivo.length(); k++) {
			    	try{   
				 
			    		JSONObject jsonObj = jsonDispositivo.getJSONObject(0);
					    String testambient = jsonObj.getString("Ambiente");
			    	    JSONObject chaveAmbi = new JSONObject(testambient);
					    String chaveResi = chaveAmbi.getString("Residencia");
					    JSONObject chavR = new JSONObject(chaveResi);
					    residencia.setCep(chavR.getString("Chave"));
					    ambiente = new Ambiente();
					    if (residencia.getCep().equals(jLoginResult)) {
					   
					    	Log.e("descricao", descricaoCenario);
					    	Log.e("conta", residencia.getCep());
					    	
					    	ambiente.setDescricao(descricaoCenario);
					    	ambiente.setChave(cenarioChave);
					    	lista.add(ambiente);
					    }
			    	}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			       }
				
				
				
				
				

			}
			
			for (Iterator it = lista.iterator(); it.hasNext();) {

				Object objeto = it.next();

				Ambiente producto = (Ambiente) objeto;

				Log.e("teste for", "aqui"+producto.getDescricao()+"chave"+producto.getChave());
				
				/*dispositivo.setDescricaoCenario(descricaoCenario);
				dispositivo.setChaveCenario(cenarioChave);
				dispositivo.setChaveUsuario(jLoginResult);
				dispositivos.add(dispositivo);*/
				 dispositivo = new DispositivoGson();
				
				 dispositivo.setDescricaoCenario(producto.getDescricao());
				dispositivo.setChaveCenario(producto.getChave());
				dispositivo.setChaveUsuario(jLoginResult);
				dispositivos.add(dispositivo);
				hg.add(producto);
			}
			
			
			  

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dispositivos;

	}

}

























/*package br.com.adeusunibratec.mb;

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
import br.com.adeusunibratec.adapter.CadastroDispositivosCenarioAdapter;
import br.com.adeusunibratec.adapter.ListaFavoritosAdapter;
import br.com.adeusunibratec.adapter.ListaResidenciaAdapter;
import br.com.adeusunibratec.adapter.ListarCenariosAdapter;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.mb.R;
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
	
	String descricaoCenario;
	//ArrayList<DispositivoGson>  dispositivos;
	String chaveCe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_cenarios);

		createProgressDialog();

		this.intent = getIntent();

		this.jLoginResult = intent.getStringExtra("idResidencia");

		
		Log.e("olha chave residencia","ds"+ jLoginResult);
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
				
				//response = AcessoWSDL.buscarAmbientes(params[0]);
				
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

				
				  listaCenariosAdapter = new ListarCenariosAdapter(
				  ListarCenariosActivity.this,
				  ListarCenariosActivity.this.criarListaCenarios(jResult,
				  jLoginResult));
				  
				  listaCenarios.setAdapter(listaCenariosAdapter);
				 

				

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public ArrayList<DispositivoGson> criarListaCenarios(String json,
			String Chave) {

		
		
		
				
	
		
		
		
	

	

		
		
		
		
		
		
		JSONArray jsonArray = null;

		JSONArray jsonTeste = null;
		
		JSONArray jsonDispositivo = null;
		// dispositivos = new ArrayList<DispositivoGson>();
		 
		 ArrayList<DispositivoGson>  descrCenario = new ArrayList<DispositivoGson>();

		 ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		 
		//ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		
		
		
		
		try {
			jsonArray = new JSONArray(json);

			Ambiente ambiente = null;
			Residencia residencia = null;
		//DispositivoGson dispositivo = null;

			HashSet lista = new HashSet();

			for (int i = 0; i < jsonArray.length(); i++) {
                
               
				
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				ambiente = new Ambiente();
				residencia = new Residencia();
				//dispositivo = new DispositivoGson();

				String ambient = jsonObject.getString("Ambiente");

				JSONObject chaveAmbient = new JSONObject(ambient);
				String chaveResidenci = chaveAmbient.getString("Residencia");
				JSONObject chav = new JSONObject(chaveResidenci);
				residencia.setChave(chav.getString("Chave"));

				String aa = jsonObject.getString("Cenario");
				
			//	Log.e("string json", aa);
				try{
					jsonTeste = new JSONArray(aa);
					 
					for (int j = 0; j <jsonTeste.length(); j++) {

						
						
						JSONObject jsonOb = jsonTeste.getJSONObject(j);

						descricaoCenario = jsonOb.getString("Descricao");
						
						chaveCe = jsonOb.getString("Chave");
						
						
				         
						
				       Log.e("olha descricao",""+descricaoCenario);
					   
					     
					      String dispoteste = jsonOb.getString("Dispositivo");
					     
					    // JSONObject TesteDispo = new JSONObject(dispoteste);
					     
					    
					     jsonDispositivo = new JSONArray(dispoteste);
					     
					    for (int k = 0; k < jsonDispositivo.length(); k++) {
							
					    	 JSONObject jsonObj = jsonDispositivo.getJSONObject(0);
							    
						     String testambient = jsonObj.getString("Ambiente");

						     JSONObject chaveAmbi = new JSONObject(testambient);
						     
						     String chaveResi = chaveAmbi.getString("Residencia");
						     
						     JSONObject chavR = new JSONObject(chaveResi);
						    
						    
						     
						     residencia.setCep(chavR.getString("Chave"));
					    
					    }
					       
					    
					     
					   
					     
					          if (chav.getString("Chave").equals(jLoginResult)) {
					    	 
					    	 DispositivoGson dispositivo = new DispositivoGson();
					    	 // Log.e("ambiente","ambiente"+residencia.getCep());
					    	 
					    	 Log.e("cenario",""+descricaoCenario+"chave cenario"+chaveCe);
					    	 
					    	 
					    	dispositivo.setDescricaoCenario(descricaoCenario);
								dispositivo.setChaveCenario(chaveCe);
								
								dispositivo.setChaveUsuario(jLoginResult);	
								
								dispositivos.add(dispositivo);
								
								
					    	 
					    	 
					    	 
					    	 
					    	 ambiente = new Ambiente();

								ambiente.setDescricao(descricaoCenario);
								
								ambiente.setChaveAmbiente(chaveCe);
								
								
							
								
								lista.add(ambiente);

					    	 
					     }
							
							
							
					     
					     
					     
					   //  Log.e("olha","dispositivo"+jsonDispositivo);
					     
					
					}
				}
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject test = new JSONObject(aa);
				
				residencia.setBairro(test.getString("Descricao"));
				   // jsonTeste = new JSONArray(aa);
				
				    //  Log.e("jsonteste","olha"+aa);
				
				//Log.e("logchave", chav.getString("Chave") + "resl" + result);
				    if (chav.getString("Chave").equals(Chave)) {

					String a = jsonObject.getString("Descricao");
					String b = jsonObject.getString("Chave");
					String d = jsonObject.getString("DataCadastro");

					        dispositivo.setDescricao(a);
					        dispositivo.setChaveCenario(b);
					
					
	
				}

			}

			
			
			for (Iterator it = lista.iterator(); it.hasNext();) {
				//DispositivoGson d = new DispositivoGson();
				
				Object objeto = it.next();

				Ambiente descricaoCenario = (Ambiente) objeto;
				
				
				
				Log.e("olha o", descricaoCenario.getDescricao()+descricaoCenario.getChaveAmbiente());
				
				         d.setDescricaoCenario("teste");
					d.setChaveCenario("1");
					
					d.setChaveUsuario(jLoginResult);	
					
					dispositivos.add(d);
					
				
//				d.setDescricao("teset");
//				d.setChaveCenario("1");
//				d.setChaveUsuario(jLoginResult);
				//Log.e("olha o", descricaoCenario.getDescricao()+descricaoCenario.getChaveAmbiente());
				
				//dispositivos.add(d);
			}

			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	JSONArray jsonArray = null;
		JSONArray jsonDispositivo = null;
		HashSet list = new HashSet();
		
		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		
		ArrayList<Dispositivo> dispositiv = new ArrayList<Dispositivo>();
		
		
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
		
		HashSet lista = new HashSet();
		
		DispositivoGson dispositivo = new DispositivoGson();
		try {
			jsonArray = new JSONArray(json);

			
			
			DispositivoGson ambiente = null;
			Residencia residencia = null;
			
			
			for (int i = 0; i < jsonArray.length(); i++) {
				
				Residencia resi = new Residencia();
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String descricaoCenario = jsonObject.getString("Descricao");
				
				descricaoCenario = jsonObject.getString("Descricao");
				
				String cenarioChave = jsonObject.getString("Chave");

				
				 String dispoteste = jsonObject.getString("Dispositivo");
				
				  jsonDispositivo = new JSONArray(dispoteste);
				
				  Log.e("descricao", descricaoCenario);
				  
			       for (int k = 0; k < jsonDispositivo.length(); k++) {
						
				    	 JSONObject jsonObj = jsonDispositivo.getJSONObject(0);
				    	 String testambient = jsonObj.getString("Ambiente");
				    	 
				    	 JSONObject chaveAmbi = new JSONObject(testambient);
				    	   String chaveResi = chaveAmbi.getString("Residencia");
				    	   JSONObject chavR = new JSONObject(chaveResi);
				    	   
				    	    resi.setCep(chavR.getString("Chave"));
				    		
				    		ambiente = new DispositivoGson();
				    		if (resi.getCep().equals(jLoginResult)) {
				    			
				    		
				    		  
				    			ambiente.setDescricao(descricaoCenario)	;
				    			ambiente.setChaveUsuario(cenarioChave);
				    			lista.add(ambiente);
				    			
				    			dispositivo.setDescricaoCenario(descricaoCenario);
								dispositivo.setChaveCenario(cenarioChave);
								
								
								
								
								
								dispositivo.setChaveUsuario(jLoginResult);
								
								//lista.add(dispositivo);
								
								dispositivos.add(dispositivo);
				    		   
				    		   
				    		   
				    		
				    	   }
				    	 
				    	  //
				    	   
				  }
				    	 
					    

					    
					     
					  
					     
					     
					    
					    
					     
					   
					     
					    
				    
				    }
				  
			
				
				     // ArrayList<Ambiente> cenario = new ArrayList<Ambiente>();

					
				      
				      
				    
						
				      
				      
				     dispositivo.setDescricaoCenario(descricaoCenario);
					dispositivo.setChaveCenario(cenarioChave);
					
					
					
					
					
					dispositivo.setChaveUsuario(jLoginResult);
					
					lista.add(dispositivo);
					
					dispositivos.add(dispositivo);
				
				
				

			
			
			
					
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Iterator it = lista.iterator(); it.hasNext();) {
			//	cont++;
				
				Object objeto = it.next();

				DispositivoGson producto = (DispositivoGson) objeto;
				
				Log.e("ta ai", producto.getDescricao());
				
				
			

			}

		
		return dispositivos;
		//return descrCenario;

	}

}
*/