package br.com.adeusunibratec.parse;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.adeusunibratec.bean.Usuario;





//@SuppressLint("NewApi")
public class JSONParserManager {

	//@SuppressLint("NewApi")
	public static void parseJSONSapiensFirstAccess(String jsonString)
			throws JSONException {

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
		//usuario.setDataCadastro(pingJSON.getString(JSONFields.DATA_CADASTRO));
		//usuario.setDataAlteracao(pingJSON.getString(JSONFields.DATA_ALTERACAO));
		//usuario.setDataAlteracao(pingJSON.getString(JSONFields.DATA_EXCLUSAO));

		HomeAutomexJSONObject.getInstance().setUsuario(usuario);
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

}
