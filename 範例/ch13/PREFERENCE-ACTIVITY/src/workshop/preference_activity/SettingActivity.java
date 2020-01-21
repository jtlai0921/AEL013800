package workshop.preference_activity;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class SettingActivity extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // 依據appsetting.xml內容來產生版面配置，並以mysetting.xml儲存偏好設定
		addPreferencesFromResource(R.xml.appsetting);
		getPreferenceManager().setSharedPreferencesName("mysetting");
	}

}
