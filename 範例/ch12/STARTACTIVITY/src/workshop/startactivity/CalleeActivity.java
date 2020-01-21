package workshop.startactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalleeActivity extends Activity {
	Button btn1 = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callee);
		btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// µ²§ôCalleeActivity¡Aªð¦^MainActivity
				finish();
			}});
	}

}
