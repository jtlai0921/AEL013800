package workshop.preference_activity;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class SettingActivity extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // �̾�appsetting.xml���e�Ӳ��ͪ����t�m�A�åHmysetting.xml�x�s���n�]�w
		addPreferencesFromResource(R.xml.appsetting);
		getPreferenceManager().setSharedPreferencesName("mysetting");
	}

}
