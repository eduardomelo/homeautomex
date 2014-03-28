package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity implements Runnable {

	private Handler handler;
	private final int SPLASH_DURATION = 1000;
	private Intent intent;
	private boolean backPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		this.startCountSplash();
	}

	private void startCountSplash() {
		this.handler = new Handler();
		this.handler.postDelayed(this, SPLASH_DURATION);
	}

	@Override
	public void run() {
		finish();
		if (!this.backPressed) {
			this.intent = new Intent(SplashActivity.this, LoginActivity.class);
			SplashActivity.this.startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		this.backPressed = true;
		super.onBackPressed();
	}
}
