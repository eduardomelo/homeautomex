package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.ListaFavoritosAdapter.LigarTask;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.AmbienteGson;
import br.com.adeusunibratec.bean.Cenario;
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;

import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.mb.R;


public class ListarCenariosAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<DispositivoGson> listaResidencias;

	private String chaveCenario;

	private AmbientesAdapter ambi;

	boolean liga = false;
	
	private String chavCenario;
	
	private String favorit;

	public ListarCenariosAdapter(Context context,
			ArrayList<DispositivoGson> arrayList) {
		this.context = context;
		this.listaResidencias = arrayList;
	}

	public class ViewHolder {
		public TextView textView;
	}

	@Override
	public int getCount() {
		return listaResidencias.size();
	}

	@Override
	public Object getItem(int position) {
		return listaResidencias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaResidencias.get(position).getIdAmbiente();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView;

		final DispositivoGson ambientes = new DispositivoGson();

		ambientes.setChaveCenario(listaResidencias.get(position)
				.getChaveCenario());
		chaveCenario = ambientes.getChaveCenario();
		ambientes.setDescricaoCenario(listaResidencias.get(position)
				.getDescricaoCenario());
		ambientes.setChaveDispositivos(listaResidencias.get(position)
				.getChaveDispositivos());
		/*ambientes.setFavorito(listaResidencias.get(position).getFavorito());*/
		ambientes.setStatusDispositivos(listaResidencias.get(position)
				.getStatusDispositivos());
		ambientes.setDescricao(listaResidencias.get(position).getDescricao());
		ambientes.setDataAlteracaoDispositivos(listaResidencias.get(position)
				.getDataAlteracaoDispositivos());
		ambientes.setDataCadastroDispositivos(listaResidencias.get(position)
				.getDataCadastroDispositivos());
		ambientes.setChaveAmbiente(listaResidencias.get(position)
				.getChaveAmbiente());
		ambientes.setChavePorta(listaResidencias.get(position).getChavePorta());

		if (convertView == null) {
			itemView = new View(context);

			itemView = inflater.inflate(R.layout.listar_cenario_adapter_layout,
					null);

			TextView textView = (TextView) itemView
					.findViewById(R.id.descricaoCenario);

			textView.setText(listaResidencias.get(position)
					.getDescricaoCenario());

			Switch a = (Switch) itemView.findViewById(R.id.switchCenario);
			
			
			final String chaveUsuario = listaResidencias.get(position)
					.getChaveUsuario();
			
			final String chaveCenario = listaResidencias.get(position)
					.getChaveCenario();

			a.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton arg0,
						boolean isChecked) {
					
					Toast.makeText(context, "clicado"+isChecked, Toast.LENGTH_LONG);
					
					if (isChecked ) {

						
						     liga = true;
						     
						    Log.e("chave cenario aqui", chaveCenario);
						    
						    chavCenario = chaveCenario;
						new StatusDispositivo().execute(chaveUsuario);

					}

					
				
					else {
						
						
						liga = false;
						 chavCenario = chaveCenario;
						new StatusDispositivo().execute(chaveUsuario);
					}

				}
			});

		} else {
			itemView = (View) convertView;
		}

		return itemView;
	}

	public class StatusDispositivo extends AsyncTask<String, Void, String> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
			// this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// this.progressDialog.show();
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

			Log.e("json cenario", jResult);
			
			Gson gson = new Gson();

			Type datasetListType = new TypeToken<Collection<Cenario>>() {
			}.getType();

			List<Cenario> datasets = gson.fromJson(jResult, datasetListType);

			

			for (Cenario dataset : datasets) {

				dataset.getDispositivo();
				Log.e("chave cenario teste2", dataset.getChave());

				ArrayList<AmbienteGson> af = new ArrayList<AmbienteGson>();

				for (Dispositivo dat : dataset.getDispositivo()) {

					dat.getAmbiente();
					dat.getPorta();

					// Log.e("aaaaaaa",dat.getAmbiente().getChave());
					//Log.e("aaaaaaateste",chavCenario);

					if (liga == true && dataset.getChave().equals(chavCenario)) {
						
						     dat.setStatus("true");
						     
						     try {
								 favorit = dat.getFavorito();
									
									Log.e("teste fvorito1", favorit);
									
								} catch (Exception e) {

									favorit = "False";
									Log.e("teste fvorito2", favorit);
								}
						     
						     

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Favorito\":\"" + favorit
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

						Log.e("testecenario1", dispositivo);
						new AtivaCenario().execute(dispositivo);

					}

					if (liga == false && dataset.getChave().equals(chavCenario))  {

						/*Toast.makeText(context, "esse da qui false", Toast.LENGTH_LONG)
								.show();*/
						dat.setStatus("False");

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Favorito\":\"" + favorit
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

						Log.e("testecenario2", dispositivo);
						new AtivaCenario().execute(dispositivo);

					}

				}

			}

		}

	}

	public class AtivaCenario extends AsyncTask<String, Void, String> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
			// this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// this.progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String response = null;
			try {

				response = AcessoWSDL.AcenderLuz(params[0]);

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

			Log.e("Ambientes list", jResult);
			if (jResult != null) {

			}

		}

	}

}










