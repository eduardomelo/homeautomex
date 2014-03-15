package br.com.adeusunibratec.acesso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

public class AcessoWSDL {

	public AcessoWSDL() {
	}

	/**
	 * Metodo generico para chamar WS.
	 * Obs:o atributo SOAP_ACTION concatenará com o METHOD_NAME.
	 * @param url
	 * @param name_space
	 * @param method_name
	 * @param soap_action
	 * @return
	 */
	public List<Object> chamandoWS(String url, String name_space,
			String method_name, String soap_action) {
//		Boolean valido = true;
		
		List<Object> resultado = new ArrayList<Object>();
		try {
			SoapObject resposta = new SoapObject(name_space, method_name);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			
			envelope.dotNet = true;
			envelope.setOutputSoapObject(resposta);

			HttpTransportSE transporte = new HttpTransportSE(url);
			transporte.call(soap_action, envelope);

			// SoapObject response = (SoapObject) envelope.getResponse();
			SoapPrimitive resultado_xml = (SoapPrimitive) envelope
					.getResponse();

			Log.e("valor de response", resultado_xml.toString());
			JSONObject jsonObject = null;
			
			try {
				jsonObject = new JSONObject(resultado_xml.toString());
				
				String nome = (String)jsonObject.getString("Nome");
				String login = (String) jsonObject.getString("Login");
				String senha = (String) jsonObject.getString("Senha");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resultado.add(resultado_xml.toString());
//			String res = resultado_xml.toString();

		} catch (IOException e) {

			e.printStackTrace();
//			valido = false;
		} catch (XmlPullParserException e) {

			e.printStackTrace();
//			valido = false;
		}
		return resultado;
	}

	public String consultarTodosUsuarios() {

		return null;
	}
}
