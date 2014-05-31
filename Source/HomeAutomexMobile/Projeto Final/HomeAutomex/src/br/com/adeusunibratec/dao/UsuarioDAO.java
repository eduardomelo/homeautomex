package br.com.adeusunibratec.dao;

import br.com.adeusunibratec.bean.Usuario;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

	private SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static final String SCRIPT_DB_DELETE = "DROP TABLE IF EXISTS usuario";
	private static final String SCRIPT_DB_CREATE = "CREATE TABLE usuario" +
			 "(id integer primary key autoincrement," +
			 "login VARCHAR(200)," +
			 "senha VARCHAR(50)" +
			 ")";

	public UsuarioDAO(Context context) {
		dbHelper = new SQLiteHelper(context, "homeautomexbd", 1, SCRIPT_DB_CREATE,
				SCRIPT_DB_DELETE);
	}
	
	public long inserir(Usuario usuario){
		ContentValues cv = new ContentValues();  
		
		cv.put("login",usuario.getLogin());
		cv.put("senha",usuario.getSenha());
		
		db = dbHelper.getWritableDatabase();
		long id = db.insert("usuario", null, cv);
		db.close();
		
		return id;
	}
	
	public int excluir(){  
	    db = dbHelper.getWritableDatabase();  
	    int rows = db.delete("usuario", null, null);  
	    return rows; // qtde. de linhas afetadas  
	  }  
	
	
	public Usuario buscarCarroPorNome(){  
		Usuario out = new Usuario();  
	  
	    String[] columns = new String[]{  
	       "id", "login", "senha"};  
	    
	  
	    db = dbHelper.getWritableDatabase();  
	    Cursor c = db.query("usuario", columns,   
	       null, null, null, null, null);  
	  
	    c.moveToFirst();  
	    while(!c.isAfterLast()){  
//	      Usuario usuario = fillCarro(c);
	    	if(c != null){
	    		out.setIdusuario(c.getInt(0));
	    		out.setLogin(c.getString(1)); 
	    		out.setSenha(c.getString(2));
	    	}
	      c.moveToNext();  
	    }  
	    c.close();  
	    db.close();  
	    return out;  
	  }  

}
