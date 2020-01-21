package workshop.putextras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CalleeActivity extends Activity {
	final int CALLEE_ID = 200;
	private Button btn1;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callee);
	    
		// �B�z�������
		Bundle params = getIntent().getExtras();
		if (params != null) {
			String msg = params.getString ("product") + "\n" + params.getInt("price");
			Toast.makeText (CalleeActivity.this, msg, Toast.LENGTH_LONG).show();
		}
		
		btn1 = (Button)findViewById(R.id.button1);	
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ����CalleeActivity�æ^�Ǹ��
				Intent intent = new Intent ();
				intent.putExtra ("ACK", "CalleeActivity�Ǧ^�����"); 
				setResult (CALLEE_ID, intent);
				finish ();
			}});
	}

}
