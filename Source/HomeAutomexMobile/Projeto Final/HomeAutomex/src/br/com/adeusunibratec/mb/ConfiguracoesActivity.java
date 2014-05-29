package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.ha.CadastroDispositivosCenarioActivity;
import br.com.adeusunibratec.ha.ListarAgendamentoActivity;
import br.com.adeusunibratec.ha.ListarCenariosActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfiguracoesActivity extends Activity implements OnClickListener {

	
	private Intent intent;
	private String teste;
	private Button btnCadastroFavoritos, btnCadastroAgendamento, btnCadastroDispositivoCenario, btnCadastroCenario;
			;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configuracoes);
		
		
		btnCadastroFavoritos = (Button) findViewById(R.id.btnCadastroFavoritos);
		btnCadastroFavoritos.setOnClickListener(this);

		btnCadastroAgendamento = (Button) findViewById(R.id.btnCadastroAgendamento);
		btnCadastroAgendamento.setOnClickListener(this);

		btnCadastroDispositivoCenario = (Button) findViewById(R.id.btnCadastroDispositivosCenario);
		btnCadastroDispositivoCenario.setOnClickListener(this);

		btnCadastroCenario = (Button) findViewById(R.id.btnCadastroCenarios);
		btnCadastroCenario.setOnClickListener(this);
		
		intent = getIntent();
		teste = intent.getStringExtra("idResidencia");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.configuracoes, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btnCadastroFavoritos:

			Intent itCadastroFavoritos = new Intent(this, FavoritosActivity.class);
			itCadastroFavoritos.putExtra("idResidencia", teste);
			startActivity(itCadastroFavoritos);

			break;

		case R.id.btnCadastroAgendamento:

			Intent itCadastroAgendamento = new Intent(this, ProgramacaoActivity.class);
			itCadastroAgendamento.putExtra("idResidencia", teste);
			startActivity(itCadastroAgendamento);

			break;

		case R.id.btnCadastroDispositivosCenario:

			Intent itCadastroDispositivosCenario = new Intent(this, CadastroDispositivosCenarioActivity.class);
			itCadastroDispositivosCenario.putExtra("idResidencia", teste);
			startActivity(itCadastroDispositivosCenario);

			break;

		case R.id.btnCadastroCenarios:

			Intent itCadastroCenarios = new Intent(this,
					CenarioActivity.class);
			itCadastroCenarios.putExtra("idResidencia", teste);
			startActivity(itCadastroCenarios);

			break;

		/*case R.id.btnAgendamento:

			Intent itAgendamento = new Intent(this, ListarAgendamentoActivity.class);
			itAgendamento.putExtra("idResidencia", teste);
			startActivity(itAgendamento);

			break;*/

		default:
			break;
		}

		
	}

}
