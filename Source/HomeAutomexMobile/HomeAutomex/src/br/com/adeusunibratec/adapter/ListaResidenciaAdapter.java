package br.com.adeusunibratec.adapter;

import br.com.adeusunibratec.bean.Residencia;
import br.com.adeusunibratec.ha.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ListaResidenciaAdapter extends BaseAdapter {

    private Context context;
    private List<Residencia> listaResidencias;

    public ListaResidenciaAdapter(Context context,
            List<Residencia> residencias) {
        this.context = context;
        this.listaResidencias = residencias;
    }

    public int getCount() {
        return listaResidencias.size();
    }

    public Object getItem(int i) {
        return listaResidencias.get(i);
    }

    public long getItemId(int i) {
        return listaResidencias.get(i).getIdResidencia();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Residencia r = listaResidencias.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.residencia_adapter_layout, null);

            holder = new ViewHolder();

            holder.txtResidencia = (TextView) convertView.findViewById(R.id.textResidencia);
            holder.txtBairro = (TextView) convertView.findViewById(R.id.textBairro);
            holder.txtNumero = (TextView) convertView.findViewById(R.id.textNumero);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtResidencia.setText(r.getLogradouro());
        holder.txtNumero.setText(r.getNumero());
        holder.txtBairro.setText(r.getBairro());

        return convertView;
    }

    static class ViewHolder {

        TextView txtResidencia;
        TextView txtBairro;
        TextView txtNumero;
    }
}
