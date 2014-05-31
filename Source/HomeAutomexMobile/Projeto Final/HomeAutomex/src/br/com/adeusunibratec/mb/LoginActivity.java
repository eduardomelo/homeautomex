package br.com.adeusunibratec.mb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.parse.JSONFields;
import br.com.adeusunibratec.util.HomeAutomexUtils;
import br.com.adeusunibratec.util.Util;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class LoginActivity extends Activity implements OnClickListener {

	
	
	//----------------notifica��es----------------------------///
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
   // please enter your sender id
    String SENDER_ID = "93702006072";
    static final String TAG = "GCMDemo";
    GoogleCloudMessaging gcm;
    TextView mDisplay;
    Context context;
    String regid;
    
  //----------------notifica��es----------------------------///
    
    private EditText loginEdit;
	private EditText senhaEdit;
	private Button logarBtn;
	private Intent intent;
	private String login;
	private String senha;

	private ProgressDialog progressDialog;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		/*mDisplay = (TextView) findViewById(R.id.display);
		context = getApplicationContext();
	
		Log.e("111", context.toString());
		
		if(checkPlayServices()){
			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationId(context);
			mDisplay.setText(regid);
			Log.e("111", mDisplay.toString());
			if(regid.isEmpty()){
				new RegisterBackground().execute();
			}
			Log.e("111", regid.toString());
		}
*/
		this.intent = getIntent();

		this.login = intent.getStringExtra("login");
		this.senha = intent.getStringExtra("senha");
	    
		
		createProgressDialog();

		this.loginEdit = (EditText) findViewById(R.id.editLogin);
		this.loginEdit.setFocusable(true);
		this.loginEdit.requestFocus();

		this.senhaEdit = (EditText) findViewById(R.id.editSenha);

		this.logarBtn = (Button) findViewById(R.id.btnLogin);
		this.logarBtn.setOnClickListener(this);
		if(this.login != null && this.senha != null){
			this.loginHomeAutomex(this.login, this.senha);
		}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	
	
	/*@Override
	protected void onResume(){
		super.onResume();
		checkPlayServices();
	}*/
	class RegisterBackground extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String msg = "";
			try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Dvice registered, registration ID=" + regid;
                         
                sendRegistrationIdToBackend();
                
                // Persist the regID - no need to register again.
               storeRegistrationId(context, regid);
            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }
		
		@Override
        protected void onPostExecute(String msg) {
                mDisplay.append(msg + "\n");
            
        }
		private void sendRegistrationIdToBackend() {
		      // Your implementation here.

				String url = "http://homeautomex.web44.net/getdevice.php";
				List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("regid", regid));
	           DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url);
	            try {
	            	
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	            try {
	            	
					HttpResponse httpResponse = httpClient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
			
			
			
		    }
		
		private void storeRegistrationId(Context context, String regId) {
		    final SharedPreferences prefs = getGCMPreferences(context);
		    int appVersion = getAppVersion(context);
		    Log.i(TAG, "Saving regId on app version " + appVersion);
		    SharedPreferences.Editor editor = prefs.edit();
		    editor.putString(PROPERTY_REG_ID, regId);
		    editor.putInt(PROPERTY_APP_VERSION, appVersion);
		    editor.commit();
		}
		}
		
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG, "This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	@SuppressLint("NewApi")
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	
	private SharedPreferences getGCMPreferences(Context context) {
	    
	    return getSharedPreferences(LoginActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}

	
	
	
	private void loginHomeAutomex(String login, String senha) {

		if (!"".equals(login)) {
			if (!"".equals(senha)) {
				  if (Util.verificaConexao(this)) {

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
	public void onClick(View arg0) {

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
					//e.printStackTrace();
					//Toast.makeText(getApplication(), "LOGIN INVALIDO TENTE NOVAMENTE", Toast.LENGTH_LONG).show();
				}
			} else {
				this.progressDialog.dismiss();
				Toast.makeText(getApplicationContext(),
						"Ocorreu um erro ao realizar o login.",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	
	
	/*private EditText loginEdit;
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
*/}
