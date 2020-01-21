package workshop.preference_activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// ±Ò°ÊSettingActivity
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SettingActivity.class);
				startActivity(intent);
			}});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
