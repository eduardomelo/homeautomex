package br.com.adeusunibratec.mb;

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
