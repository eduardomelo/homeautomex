package br.com.adeusunibratec.dao;

import br.com.adeusunibratec.bean.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static final String SCRIPT_DB_DELETE = "DROP TABLE IF EXISTS usuario";
	private static final String SCRIPT_DB_CREATE = "CREATE TABLE usuario" +
			 "(id integer primary key autoincrement," +
			 "NOME VARCHAR(200) not null, " +
			 "CELULAR VARCHAR(10)," +
			 "LOGIN VARCHAR(200)," +
			 "SENHA VARCHAR(50)," +
			 "DESTIVADO BOOLEAN," +
			 "TELEFONE VARCHAR(50)," +
			 "DATA_EXCLUSAO VARCHAR(50)," +
			 "DATA_ALTERACAO VARCHAR(50)," +
			 "DATA_CADASTRO VARCHAR(50)," +
			 "EMAIL VARCHAR(50))";

	public UsuarioDAO(Context context) {
		dbHelper = new SQLiteHelper(context, "curso", 1, SCRIPT_DB_CREATE,
				SCRIPT_DB_DELETE);
	}
	
	private long inserir(Usuario usuario){
		ContentValues cv = new ContentValues();  
		cv.put("NOME",usuario.getNome());
		cv.put("CELULAR",usuario.getCelular());
		cv.put("LOGIN",usuario.getLogin());
		cv.put("SENHA",usuario.getSenha());
		cv.put("DESTIVADO",usuario.getDesativado());
		cv.put("TELEFONE",usuario.getTelefone());
		cv.put("DATA_EXCLUSAO",usuario.getDataExclusao().toString());
		cv.put("DATA_ALTERACAO",usuario.getDataAlteracao().toString());
		cv.put("DATA_CADASTRO",usuario.getDataCadastro().toString());
		cv.put("EMAIL",usuario.getEmail());
//		cv.put("CHAVE",usuario.getChave());
		
		db = dbHelper.getWritableDatabase();
		long id = db.insert("usuario", null, cv);
		db.close();
		
		return id;
	}

}
