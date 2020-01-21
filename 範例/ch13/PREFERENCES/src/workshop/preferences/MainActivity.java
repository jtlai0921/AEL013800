package workshop.preferences;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int count = 1;
	private TextView txtview1 = null;
    @Override
	protected void onPause() {
		// �N�Ұʤ���ɶ��ΰ��榸�Ƽg�J��mypref.xml���n�]�w��
		super.onPause();
		SharedPreferences.Editor spe = getSharedPreferences("mypref", MODE_PRIVATE).edit();
		spe.putString("LAST_LAUNCHED", new Date(System.currentTimeMillis()).toString());
		spe.putInt("LAUNCH_COUNT", count+1);
		spe.commit();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		getPreference();
    }

    private void getPreference() {
		// ��mypref.xmlŪ���W���������ɶ��ΰ��榸�ơA����ܦbtextView1
		SharedPreferences sp = getSharedPreferences("mypref", MODE_PRIVATE);
		String lastLunchDate = sp.getString("LAST_LAUNCHED", new Date(System.currentTimeMillis()).toString());
		count = sp.getInt("LAUNCH_COUNT", 1);
		txtview1 = (TextView) findViewById(R.id.textView1);
		txtview1.setText("last launched date: "+ lastLunchDate + "\nlaunched times: "+ count);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
