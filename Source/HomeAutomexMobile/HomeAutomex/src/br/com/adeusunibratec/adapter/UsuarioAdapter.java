/*package br.com.adeusunibratec.adapter;


Alberico essa classe não vamos precisar dela não ate por que não
precisamos listar usuario mas fiz seguindo o meu exemplo só pra tu dar uma sacada 

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.adeusunibratec.bean.Usuario;
import br.com.adeusunibratec.ha.R;

public class UsuarioAdapter extends BaseAdapter{
	
	private Context ctx;
	private List<Usuario> usuarios;

	public UsuarioAdapter(Context ctx, List<Usuario> usuarios) {
		this.ctx = ctx;
		this.usuarios = usuarios;
	}

	@Override
	public int getCount() {
		return usuarios.size();
	}

	@Override
	public Object getItem(int position) {
		return usuarios.get(position);
	}

	@Override
	public long getItemId(int positon) {
		return usuarios.get(positon).getChave();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder; 
		
		Usuario usu = usuarios.get(position);
		if (convertView == null){ 
		convertView = LayoutInflater.from(ctx).inflate(R.layout.linha_usuarios, null);

		holder = new ViewHolder();
		
//		holder.txtRazao = (TextView)
//				convertView.findViewById(R.id.txt_razao);
		holder.txtNome = (TextView)
				convertView.findViewById(R.id.txtNomeUsuario);
		holder.txtTelefone = (TextView)
				convertView.findViewById(R.id.txtTelefone);
		holder.txtCelular = (TextView)
				convertView.findViewById(R.id.txtCelular);
		holder.txtEmail = (TextView)
				convertView.findViewById(R.id.txtEmail);
		holder.txtLogin = (TextView)
				convertView.findViewById(R.id.txtLogin);
		holder.txtSenha = (TextView)
				convertView.findViewById(R.id.txtSenha);
		
		convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.txtNome.setText(usu.getNome());
		holder.txtTelefone.setText(usu.getTelefone());
		holder.txtCelular.setText(usu.getCelular());
		holder.txtEmail.setText(usu.getEmail());
		holder.txtLogin.setText(usu.getLogin());
		holder.txtSenha.setText(usu.getSenha());
//		holder.txtRazao.setText(usu.get);

		return convertView;
	}
	static class ViewHolder {
//		TextView txtRazao;
		TextView txtNome;
		TextView txtTelefone;
		TextView txtCelular;
		TextView txtEmail;
		TextView txtLogin;
		TextView txtSenha;
		
		
	}


}
*/