package workshop.sms;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText phone;
	private EditText message;
	private Button btnSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		getViews();
		btnSend.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// �ϥ�SmsManager����ǰe²�T
				SmsManager smsManager = SmsManager.getDefault(); 
				smsManager.sendTextMessage(
		    							phone.getText().toString(),
		    						 	null, 
		    						 	message.getText().toString(),
		    							null,
		    							null);
			}});
    }
    
	private void getViews() {
		// ���o��������ѷ�
		phone = (EditText)findViewById(R.id.editText1);
		message = (EditText)findViewById(R.id.editText2);
		btnSend = (Button)findViewById(R.id.button1); 
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
