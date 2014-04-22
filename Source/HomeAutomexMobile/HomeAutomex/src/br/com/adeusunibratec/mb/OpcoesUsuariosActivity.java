package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.ha.AmbienteActivity;
import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OpcoesUsuariosActivity extends Activity implements OnClickListener {

	private Intent intent;
	private String teste;
	private Button btnFavoritos, btnConfiguracoes, btnCenario, btnAmbientes,
			btnProgramacao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes_usuarios);

		btnFavoritos = (Button) findViewById(R.id.btn_favoritos);
		btnFavoritos.setOnClickListener(this);

		btnConfiguracoes = (Button) findViewById(R.id.btn_configuracoes);
		btnConfiguracoes.setOnClickListener(this);

		btnCenario = (Button) findViewById(R.id.btn_cenario);
		btnCenario.setOnClickListener(this);

		btnAmbientes = (Button) findViewById(R.id.btn_ambiente);
		btnAmbientes.setOnClickListener(this);

		btnProgramacao = (Button) findViewById(R.id.btn_programacao);
		btnProgramacao.setOnClickListener(this);

		intent = getIntent();
		teste = intent.getStringExtra("idResidencia");

		Toast.makeText(getApplication(), teste, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opcoes_usuarios, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_ambiente:
//			Intent itAmbiente = new Intent(this,AmbienteActivity.class);
//			itAmbiente.putExtra("idAmbiente", teste);
//			startActivity(itAmbiente);
			Toast.makeText(getApplication(), "Ambiente", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_cenario:
			Toast.makeText(getApplication(), "Cenario", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_configuracoes:
			Toast.makeText(getApplication(), "Configura��es", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_programacao:
			Toast.makeText(getApplication(), "Programa��o", Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_favoritos:
			Toast.makeText(getApplication(), "Favoritos", Toast.LENGTH_LONG).show();
//			Intent itFavoritos = new Intent(this,FavoritosActivity.class);
//			itFavoritos.putExtra("idAmbiente", teste);
//			startActivity(itFavoritos);
			break;

		default:
			break;
		}
	}

}
