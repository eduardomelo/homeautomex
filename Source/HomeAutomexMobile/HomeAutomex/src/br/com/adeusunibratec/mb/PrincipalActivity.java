package br.com.adeusunibratec.mb;

import br.com.adeusunibratec.acesso.ConsultarTodosUsuarios;
import br.com.adeusunibratec.ha.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PrincipalActivity extends Activity{
	Button btn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
       try {
    	   new ConsultarTodosUsuarios().execute();
	} catch (Exception e) {
		System.out.println(e);
	}
        
        
    }
    
}
