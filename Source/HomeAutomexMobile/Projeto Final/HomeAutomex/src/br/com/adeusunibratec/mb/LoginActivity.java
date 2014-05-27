package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;



import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;


import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import br.com.adeusunibratec.parse.JSONFields;
import br.com.adeusunibratec.util.HomeAutomexUtils;

import android.media.MediaPlayer;
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
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText loginEdit;
	private EditText senhaEdit;
	private Button logarBtn;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		createProgressDialog();

		this.loginEdit = (EditText) findViewById(R.id.editLogin);
		this.loginEdit.setFocusable(true);
		this.loginEdit.requestFocus();

		this.senhaEdit = (EditText) findViewById(R.id.editSenha);

		this.logarBtn = (Button) findViewById(R.id.btnLogin);
		this.logarBtn.setOnClickListener(this);

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

	private void loginHomeAutomex(String login, String senha) {

		if (!"".equals(login)) {
			if (!"".equals(senha)) {
				  if (HomeAutomexUtils
						.internetConnection(getApplicationContext())) {

					new LoginTask(this.progressDialog).execute(login, senha);

				} else {
					Toast.makeText(getApplicationContext(),
							"Verifique sua conexão com a internet.",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(getApplicationContext(), "Informe a senha.",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "Informe o login.",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View v) {
		
			this.loginHomeAutomex(loginEdit.getText().toString(), senhaEdit
					.getText().toString());
			
	}

	
	private void showHomeActivity(String jResult) {
		Intent intent = new Intent(LoginActivity.this, ResidenciasActivity.class);
		intent.putExtra("jLoginResult", jResult);
		this.startActivity(intent);
	}

	public class LoginTask extends AsyncTask<String, Void, String> {

		private ProgressDialog progressDialog;
		private int perfil;

		public LoginTask(ProgressDialog params) {

			this.progressDialog = params;
			this.progressDialog.setMessage("Efetuando o login.");

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
				
				

				response = AcessoWSDL.loginWS(params[0], params[1]);

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
			
			
			
			String a = jResult.toString();
			
			if(jResult.equals("null")){
				this.progressDialog.dismiss();
				Toast.makeText(getApplication(), "LOGIN INVALIDO TENTE NOVAMENTE", Toast.LENGTH_LONG).show();	
				EditText loginEdit = (EditText) findViewById(R.id.editLogin);
				loginEdit.setFocusable(true);
				loginEdit.requestFocus();
				loginEdit.setText("");
					
				EditText senhaEdit = (EditText) findViewById(R.id.editSenha);
				
				senhaEdit.setText("");
			}
			
		
			
			if (jResult != null) {
				try {
					JSONObject checkErro = new JSONObject(jResult);
					if (checkErro.has(JSONFields.ERRO)) {
						this.progressDialog.dismiss();
						Toast.makeText(getApplicationContext(),
								checkErro.getString(JSONFields.ERRO),
								Toast.LENGTH_SHORT).show();
					} else {
						this.progressDialog.dismiss();
						showHomeActivity(jResult);
						LoginActivity.this.finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				this.progressDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Ocorreu um erro ao realizar o login.",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
