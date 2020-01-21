package workshop.putextras;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1 = null;
	final int MY_ID = 100;
	final int CALLEE_ID = 200;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 處理CalleeActivity回傳資料
		if (requestCode == MY_ID && resultCode == CALLEE_ID){
			Bundle results = data.getExtras();
			if (results != null){
				String result = results.getString ("ACK");
				Toast.makeText (MainActivity.this, result, Toast.LENGTH_LONG).show();
			}
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById (R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 啟動CalleeActivity並傳遞二筆資料
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CalleeActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("product", "mouse");
				bundle.putInt("price", 350);
				intent.putExtras(bundle);
				startActivityForResult (intent, MY_ID);				
			}});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
