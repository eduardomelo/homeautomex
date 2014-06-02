package br.com.homeautomex.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.mb.R;


import br.com.homeautomex.acesso.AcessoWSDL;
import br.com.homeautomex.bean.Ambiente;
import br.com.homeautomex.bean.Residencia;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AmbientesAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;

	public AmbientesAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;

	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@SuppressLint("NewApi")
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		String a = childText;

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item_ambiente,
					null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);

		try {
			JSONObject obj = new JSONObject(childText);

			final Ambiente ambientes = new Ambiente();
			ambientes.setDescricao(obj.getString("Descricao"));
		   
			ambientes.setStatusDispositivos(obj.getString("Status"));
			
			ambientes.setFavorito(obj.getString("Favorito"));
			
			//Log.e("tstando log favorito","aqui"+ambientes.getFavorito() );
			
			ambientes.setChaveDispositivos(obj.getString("Chave"));
			ambientes.setDataAlteracaoDispositivos(obj
					.getString("DataCadastro"));

			String porta = obj.getString("Porta");
			JSONObject port = new JSONObject(porta);
			ambientes.setChavePorta(port.getString("Chave"));

			String ambiente = obj.getString("Ambiente");
			JSONObject ambient = new JSONObject(ambiente);
			ambientes.setChaveAmbiente(ambient.getString("Chave"));

			String aa = "";
			// String ambient = jsonObject.getString("Ambiente");

			//Log.e("vendo chave", ambientes.getChaveDispositivos());

			/*
			 * CheckBox mCheckBox = (CheckBox) convertView
			 * .findViewById(R.id.checkBox1);
			 */

			Switch s = (Switch) convertView.findViewById(R.id.switch1);

			if (s != null) {

				s.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
							boolean isChecked) {

						
						if (isChecked) {

							ambientes.setStatusDispositivos("true");
							String dispositivo = "{\"Chave\":"
									+ ambientes.getChaveDispositivos()
									+ ",\"Status\":\""
									+ ambientes.getStatusDispositivos()
									+ "\",\"Favorito\":\""
									+ ambientes.getFavorito()
									+ "\",\"Descricao\":\""
									+ ambientes.getDescricao()
									+ "\",\"DataACadastro\":\""
									+ ambientes.getDataAlteracaoDispositivos()
									+ "\",\"Ambiente\":{\"Chave\":"
									+ ambientes.getChaveAmbiente()
									+ "},\"Porta\":{\"Chave\":"
									+ ambientes.getChavePorta() + "}}";

						} else
							ambientes.setStatusDispositivos("false");
						String dispositivo = "{\"Chave\":"
								+ ambientes.getChaveDispositivos()
								+ ",\"Status\":\""
								+ ambientes.getStatusDispositivos()
								+ "\",\"Favorito\":\""
									+ ambientes.getFavorito()
								+ "\",\"Descricao\":\""
								+ ambientes.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ ambientes.getDataAlteracaoDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ ambientes.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":"
								+ ambientes.getChavePorta() + "}}";

						
						//Log.e("--------------", dispositivo);
						new LigarTask().execute(dispositivo);
					}
				});
			}

			/*if (ambientes.getStatusDispositivos().equals("true")) {
				Log.e("Favorito Cadastrado",
						"esse e favorito");
				s.setChecked(true);
			} else {
				s.setChecked(false);
			}*/
			
			txtListChild.setText(obj.getString("Descricao"));
			//txtListChild.setText(childText);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		CheckedTextView lblListHeader = (CheckedTextView) convertView
				.findViewById(R.id.textView1);

		((CheckedTextView) convertView).setChecked(isExpanded);

		/*
		 * CheckBox mCheckBox = (CheckBox) convertView
		 * .findViewById(R.id.checkBox1);
		 */

		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	/*
	 * @Override public void onCheckedChanged(CompoundButton arg0, boolean
	 * isChecked) {
	 * 
	 * Toast.makeText(this._context.getApplicationContext(), arg0 +
	 * "Clicou no Switch   " + (isChecked ? "on" : "off"),
	 * Toast.LENGTH_SHORT).show();
	 * 
	 * 
	 * Log.e("testando", "clicando no switch ");
	 * 
	 * //new LigarTask().execute("1"); new LigarTask().execute(
	 * "{\"Chave\":\"1\",\"Status\":\"true\",\"Descricao\":\"lampada\",\"DataAlteracao\":\"2014-04-16T15:42:13.397\",\"Ambiente\":{\"Chave\":1},\"Porta\":{\"Chave\":2}}"
	 * );
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	public class LigarTask extends AsyncTask<String, Void, String> {

		// private ProgressDialog progressDialog;

		// private int perfil;

		/*
		 * public ResidenciaTask(ProgressDialog params) {
		 * 
		 * this.progressDialog = params;
		 * this.progressDialog.setMessage("Carregando Ambientes...");
		 * 
		 * }
		 */

		@Override
		protected void onCancelled() {
			super.onCancelled();
			// this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// this.progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String response = null;
			try {

				response = AcessoWSDL.AcenderLuz(params[0]);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return response;
		}

		@Override
		protected void onPostExecute(String jResult) {
			super.onPostExecute(jResult);

			//Log.e("Ambientes list", jResult);
			if (jResult != null) {

			}

		}

	}

}
