package br.com.adeusunibratec.mb;



import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends Activity implements Runnable {

	private Handler handler;
	private final int SPLASH_DURATION = 6000;
	private Intent intent;
	private boolean backPressed = false;

	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		this.startCountSplash();
	}

	private void startCountSplash() {
		this.handler = new Handler();
		this.handler.postDelayed(this, SPLASH_DURATION);

		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
		l.clearAnimation();
		l.startAnimation(anim);

		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.reset();
		ImageView iv = (ImageView) findViewById(R.id.logo);
		iv.clearAnimation();
		iv.startAnimation(anim);
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