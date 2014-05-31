package br.com.adeusunibratec.mb;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;




import br.com.adeusunibratec.bean.Usuario;
import br.com.adeusunibratec.dao.UsuarioDAO;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;




import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
		
		MediaPlayer player =   
		   		 player = MediaPlayer.create(this, R.drawable.home);
		   		 player.start();
		   		 
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
			Usuario usuario = new Usuario();
			UsuarioDAO dao = new UsuarioDAO(this);
			usuario = dao.buscarCarroPorNome();
			 
			this.intent = new Intent(SplashActivity.this, LoginActivity.class);
			if(usuario.getLogin() != null && usuario.getSenha() != null){
				intent.putExtra("login", usuario.getLogin());
				intent.putExtra("senha", usuario.getSenha());
			}
			
			SplashActivity.this.startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		this.backPressed = true;
		super.onBackPressed();
	}

}