package workshop.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
	protected void onDestroy() {
    	//�ϥ�Log�O��Destroy�@�~�T��
		Log.d("ursa", "onDestroy");		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		//�ϥ�Log�O��Pause�@�~�T��
		Log.d("ursa", "onPause");		
		super.onPause();
	}

	@Override
	protected void onRestart() {
		//�ϥ�Log�O��Restart�@�~�T��
		Log.d("ursa", "onRestart");		
		super.onRestart();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//�ϥ�Log�O��RestoreInstanceState�@�~�T��
		Log.d("ursa", "onRestoreInstanceState");		
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		//�ϥ�Log�O��Resume�@�~�T��
		Log.d("ursa", "onResume");		
		super.onResume();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//�ϥ�Log�O��SaveInstanceState�@�~�T��
		Log.d("ursa", "onSaveInstanceState");		
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStart() {
		//�ϥ�Log�O��Start�@�~�T��
		Log.d("ursa", "onStart");		
		super.onStart();
	}

	@Override
	protected void onStop() {
		//�ϥ�Log�O��Stop�@�~�T��
		Log.d("ursa", "onStop");		
		super.onStop();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		Log.d("ursa", "onCreate");		//�ϥ�Log�O��Create�@�~�T��
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
