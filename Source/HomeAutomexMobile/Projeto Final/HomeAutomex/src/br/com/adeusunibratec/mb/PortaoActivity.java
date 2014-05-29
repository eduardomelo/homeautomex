package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.adapter.AmbientesAdapter.LigarTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class PortaoActivity extends Activity {

	Switch portao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_portao);

		portao = (Switch) findViewById(R.id.switchPortao);

		if (portao != null) {

			portao.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton button,
						boolean isChecked) {

					if (isChecked) {

						Toast.makeText(getApplication(), "Ligado", Toast.LENGTH_LONG)
								.show();

					} else
						Toast.makeText(getApplication(), "Desligado", Toast.LENGTH_LONG)
								.show();
					// new LigarTask().execute(dispositivo);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.portao, menu);
		return true;
	}

}
