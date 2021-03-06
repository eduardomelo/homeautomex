package br.com.homeautomex.parse;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;
import br.com.homeautomex.bean.Residencia;
import br.com.homeautomex.bean.Usuario;

@SuppressLint("NewApi")
public class JSONParserManager {

	@SuppressLint("NewApi")
	public static Usuario parseJSONSapiensFirstAccess(String jsonString)
			throws JSONException {

		Log.e("segundo", jsonString);

		if (jsonString == null || jsonString.isEmpty()) {
			throw new ParserException(ParserException.Error.INTERNAL_ERROR);
		}

		try {
			jsonString = new String(jsonString.getBytes(Charset
					.defaultCharset()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		JSONObject pingJSON = new JSONObject(jsonString);

		Usuario usuario = new Usuario();

		usuario.setNome(pingJSON.getString(JSONFields.NOME));
		usuario.setLogin(pingJSON.getString(JSONFields.LOGIN));
		usuario.setSenha(pingJSON.getString(JSONFields.SENHA));
		usuario.setTelefone(pingJSON.getString(JSONFields.TELEFONE));
		usuario.setCelular(pingJSON.getString(JSONFields.CELULAR));
		usuario.setEmail(pingJSON.getString(JSONFields.EMAIL));
		// usuario.setChave((char) pingJSON.getLong(JSONFields.CHAVE));
		usuario.setChave(pingJSON.getString(JSONFields.CHAVE));
		// Log.e("testando", usuario.getChave());

		HomeAutomexJSONObject.getInstance().setUsuario(usuario);
		return usuario;
	}

	public static JSONObject createJSONLogin(String login, String senha)
			throws JSONException {

		JSONObject jLogin = new JSONObject();

		if (!"".equals(login)) {

			jLogin.put(JSONFields.LOGIN, login);

		}

		if (!"".equals(senha)) {
			jLogin.put(JSONFields.SENHA, senha);
		}

		return jLogin;
	}

	public static ArrayList<Usuario> parserArrayUsuario(JSONArray jsonArray)
			throws JSONException {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario usuario = null;
		Log.e("parse array usuario ", jsonArray.toString());

		for (int i = 0; i < jsonArray.length(); i++) {

			// JSONObject jsonObject = jsonArray.getJSONObject(i);
			usuario = new Usuario();

			/*
			 * usuario.setNome((String) jsonObject.getString("Nome"));
			 * usuario.setTelefone((String) jsonObject.getString("Telefone"));
			 * usuario.setCelular((String) jsonObject.getString("Celular"));
			 * usuario.setEmail((String) jsonObject.getString("Email"));
			 * usuario.setLogin((String) jsonObject.getString("Login"));
			 * usuario.setSenha((String) jsonObject.getString("Senha"));
			 */

			usuario.setIdusuario(jsonArray.getJSONObject(i).getInt(
					JSONFields.ID_USUARIO));

			usuario.setNome(jsonArray.getJSONObject(i).getString(
					JSONFields.NOME));

			usuario.setSenha(jsonArray.getJSONObject(i).getString(
					JSONFields.LOGIN));

			usuario.setTelefone(jsonArray.getJSONObject(i).getString(
					JSONFields.TELEFONE));
			usuario.setCelular(jsonArray.getJSONObject(i).getString(
					JSONFields.CELULAR));
			usuario.setEmail(jsonArray.getJSONObject(i).getString(
					JSONFields.EMAIL));

			usuarios.add(usuario);
		}

		Log.e("lista de usuarios ", usuarios.toString());
		return usuarios;
	}

	public static ArrayList<Residencia> parserArrayResidencias(
			JSONObject jsonObject) throws JSONException {

		ArrayList<Residencia> residencias = new ArrayList<Residencia>();
		Residencia residencia = null;

		JSONArray teste = jsonObject.getJSONArray("Residencias");

		
		for (int i = 0; i < teste.length(); i++) {
			residencia = new Residencia();
			teste.getJSONObject(i).getString(JSONFields.CIDADE);
			Log.e("olha aiiiiiiiii",
					teste.getJSONObject(i).getString(JSONFields.CHAVE));

			residencia.setLogradouro(teste.getJSONObject(i).getString(
					JSONFields.LOGRADOURO));
			residencia.setCidade(teste.getJSONObject(i).getString(
					JSONFields.CIDADE));
			residencia.setBairro(teste.getJSONObject(i).getString(
					JSONFields.BAIRRO));
			residencia.setCep(teste.getJSONObject(i).getString(JSONFields.CEP));
			residencia.setNumero(teste.getJSONObject(i).getString(
					JSONFields.NUMERO));
			residencia.setComplemento(teste.getJSONObject(i).getString(
					JSONFields.COMPLEMENTO));
			residencia.setChave(teste.getJSONObject(i).getString(
					JSONFields.CHAVE));

			residencias.add(residencia);

		}

		return residencias;
	}

	public static JSONObject createJSONBuscarResidencias(String chave) throws JSONException {

		JSONObject jsonBuscarResidencia = new JSONObject();
		jsonBuscarResidencia.put(JSONFields.LOGIN, chave);
		
		return jsonBuscarResidencia;
	}
	
	public static JSONObject JsoncreateCrearCenario(String chave) throws JSONException {

		JSONObject jsonBuscarResidencia = new JSONObject();
		jsonBuscarResidencia.put(JSONFields.DESCRICAO, chave);
		
		return jsonBuscarResidencia;
	}
	
}
