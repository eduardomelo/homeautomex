package br.com.adeusunibratec.acesso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;



import br.com.adeusunibratec.bean.Usuario;
import br.com.adeusunibratec.parse.JSONParserManager;

import android.util.Log;

public class AcessoWSDL {
	private static String name_space = "http://tempuri.org/";
	private static String url = "http://192.168.0.100/meuWebservice/HomeAutomexWS.asmx";

	private static String soap_action_consutar_todos_usuarios = "http://tempuri.org/ConsutarTodosUsuarios";
	private static String method_consutar_todos_usuarios = "ConsutarTodosUsuarios";
	
	
	//minhas variaveis depois mudo 
	public final static String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public final static String SOAP_ADDRESS = "http://172.16.2.190/meuWebservice/HomeAutomexWS.asmx";
	public final static String SOAP_LOGIN_ACTION = "http://tempuri.org/Logar";
	public final static String OPERATION_LOGIN = "Logar";
	public final static String J_LOGIN = "jUsuario";
	
	
	public static String loginWS(String login, String senha) throws JSONException, ClientProtocolException,
			UnsupportedEncodingException, IOException, XmlPullParserException,
			UnknownHostException {

		String jResult = null;

		JSONObject jsonLogin = JSONParserManager.createJSONLogin(login, senha);

		jResult = sendWebServiceContent(jsonLogin.toString(), OPERATION_LOGIN,
				J_LOGIN, SOAP_LOGIN_ACTION);

		return jResult;
	}

	

	

	private static String sendWebServiceContent(String jString,
			String operation, String chave, String soapAction)
			throws IOException, ClientProtocolException,
			UnsupportedEncodingException, XmlPullParserException,
			UnknownHostException {

		String retorno = null;

		SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, operation);

		if (!chave.equals("")) {
			
			PropertyInfo pi = new PropertyInfo();

			pi.setName(chave);
			pi.setValue(jString);
			pi.setType(String.class);

			request.addProperty(pi);
			Log.e("teste", request.toString());
		}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);

		HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
		Object response = null;

		httpTransport.call(soapAction, envelope);
		response = envelope.getResponse();

		retorno = response.toString();

		return retorno;
	}

	

	public AcessoWSDL() {
	}

	
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

			// if ((inValor != null && inParametro != null) ||
			// (!inValor.isEmpty() && !inParametro.isEmpty())) {
			// resposta.addProperty(inParametro, inValor);
			// }

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

		String cWS = this.chamandoWS(url, name_space,
				method_consutar_todos_usuarios,
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
