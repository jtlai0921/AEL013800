package workshop.alertdlg;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1 = null; 
	private Button btn2 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
		setListeners();
    }

    private void setListeners() {
		// 設定按鈕之Click事件監聽器
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示Toast訊息
		    	Toast.makeText (MainActivity.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示AlertDialog
				showAlert();
			}});
	}

	private void getViews() {
		// 取得介面元件參照
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
	}

	private void showAlert() {
		// 建構並顯示AlertDialog
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.dlg_title);
		dlg.setIcon(R.drawable.ic_launcher);
		dlg.setMessage(R.string.dlg_msg);
		dlg.setPositiveButton("YES", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				// 顯示YES按鈕作業訊息
				Toast.makeText(MainActivity.this, "已按下YES按鈕", Toast.LENGTH_SHORT).show();
				}});
		dlg.setNegativeButton("NO", null);
		dlg.show();

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
