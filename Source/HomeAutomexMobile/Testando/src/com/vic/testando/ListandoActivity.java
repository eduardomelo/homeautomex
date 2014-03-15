package com.vic.testando;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;
import android.widget.Toast;

public class ListandoActivity extends Activity {

	
	GridView gvdados;
	ProgressDialog dialog;
	String res;
	
	
	private static String NAME_SPACE = "http://tempuri.org/";
	//private static String URL = "http://10.0.2.2/meuWebservice/WebService1.asmx";
	private static String URL = "http://10.1.2.34:9090/WS/HomeAutomexWS.asmx";
		
	private static String METHOD_NAME = "ConsutarTodosUsuarios";
	//private static String SOAP_ACTION = "http://testandoservico/HelloWorld";
	private static String SOAP_ACTION = "http://tempuri.org/ConsutarTodosUsuarios";
	
	/*private static String NAME_SPACE = "http://testandoservico/";
	private static String URL = "http://10.0.2.2/meuWebservice/WebService1.asmx";
	private static String METHOD_NAME = "HelloWorld";
	private static String SOAP_ACTION = "http://testandoservico/HelloWorld";
*/
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listando);

		
		new AsyncListando().execute();
		

	}

	// --------------------- conectando com WebService --------------------//
	public Boolean invocarWS() {
		Boolean bandeira = true;

		try {
			SoapObject resposta = new SoapObject(NAME_SPACE, METHOD_NAME);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			
			envelope.dotNet = true;
			
			envelope.setOutputSoapObject(resposta);
			
			HttpTransportSE transporte = new HttpTransportSE(URL);
			transporte.call(SOAP_ACTION, envelope);
			
			//SoapObject response = (SoapObject) envelope.getResponse();
			SoapPrimitive resultado_xml = (SoapPrimitive) envelope
					.getResponse();

			Log.e("valor de response", resultado_xml.toString());
			
			res = resultado_xml.toString();
			
		} catch (IOException e) {

			e.printStackTrace();
			bandeira = false;
		} catch (XmlPullParserException e) {

			e.printStackTrace();
			bandeira = false;
		}

		return bandeira;
		
	}

	// --------------------- Classe para sincronizar as tarefas ------------------//
	class AsyncListando extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
			dialog = new ProgressDialog(ListandoActivity.this);
			dialog.setMessage("Carregando dados...");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(invocarWS()){
				return "ok";
			}else{
				return "err";
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			dialog.dismiss();
			if(result.equals("ok")){
			// carregando a menssagem	
				Toast.makeText(ListandoActivity.this, res, Toast.LENGTH_LONG).show();
			}
		
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listando, menu);
		return true;
	}


}
