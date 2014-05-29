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
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.bean.DispositivoGson;
import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.ListarAgendamentoActivity;
import br.com.adeusunibratec.ha.ListarCenariosActivity;

import br.com.adeusunibratec.mb.ListarFavoritos.ResidenciaTask;
import br.com.adeusunibratec.parse.HomeAutomexJSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class OpcoesUsuariosActivity extends Activity implements OnClickListener {

	private Intent intent;
	private String teste;
	private Button btnFavoritos, btnConfiguracoes, btnCenario, btnAmbientes,
			btnProgramacao, btnListarCenario;

	private ProgressDialog progressDialog;

	private ListaFavoritosAdapter listaFavoritosAdapter;

	private ListView listaResidencias;
	private String favorito;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes_usuarios);

		btnFavoritos = (Button) findViewById(R.id.btnFavorito);
		btnFavoritos.setOnClickListener(this);

		btnConfiguracoes = (Button) findViewById(R.id.btnConfiguracao);
		btnConfiguracoes.setOnClickListener(this);

		btnCenario = (Button) findViewById(R.id.btnCenario);
		btnCenario.setOnClickListener(this);

		btnAmbientes = (Button) findViewById(R.id.btnAmbiente);
		btnAmbientes.setOnClickListener(this);

		btnProgramacao = (Button) findViewById(R.id.btnAgendamento);
		btnProgramacao.setOnClickListener(this);

		intent = getIntent();
		teste = intent.getStringExtra("idResidencia");

		//setupViews();

	}

	private void setupViews() {

		// this.listaResidencias = (ListView)
		// findViewById(R.id.listViewFavoritos);

		/*
		 * this.listaResidencias.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) {
		 * 
		 * 
		 * 
		 * Residencia residencia = (Residencia) listaResidencias
		 * .getItemAtPosition(position);
		 * 
		 * 
		 * 
		 * senddispositivosToActivity(residencia); } });
		 */

		String chave = null;

		chave = HomeAutomexJSONObject.getInstance().getUsuario().getChave();

		//Toast.makeText(getApplication(), "", duration)
		// lembrando que é para colocar a string chave dentro do parametro o "2"
		// é só para testar o metodo
		//new ResidenciaTask(this.progressDialog).execute(chave);

	}

	public class ResidenciaTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;

		public ResidenciaTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Listando Favoritos...");

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

				listaFavoritosAdapter = new ListaFavoritosAdapter(
						OpcoesUsuariosActivity.this,
						OpcoesUsuariosActivity.this.criarListaFavoritos(
								jResult, teste));

				listaResidencias.setAdapter(listaFavoritosAdapter);

			} else {
				this.progressDialog.dismiss();
				// mensagem de erro
			}
		}
	}

	public ArrayList<DispositivoGson> criarListaFavoritos(String json,
			String Chave) {

		JSONArray jsonArray = null;

		ArrayList<Ambiente> ambientes = new ArrayList<Ambiente>();

		ArrayList<DispositivoGson> dispositivos = new ArrayList<DispositivoGson>();
		ArrayList<Ambiente> hg = new ArrayList<Ambiente>();
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

					if (dispositivo.getFavorito().equals("true")) {

						favorito = "true";

						dispositivos.add(dispositivo);

					}
				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (favorito.equals("true")) {

			/*Toast.makeText(
					getApplication(),
					"VOCÊ NÃO TEM DISPOSITOS\nFAVORITOS\nCADASTRE PARA FACILITAR SUA NAVEGAÇÃO",
					Toast.LENGTH_LONG).show();*/

			Intent intent = new Intent(OpcoesUsuariosActivity.this,
					ListarFavoritos.class);

			intent.putExtra("idResidencia", teste);

			startActivity(intent);

		}
		return dispositivos;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opcoes_usuarios, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btnFavorito:

			Intent itFavoritos = new Intent(this, ListarFavoritos.class);
			itFavoritos.putExtra("idResidencia", teste);
			startActivity(itFavoritos);

			break;

		case R.id.btnCenario:

			Intent itCenarios = new Intent(this, ListarCenariosActivity.class);
			itCenarios.putExtra("idResidencia", teste);
			startActivity(itCenarios);

			break;

		case R.id.btnAmbiente:

			Intent itAmbiente = new Intent(this, AmbienteActivity.class);
			itAmbiente.putExtra("idResidencia", teste);
			startActivity(itAmbiente);

			break;

		case R.id.btnConfiguracao:

			Intent itConfiguracao = new Intent(this,
					ConfiguracoesActivity.class);
			itConfiguracao.putExtra("idResidencia", teste);
			startActivity(itConfiguracao);

			break;

		case R.id.btnAgendamento:

			Intent itAgendamento = new Intent(this,
					ListarAgendamentoActivity.class);
			itAgendamento.putExtra("idResidencia", teste);
			startActivity(itAgendamento);

			break;

		default:
			break;
		}
	}

}
