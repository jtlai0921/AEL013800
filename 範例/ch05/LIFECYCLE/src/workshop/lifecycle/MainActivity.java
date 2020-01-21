package workshop.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
	protected void onDestroy() {
    	//使用Log記錄Destroy作業訊息
		Log.d("ursa", "onDestroy");		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		//使用Log記錄Pause作業訊息
		Log.d("ursa", "onPause");		
		super.onPause();
	}

	@Override
	protected void onRestart() {
		//使用Log記錄Restart作業訊息
		Log.d("ursa", "onRestart");		
		super.onRestart();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//使用Log記錄RestoreInstanceState作業訊息
		Log.d("ursa", "onRestoreInstanceState");		
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		//使用Log記錄Resume作業訊息
		Log.d("ursa", "onResume");		
		super.onResume();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//使用Log記錄SaveInstanceState作業訊息
		Log.d("ursa", "onSaveInstanceState");		
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart() {
		//使用Log記錄Start作業訊息
		Log.d("ursa", "onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		//使用Log記錄Stop作業訊息
		Log.d("ursa", "onStop");		
		super.onStop();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		Log.d("ursa", "onCreate");		//使用Log記錄Create作業訊息
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
