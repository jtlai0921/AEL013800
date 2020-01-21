package workshop.implicit_intent;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	String[] implicit_intent = {
			"Implicit intent - CALL", "Implicit intent - SMS",
			"Implicit intent - EMAIL", "Implicit intent - MAP",
			"Implicit intent - STREET MAP", "Implicit intent - ROUTE",
			"Implicit intent - CAMERA"};
	private Button btn;
	private DialogInterface.OnClickListener doImplicitIntent = new DialogInterface.OnClickListener(){
		public void onClick(DialogInterface dialog, int which) {
			Intent intent;
			// 執行Implicit intent作業
			switch (which){
			case 0:		//Implicit intent - CALL
				intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5556"));
				startActivity(intent);		
				break;
			case 1:		//Implicit intent - SMS
				intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:5556"));  
				intent.putExtra("sms_body", "The SMS text");	
				startActivity(intent);		
				break;
			case 2:		//Implicit intent - EMAIL
				intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:sswu@nkut.edu.tw"));
				intent.putExtra(Intent.EXTRA_TEXT, "The email body text");  
				intent.putExtra(Intent.EXTRA_SUBJECT, "hello");  
				startActivity(intent);			
				break;
			case 3:		//Implicit intent - MAP
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:23.978996,120.696672?z=18")); 
				startActivity(intent);		
				break;
			case 4:		//Implicit intent - STREET MAP
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=23.978996,120.696672&cbp=1,0,,0,1.0"));
				startActivity(intent);		
				break;
			case 5:		//Implicit intent - ROUTE
				intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?f=d&saddr=23.979116 120.696788&daddr=24.05832 120.679065&hl=tw"));  		
				startActivity(intent);	
				break;
			case 6:		//Implicit intent - CAMERA
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    
				//設定相片存檔檔名/sdcard/lab12.3.jpg
				File f = new File(Environment.getExternalStorageDirectory().toString() + "/lab12.3.jpg");  
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));   
				startActivity(intent);
				break;				
			}			
		}};

		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 以implicit_intent陣列為資料來源建立項目清單式AlertDialog，並設定doImplicitIntent()做為清單項目之Click事件監聽器
				AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
				dlg.setTitle(R.string.title_activity_main);
				dlg.setIcon(R.drawable.ic_launcher);
				dlg.setItems(implicit_intent, doImplicitIntent);
				//加入CANCEL按鈕，並設定按下CANCEL按鈕之Click事件監聽器為null
				dlg.setNeutralButton("CANCEL", null); 
				dlg.show();
			}});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