/*package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.ListaFavoritosAdapter.LigarTask;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.AmbienteGson;
import br.com.adeusunibratec.bean.Cenario;
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;

import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.mb.ListarCenariosActivity;
import br.com.adeusunibratec.mb.R;

public class ListarCenariosAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<DispositivoGson> listaResidencias;

	private String chaveCenario;

	private AmbientesAdapter ambi;

	boolean liga = false;
	
	private String chavCenario;
	
	private String favorit;

	public ListarCenariosAdapter(Context context,
			ArrayList<DispositivoGson> arrayList) {
		this.context = context;
		this.listaResidencias = arrayList;
	}

	public class ViewHolder {
		public TextView textView;
	}

	@Override
	public int getCount() {
		return listaResidencias.size();
	}

	@Override
	public Object getItem(int position) {
		return listaResidencias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaResidencias.get(position).getIdAmbiente();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView;

		final DispositivoGson ambientes = new DispositivoGson();

		ambientes.setChaveCenario(listaResidencias.get(position)
				.getChaveCenario());
		chaveCenario = ambientes.getChaveCenario();
		ambientes.setDescricaoCenario(listaResidencias.get(position)
				.getDescricaoCenario());
		ambientes.setChaveDispositivos(listaResidencias.get(position)
				.getChaveDispositivos());
		ambientes.setFavorito(listaResidencias.get(position).getFavorito());
		ambientes.setStatusDispositivos(listaResidencias.get(position)
				.getStatusDispositivos());
		ambientes.setDescricao(listaResidencias.get(position).getDescricao());
		ambientes.setDataAlteracaoDispositivos(listaResidencias.get(position)
				.getDataAlteracaoDispositivos());
		ambientes.setDataCadastroDispositivos(listaResidencias.get(position)
				.getDataCadastroDispositivos());
		ambientes.setChaveAmbiente(listaResidencias.get(position)
				.getChaveAmbiente());
		ambientes.setChavePorta(listaResidencias.get(position).getChavePorta());

		if (convertView == null) {
			itemView = new View(context);

			itemView = inflater.inflate(R.layout.listar_cenario_adapter_layout,
					null);

			TextView textView = (TextView) itemView
					.findViewById(R.id.descricaoCenario);

			textView.setText(listaResidencias.get(position)
					.getDescricaoCenario());

			Switch a = (Switch) itemView.findViewById(R.id.switchCenario);
			
			
			final String chaveUsuario = listaResidencias.get(position)
					.getChaveUsuario();
			
			final String chaveCenario = listaResidencias.get(position)
					.getChaveCenario();

			a.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton arg0,
						boolean isChecked) {
					
					
					
					if (isChecked ) {

						
						     liga = true;
						     
						   
						    
						    chavCenario = chaveCenario;
						new StatusDispositivo().execute(chaveUsuario);

					}

					
				
					else {
						
						
						liga = false;
						 chavCenario = chaveCenario;
						new StatusDispositivo().execute(chaveUsuario);
					}

				}
			});

		} else {
			itemView = (View) convertView;
		}

		return itemView;
	}

	public class StatusDispositivo extends AsyncTask<String, Void, String> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
			// this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// this.progressDialog.show();
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

			
			
			Gson gson = new Gson();

			Type datasetListType = new TypeToken<Collection<Cenario>>() {
			}.getType();

			List<Cenario> datasets = gson.fromJson(jResult, datasetListType);

			

			for (Cenario dataset : datasets) {

				dataset.getDispositivo();
				

				ArrayList<AmbienteGson> af = new ArrayList<AmbienteGson>();

				for (Dispositivo dat : dataset.getDispositivo()) {

					dat.getAmbiente();
					dat.getPorta();

					// Log.e("aaaaaaa",dat.getAmbiente().getChave());
					//Log.e("aaaaaaateste",chavCenario);

					if (liga == true && dataset.getChave().equals(chavCenario)) {
						
						Log.e("liga","liga");
						
						
						     dat.setStatus("true");
						     
						     try {
								 favorit = dat.getFavorito();
									
								
									
								} catch (Exception e) {

									favorit = "False";
									
								}
						     
						     

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Favorito\":\"" + favorit
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

					
						//new AtivaCenario().execute(dispositivo);

					}

					if (liga == false && dataset.getChave().equals(chavCenario))  {

						Toast.makeText(context, "esse da qui false", Toast.LENGTH_LONG)
								.show();
						dat.setStatus("False");

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Favorito\":\"" + favorit
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

					
						new AtivaCenario().execute(dispositivo);

					}

				}

			}

		}

	}

	public class AtivaCenario extends AsyncTask<String, Void, String> {

		@Override
		protected void onCancelled() {
			super.onCancelled();
			// this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// this.progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String response = null;
			try {

				response = AcessoWSDL.AcenderLuz(params[0]);

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

			}

		}

	}

}
*/