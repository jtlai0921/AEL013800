package workshop.event;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn1 = null;
	Button btn2 = null;
	TextView txtview = null;
	private OnClickListener btn2ClickListener = new OnClickListener(){
		public void onClick(View arg0) {
			// 顯示button2作業訊息
			txtview.setText("Button 2 was clicked");			
		}};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      	txtview = (TextView) findViewById(R.id.textView1);
     	btn1 = (Button)findViewById(R.id.button1);
      	btn2 = (Button)findViewById(R.id.button2);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示button1作業訊息
				txtview.setText("Button 1 was clicked");
			}});
		btn2.setOnClickListener(btn2ClickListener );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
