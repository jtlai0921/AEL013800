package workshop.notification;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
		btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				doNotify();
			}});	
    }

    protected void doNotify() {
		// TODO Auto-generated method stub
		NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, "重要訊息", System.currentTimeMillis());
		PendingIntent pIntent = PendingIntent.getActivity(
					this,
					0,
 					new Intent(Intent.ACTION_VIEW, Uri.parse("geo:23.978996,120.696672?z=18")),
					PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(
            	this,
					"南開科大",
 					"電子工程系(所)-電腦遊戲設計組",
					pIntent);
		notification.flags|=Notification.FLAG_AUTO_CANCEL;
		nManager.notify(0, notification);
		finish();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
