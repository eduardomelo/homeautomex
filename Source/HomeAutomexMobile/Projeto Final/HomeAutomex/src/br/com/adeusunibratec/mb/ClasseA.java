package br.com.homeautomex.mb;

import br.com.adeusunibratec.mb.R;
import android.app.Activity;
import android.os.Bundle;

public class ClasseA extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a);
		
		this.finish();
	}

}
