package br.com.adeusunibratec.acesso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.bean.Usuario;

import android.util.Log;

public class AcessoWSDL {

	private static String name_space = "http://tempuri.org/";
	private static String url = "http://172.16.3.39:9090/WS/HomeAutomexWS.asmx";
	
	private static String soap_action_consutar_todos_usuarios = "http://tempuri.org/ConsutarTodosUsuarios";
	private static String method_consutar_todos_usuarios = "ConsutarTodosUsuarios";

	public AcessoWSDL() {
	}

	// sadsd
	/**
	 * Metodo generico para chamar WS. Obs:o atributo SOAP_ACTION concatenará
	 * com o METHOD_NAME.
	 * 
	 * @param url
	 * @param name_space
	 * @param method_name
	 * @param soap_action
	 * @return
	 */
	public String chamandoWS(String url, String name_space, String method_name,
			String soap_action, String inValor, String inParametro) {
		// Boolean valido = true;

		String resultado = null;
		try {
			SoapObject resposta = new SoapObject(name_space, method_name);

//			if ((inValor != null && inParametro != null) || (!inValor.isEmpty() && !inParametro.isEmpty())) {
//				resposta.addProperty(inParametro, inValor);
//			}

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

			resultado = resultado_xml.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Usuario> consultarTodosUsuarios(String in) {
		List<Usuario> resultado = null;
		String inValor = null;
		String inParametro = null;
		
		resultado = new ArrayList<Usuario>();

		String cWS = this.chamandoWS(url, name_space, method_consutar_todos_usuarios,
										soap_action_consutar_todos_usuarios, inValor, inParametro);

		try {
			JSONArray jsonArray = new JSONArray(cWS.toString());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Usuario usuario = new Usuario();
				usuario.setNome((String) jsonObject.getString("Nome"));
				usuario.setTelefone((String) jsonObject.getString("Telefone"));
				usuario.setCelular((String) jsonObject.getString("Celular"));
				usuario.setEmail((String) jsonObject.getString("Email"));
				usuario.setLogin((String) jsonObject.getString("Login"));
				usuario.setSenha((String) jsonObject.getString("Senha"));
				
				resultado.add(usuario);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
