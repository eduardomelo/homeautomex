package br.com.adeusunibratec.adapter;

import java.util.ArrayList;

import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaResidenciaAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Residencia> listaResidencias;

	public ListaResidenciaAdapter(Context context,
			ArrayList<Residencia> residencias) {
		this.context = context;
		this.listaResidencias = residencias;
	}

	public class ViewHolder {
		public TextView textView;
	}

	@Override
	public int getCount() {
		return listaResidencias.size();
	}

	@Override
	public Object getItem(int position) {
		return listaResidencias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listaResidencias.get(position).getIdResidencia();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView;

		if (convertView == null) {
			itemView = new View(context);

			itemView = inflater.inflate(R.layout.residencia_adapter_layout, null);

			TextView textView = (TextView) itemView
					.findViewById(R.id.textResidencia);
			textView.setText(listaResidencias.get(position).getCidade());

			TextView textairro = (TextView) itemView
					.findViewById(R.id.textBairro);
			textairro.setText(listaResidencias.get(position).getBairro());

			TextView textNumero = (TextView) itemView
					.findViewById(R.id.textNumero);
			textNumero.setText(listaResidencias.get(position).getNumero());

		} else {
			itemView = (View) convertView;
		}

		return itemView;
	}

}
