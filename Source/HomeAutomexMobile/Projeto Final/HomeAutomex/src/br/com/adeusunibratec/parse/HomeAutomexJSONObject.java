package br.com.homeautomex.parse;

import java.util.ArrayList;

import br.com.homeautomex.bean.Usuario;



public class HomeAutomexJSONObject {

	private static HomeAutomexJSONObject instance;

	private Usuario usuario;

	private ArrayList<Usuario> usuarios;

	public static HomeAutomexJSONObject getInstance() {
		if (instance == null) {
			instance = new HomeAutomexJSONObject();
		}
		return instance;
	}

	public void logoutUsuario() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
