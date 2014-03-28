package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class OpcoesUsuariosActivity extends Activity {

	private Intent intent;
	private String teste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes_usuarios);
		

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

}
