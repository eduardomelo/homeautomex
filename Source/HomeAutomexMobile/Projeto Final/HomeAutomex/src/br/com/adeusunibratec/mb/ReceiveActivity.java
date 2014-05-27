package br.com.adeusunibratec.mb;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.adeusunibratec.ha.R;
import br.com.adeusunibratec.ha.R.layout;
import br.com.adeusunibratec.ha.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ReceiveActivity extends Activity {

	 TextView name;
	    TextView deal;
	    TextView valid;
	    TextView address;
	    JSONObject json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive);
		Intent intent = getIntent();
		
		name = (TextView) findViewById(R.id.name);
		deal = (TextView) findViewById(R.id.deal);
		valid = (TextView) findViewById(R.id.valid);
		address = (TextView)findViewById(R.id.address);
		String message = intent.getExtras().getString("message");
		try {
			json = new JSONObject(message);
			String stime = json.getString("name");
			name.setText(stime);
			
			String slecturename = json.getString("deal");
			deal.setText(slecturename);
			
			String sroom = json.getString("valid");
			valid.setText(sroom);
			
			String sfaculty = json.getString("address");
			address.setText(sfaculty);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receive, menu);
		return true;
	}


}
