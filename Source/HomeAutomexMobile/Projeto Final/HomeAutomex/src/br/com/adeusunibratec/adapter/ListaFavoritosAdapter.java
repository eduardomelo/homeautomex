package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.AmbientesAdapter.LigarTask;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.R;

public class ListaFavoritosAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<DispositivoGson> listaResidencias;

	public ListaFavoritosAdapter(Context context,
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
		
		final Ambiente ambientes = new Ambiente();
		ambientes.setChaveDispositivos(listaResidencias.get(position).getChaveDispositivos());
		ambientes.setFavorito(listaResidencias.get(position).getFavorito());
		ambientes.setStatusDispositivos(listaResidencias.get(position).getStatusDispositivos());
		ambientes.setDescricao(listaResidencias.get(position).getDescricao());
		ambientes.setDataAlteracaoDispositivos(listaResidencias.get(position).getDataAlteracaoDispositivos());
		ambientes.setDataCadastroDispositivos(listaResidencias.get(position).getDataCadastroDispositivos());
		ambientes.setChaveAmbiente(listaResidencias.get(position).getChaveAmbiente());
		ambientes.setChavePorta(listaResidencias.get(position).getChavePorta());
		
		
		if (convertView == null) {
			itemView = new View(context);

			itemView = inflater.inflate(R.layout.favorito_adapter_layout, null);

			TextView textView = (TextView) itemView
					.findViewById(R.id.descricaoDispositivo);
			textView.setText(listaResidencias.get(position).getDescricao());

			Switch a = (Switch) itemView.findViewById(R.id.switchFavoritos);

			a.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton arg0,
						boolean isChecked) {
					if (isChecked) {
						
						ambientes.setStatusDispositivos("true");
						ambientes.setFavorito("true");
						String dispositivo = "{\"Chave\":"
								+ ambientes.getChaveDispositivos()
								+ ",\"Favorito\":\"" + ambientes.getFavorito()
								+"\",\"Status\":\""
								+ ambientes.getStatusDispositivos()
								+ "\",\"Descricao\":\""
								+ ambientes.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ ambientes.getDataCadastroDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ ambientes.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":"
								+ ambientes.getChavePorta() + "}}";
						Log.e("log do dispositivo true", dispositivo);
						new LigarTask().execute(dispositivo);
						
					}

					else {
						ambientes.setStatusDispositivos("false");
						String dispositivo = "{\"Chave\":"
								+ ambientes.getChaveDispositivos()
								+ ",\"Favorito\":\"" + ambientes.getFavorito()
								+ "\",\"Status\":\""
								+ ambientes.getStatusDispositivos()
								+ "\",\"Descricao\":\""
								+ ambientes.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ ambientes.getDataCadastroDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ ambientes.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":"
								+ ambientes.getChavePorta() + "}}";

						
						Log.e("log do dispositivo false", dispositivo);
						new LigarTask().execute(dispositivo);
					}

				}
			});

		} else {
			itemView = (View) convertView;
		}

		return itemView;
	}
	
	
	public class LigarTask extends AsyncTask<String, Void, String> {

		// private ProgressDialog progressDialog;

		// private int perfil;

		/*
		 * public ResidenciaTask(ProgressDialog params) {
		 * 
		 * this.progressDialog = params;
		 * this.progressDialog.setMessage("Carregando Ambientes...");
		 * 
		 * }
		 */

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
