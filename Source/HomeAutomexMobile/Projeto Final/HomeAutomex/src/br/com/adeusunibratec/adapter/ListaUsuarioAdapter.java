package br.com.homeautomex.adapter;
/*package br.com.adeusunibratec.adapter;

import java.util.ArrayList;

import br.com.adeusunibratec.bean.Usuario;

import br.com.adeusunibratec.ha.R;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ListaUsuarioAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Usuario> listausuarios;

	public ListaUsuarioAdapter(Context context, ArrayList<Usuario> usuarios) {
		
		Log.e("entrou aqui", context.toString() +"usuarios"+usuarios);
		this.context = context;
		this.listausuarios = usuarios;
	}

	public class ViewHolder {
		public TextView textView;
	}

	@Override
	public int getCount() {
		return listausuarios.size();
	}

	@Override
	public Object getItem(int position) {
		return listausuarios.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listausuarios.get(position).getIdusuario();
	//return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView;

		if (convertView == null) {
			itemView = new View(context);

			itemView = inflater.inflate(R.layout.residencia_adapter_layout, null);

			TextView textView = (TextView) itemView.findViewById(R.id.textResidencia);
			textView.setText(listausuarios.get(position).getNome());
		} else {
			itemView = (View) convertView;
		}

		return itemView;
	}

}*/