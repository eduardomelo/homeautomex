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
	private static String url = "http://172.16.3.72/meuWebservice/HomeAutomexWS.asmx";

	private static String soap_action_consutar_todos_usuarios = "http://tempuri.org/ConsutarTodosUsuarios";
	private static String method_consutar_todos_usuarios = "ConsutarTodosUsuarios";

	// minhas variaveis depois mudo
	public final static String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
	/* public final static String SOAP_ADDRESS =
	 "http://192.168.0.102/vai/HomeAutomexWS.asmx";*/

	public final static String SOAP_ADDRESS = "http://10.0.2.2/vai/HomeAutomexWS.asmx";

	public final static String SOAP_LOGIN_ACTION = "http://tempuri.org/Logar";
	public final static String OPERATION_LOGIN = "Logar";
	public final static String J_LOGIN = "jUsuario";

	public final static String SOAP_BUSCAR_RESIDENCIA = "http://tempuri.org/BuscarUsuarioPorChave";
	public final static String OPERATION_BUSCARRESIDENCIA = "BuscarUsuarioPorChave";
	public final static String J_CHAVE = "jChave";

	public final static String SOAP_BUSCAR_AMBIENTES = "http://tempuri.org/BuscarAmbientePorChave";
	public final static String OPERATION_BUSCARAMBIENTES = "BuscarAmbientePorChave";

	public final static String SOAP_BUSCAR_AMBIENTE = "http://tempuri.org/ConsutarTodosDispositivoPorUsuarioChave";
	public final static String OPERATION_BUSCARAMBIENTE = "ConsutarTodosDispositivoPorUsuarioChave";

	public final static String SOAP_ACENDER_LUZ = "http://tempuri.org/AlterarDispositivo";
	public final static String OPERATION_ACENDER_LUZ = "AlterarDispositivo";
	public final static String JDispositivo = "jDispositivo";

	public final static String SOAP_BUSCAR_CENARIO = "http://tempuri.org/ConsutarTodosCenarioPorUsuarioChave";
	public final static String OPERATION_BUSCARCENARIO = "ConsutarTodosCenarioPorUsuarioChave";

	public final static String SOAP_BUSCAR_AGENDAMENTO = "http://tempuri.org/ConsutarTodosAgendamentoPorUsuarioChave";
	public final static String OPERATION_BUSCARAGENDAMENTO = "ConsutarTodosAgendamentoPorUsuarioChave";

	public final static String SOAP_CADASTRO_CENARIO = "http://tempuri.org/InserirCenario";
	public final static String OPERATION_CADASTRO_CENARIO = "InserirCenario";
	public final static String CENARIO = "jCenario";
	
	public final static String SOAP_CADASTRO_AGENDAMENTO = "http://tempuri.org/InserirAgendamento";
	public final static String OPERATION_CADASTRO_AGENDAMENTO = "InserirAgendamento";
	public final static String AGENDAMENTO = "jAgendamento";
	
	public final static String SOAP_CADASTROCENARIODISPOSITIVO = "http://tempuri.org/CriarCenarioDispositivo";
	public final static String OPERATION_CENARIODISPOSITIVO = "CriarCenarioDispositivo";
	//public final static String J_LOGIN = "jUsuario";
	

	public static String loginWS(String login, String senha)
			throws JSONException, ClientProtocolException,
			UnsupportedEncodingException, IOException, XmlPullParserException,
			UnknownHostException {

		String jResult = null;

		JSONObject jsonLogin = JSONParserManager.createJSONLogin(login, senha);

		jResult = sendWebServiceContent(jsonLogin.toString(), OPERATION_LOGIN,
				J_LOGIN, SOAP_LOGIN_ACTION);

		return jResult;
	}
	
	
	
	public static String cadastroDispositivoCenario(String json, String status)
			throws JSONException, ClientProtocolException,
			UnsupportedEncodingException, IOException, XmlPullParserException,
			UnknownHostException {

		String jResult = null;

		//JSONObject jsonLogin = JSONParserManager.createJSONLogin(login, senha);
     String teste = json + "Status="+status;
     
     //https://www.youtube.com/watch?v=jXyt-BwOdcE
		
		jResult = sendWebServiceContent(json, OPERATION_CENARIODISPOSITIVO,
				CENARIO, SOAP_CADASTROCENARIODISPOSITIVO);

		return jResult;
	}
	
	

	public static String buscarResidencia(String chave) throws JSONException,
			ClientProtocolException, UnsupportedEncodingException,
			UnknownHostException, IOException, XmlPullParserException {
		String result = null;

		Log.e("metodo buscar residencia", chave);

		// JSONObject jsonResidencia =
		// JSONParserManager.createJSONBuscarResidencias(chave,senha);

		result = sendWebServiceContent(chave, OPERATION_BUSCARRESIDENCIA,
				J_CHAVE, SOAP_BUSCAR_RESIDENCIA);

		return result;
	}

	public static String buscarAmbientes(String chave) throws JSONException,
			ClientProtocolException, UnsupportedEncodingException,
			UnknownHostException, IOException, XmlPullParserException {
		String result = null;

		Log.e("metodo buscar residencia", chave);

		// JSONObject jsonResidencia =
		// JSONParserManager.createJSONBuscarResidencias(chave,senha);

		result = sendWebServiceContent(chave, OPERATION_BUSCARAMBIENTE,
				J_CHAVE, SOAP_BUSCAR_AMBIENTE);

		return result;
	}

	public static String CadastroCenario(String chave, String usuario)
			throws JSONException, ClientProtocolException,
			UnsupportedEncodingException, UnknownHostException, IOException,
			XmlPullParserException {

		String result = null;

		Log.e("metodo buscar residencia", chave);

		// JSONObject jsonCadastroCenario =
		// JSONParserManager.JsoncreateCrearCenario(chave);

		// Log.e("chegou aqui ", ""+jsonCadastroCenario);

		String cadastroCenario = "{\"Descricao\":\"" + chave
				+ "\",\"Desativado\":true,\"Usuario\":" + usuario
				+ ",\"DataCadastro\":\"\"}";

		Log.e("chegou aqui ", "" + cadastroCenario);

		result = sendWebServiceContent(cadastroCenario.toString(),
				OPERATION_CADASTRO_CENARIO, CENARIO, SOAP_CADASTRO_CENARIO);

		return result;
	}
	
	
	
	public static String CadastroAgendamento(String chave, String usuario,String data,String hora)
			throws JSONException, ClientProtocolException,
			UnsupportedEncodingException, UnknownHostException, IOException,
			XmlPullParserException {

		String result = null;

		Log.e("metodo buscar residencia", chave);

		// JSONObject jsonCadastroCenario =
		// JSONParserManager.JsoncreateCrearCenario(chave);

		// Log.e("chegou aqui ", ""+jsonCadastroCenario);

		String cadastroAgendamento = "{\"Descricao\":\"" + chave
				+ "\",\"Ativo\":true,\"DataAgendamento\":" + data+hora
				+ ",\"Usuario\":"+usuario+",\"DataCadastro\":\"\"}";

		Log.e("chegou aquina descricao agendamento ", "olha" + cadastroAgendamento);

		result = sendWebServiceContent(cadastroAgendamento.toString(),
				OPERATION_CADASTRO_AGENDAMENTO, AGENDAMENTO, SOAP_CADASTRO_AGENDAMENTO);
		
		

		return result;
	}
	
	

	public static String buscarCenarios(String chave) throws JSONException,
			ClientProtocolException, UnsupportedEncodingException,
			UnknownHostException, IOException, XmlPullParserException {
		String result = null;

		Log.e("metodo buscar cenario", chave);

		// JSONObject jsonResidencia =
		// JSONParserManager.createJSONBuscarResidencias(chave,senha);

		result = sendWebServiceContent(chave, OPERATION_BUSCARCENARIO, J_CHAVE,
				SOAP_BUSCAR_CENARIO);

		return result;
	}

	public static String buscarAgendamento(String chave) throws JSONException,
			ClientProtocolException, UnsupportedEncodingException,
			UnknownHostException, IOException, XmlPullParserException {
		String result = null;

		Log.e("metodo buscar cenario", chave);

		// JSONObject jsonResidencia =
		// JSONParserManager.createJSONBuscarResidencias(chave,senha);

		result = sendWebServiceContent(chave, OPERATION_BUSCARAGENDAMENTO,
				J_CHAVE, SOAP_BUSCAR_AGENDAMENTO);

		return result;
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

	public static String AcenderLuz(String chave) throws JSONException,
			ClientProtocolException, UnsupportedEncodingException,
			UnknownHostException, IOException, XmlPullParserException {
		String result = null;

		// Log.e("Acender Luz", chave =
		// "Chave:1,Descricao:lampada,DataCadastro:2014-04-16T15:42:13.397");

		/*
		 * JSONObject jsonResidencia =
		 * JSONParserManager.createJSONBuscarResidencias(chave);
		 */
		// chave = "";

		result = sendWebServiceContent(chave, OPERATION_ACENDER_LUZ,
				JDispositivo, SOAP_ACENDER_LUZ);

		Log.e("teste Luzzzzzzzzzz", result);

		return result;
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
