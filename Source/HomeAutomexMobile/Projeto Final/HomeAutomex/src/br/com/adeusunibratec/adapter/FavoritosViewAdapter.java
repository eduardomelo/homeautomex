package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.AmbientesAdapter.LigarTask;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.mb.R;
import br.com.adeusunibratec.mb.FavoritosActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritosViewAdapter extends BaseExpandableListAdapter {

	String favorit = "";
	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;

	public FavoritosViewAdapter(Context context, List<String> listDataHeader,
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

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_item, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);

		try {
			JSONObject obj = new JSONObject(childText);

			final Ambiente ambientes = new Ambiente();
			ambientes.setDescricao(obj.getString("Descricao"));

			ambientes.setChaveDispositivos(obj.getString("Chave"));
			ambientes.setDataAlteracaoDispositivos(obj
					.getString("DataCadastro"));
			
			
			ambientes.setFavorito(obj.getString("Favorito"));

			
			Log.e("-------------------------", ambientes.getFavorito());
			/*try {
				ambientes.setFavorito(obj.getString("Favorito"));

				favorit = ambientes.getFavorito();
			} catch (JSONException e) {

				favorit = "false";
			}*/
			
			String porta = obj.getString("Porta");
			JSONObject port = new JSONObject(porta);
			ambientes.setChavePorta(port.getString("Chave"));

			String ambiente = obj.getString("Ambiente");
			JSONObject ambient = new JSONObject(ambiente);
			ambientes.setChaveAmbiente(ambient.getString("Chave"));

			String aa = "";
			// String ambient = jsonObject.getString("Ambiente");

			//Log.e("JSONObject obj", porta);

			CheckBox mCheckBox = (CheckBox) convertView
					.findViewById(R.id.checkBox1);

			mCheckBox.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// is chkIos checked?
					if (((CheckBox) v).isChecked()) {
						/*
						 * Toast.makeText(_context, childText,
						 * Toast.LENGTH_LONG) .show();
						 */

						ambientes.setFavorito("true");
						ambientes.setStatusDispositivos("False");
						String dispositivo = "{\"Chave\":"
								+ ambientes.getChaveDispositivos()
								+ ",\"Favorito\":\"" + ambientes.getFavorito()
								+ "\",\"Status\":\""
								+ ambientes.getStatusDispositivos()
								+ "\",\"Descricao\":\""
								+ ambientes.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ ambientes.getDataAlteracaoDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ ambientes.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":"
								+ ambientes.getChavePorta() + "}}";
						new LigarTask().execute(dispositivo);
						Log.e("Favorito Cadastrado",
								"ESSE DISPOSITIVO É FAVORITO");

					} else {

						ambientes.setFavorito("true");
						ambientes.setStatusDispositivos("False");
						String dispositivo = "{\"Chave\":"
								+ ambientes.getChaveDispositivos()
								+ ",\"Favorito\":\"" + ambientes.getFavorito()
								+ "\",\"Status\":\""
								+ ambientes.getStatusDispositivos()
								+ "\",\"Descricao\":\""
								+ ambientes.getDescricao()
								+ "\",\"DataACadastro\":\""
								+ ambientes.getDataAlteracaoDispositivos()
								+ "\",\"Ambiente\":{\"Chave\":"
								+ ambientes.getChaveAmbiente()
								+ "},\"Porta\":{\"Chave\":"
								+ ambientes.getChavePorta() + "}}";

						Log.e("Favorito Cadastrado",
								"ESSE DISPOSITIVO NÃO É FAVORITO");
						new LigarTask().execute(dispositivo);
					}

				}
			});

			if (ambientes.getFavorito().equals("true")) {
				Log.e("Favorito Cadastrado",
						"esse e favorito");
				mCheckBox.setChecked(true);
			} else {
				mCheckBox.setChecked(false);
			}
			

			txtListChild.setText(ambientes.getDescricao());

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

			Log.e("Ambientes list", jResult);
			if (jResult != null) {

			}

		}

	}

}
