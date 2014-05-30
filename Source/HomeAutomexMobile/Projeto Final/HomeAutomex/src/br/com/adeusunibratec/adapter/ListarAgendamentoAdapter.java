package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.ListarCenariosAdapter.AtivaCenario;
import br.com.adeusunibratec.adapter.ListarCenariosAdapter.StatusDispositivo;
import br.com.adeusunibratec.bean.AmbienteGson;
import br.com.adeusunibratec.bean.Cenario;
import br.com.adeusunibratec.bean.Dispositivo;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.mb.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ListarAgendamentoAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<DispositivoGson> listaResidencias;

	private String chaveCenario;

	private AmbientesAdapter ambi;

	boolean liga = false;

	public ListarAgendamentoAdapter(Context context,
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

			itemView = inflater.inflate(R.layout.listar_agendamento_adapter_layout,
					null);

			TextView textView = (TextView) itemView
					.findViewById(R.id.descricaoCenario);

			textView.setText(listaResidencias.get(position)
					.getDescricaoCenario());

			Switch a = (Switch) itemView.findViewById(R.id.switchCenario);
			final String chaveUsuario = listaResidencias.get(position)
					.getChaveUsuario();

			a.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton arg0,
						boolean isChecked) {
					
					Toast.makeText(context, "clicado"+isChecked, Toast.LENGTH_LONG);
					
					if (isChecked ) {

						
						     liga = true;
						new StatusDispositivo().execute(chaveUsuario);

					}

					
				
					else {
						
						
						liga = false;
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

					

					if (liga == true) {
						
						dat.setStatus("true");

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

						/*Toast.makeText(context, "true", Toast.LENGTH_LONG)
								.show();
*/
						new AtivaCenario().execute(dispositivo);

					}

					else {

						/*Toast.makeText(context, "esse da qui false", Toast.LENGTH_LONG)
								.show();*/
						dat.setStatus("False");

						String dispositivo = "{\"Chave\":" + dat.getChave()
								+ ",\"Status\":\"" + dat.getStatus()
								+ "\",\"Descricao\":\"" + dat.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ dat.getDataCadastro()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ dat.getAmbiente().getChave()
								+ "},\"Porta\":{\"Chave\":"
								+ dat.getPorta().getChave() + "}}";

						//Log.e("dispositivo", dispositivo);
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
