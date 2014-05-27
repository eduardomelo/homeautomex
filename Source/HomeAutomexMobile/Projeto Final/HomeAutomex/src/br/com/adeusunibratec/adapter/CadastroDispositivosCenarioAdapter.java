package br.com.adeusunibratec.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.com.adeusunibratec.acesso.AcessoWSDL;
import br.com.adeusunibratec.adapter.AmbientesAdapter.LigarTask;
import br.com.adeusunibratec.bean.Ambiente;
import br.com.adeusunibratec.ha.R;

public class CadastroDispositivosCenarioAdapter extends
		BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;
	
	String chaveCenario = null;

	public CadastroDispositivosCenarioAdapter(Context context,
			List<String> listDataHeader,
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

		Log.e("teste json aaaa", childText);
		
		String a = childText;

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(
					R.layout.list_item_cadastrodispositivocenario, null);
		}

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);
		
		
		try {
			JSONObject obj = new JSONObject(childText);

			final Ambiente ambientes = new Ambiente();
			ambientes.setDescricao(obj.getString("Descricao"));
			   ambientes.setChave(obj.getString("ChaveDispositivo"));
			  ambientes.setChaveAmbiente(obj.getString("chaveCenario"));
			
			
		

		Switch s = (Switch) convertView.findViewById(R.id.switch1);

		if (s != null) {

			s.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {

					if (isChecked) {
						Toast.makeText(_context, "ei aqui"+ambientes.getChave()+"AM"+ambientes.getChaveAmbiente(), Toast.LENGTH_LONG).show();
					}
				}
			});
		}

		Log.e("testandojsn", childText);

		txtListChild.setText(ambientes.getDescricao());

		
		}catch (JSONException e) {
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

		Log.e("teeeeeee", "olha"+headerTitle);
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

			Log.e("Ambientes list", jResult);
			if (jResult != null) {

			}

		}

	}

}
