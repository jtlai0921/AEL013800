package workshop.startactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn1 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById (R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ³z¹LIntent±Ò°ÊCalleeActivity
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CalleeActivity.class);
				startActivity(intent);
			}});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
