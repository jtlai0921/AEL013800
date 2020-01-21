package workshop.alertdialog_ii;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1, btn2, btn3, btn4;
	private String[] lessons;
	int sel = 0;
	boolean[] checked = null; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	lessons = getResources().getStringArray (R.array.courses);
        checked = new boolean[lessons.length];
        getViews();
        setListeners();
    }

    private void setListeners() {
		// 設定按鈕元件之Click事件監聽器
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示一般清單式AlertDialog
				show_listAlert();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示單選清單式AlertDialog
				show_schoiceAlert();
			}});
		btn3.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示複選清單式AlertDialog
				show_mchoiceAlert();
			}});
		btn4.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示自訂版面AlertDialog
				show_customizedAlert();
			}});
    }
    
	// 建構並顯示自訂版面AlertDialog
	private void show_customizedAlert() {
		//依據login.xml建構版面元件
		final TableLayout loginLayout = (TableLayout) getLayoutInflater().inflate(R.layout.login, null);
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		//設定使用loginLayout做為對話視窗面版
		dlg.setView(loginLayout);
		dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// 使用Toast顯示輸入之資料內容		
				TextView txtname = (TextView) loginLayout.findViewById(R.id.editText1);
				TextView txtpasswd = (TextView) loginLayout.findViewById(R.id.editText2);
				String msg = txtname.getText().toString() + ", " + txtpasswd.getText().toString();
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}});
		dlg.show();
	}
	
	//建構並顯示複選清單式AlertDialog
	private void show_mchoiceAlert() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		// 設定使用複選清單，並實作點選清單項目之事件監聽器
		dlg.setMultiChoiceItems(lessons, checked, new DialogInterface.OnMultiChoiceClickListener(){
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// 記錄項目之勾選狀態
				checked[which] = isChecked;
			}});
		// 加入OK按鈕，並實作OK按鈕之Click事件監聽器
		dlg.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				// 使用Toast顯示所有被勾選項目之內容
				String msg = "";
				for (int i = 0; i<lessons.length; i++)
					if (checked[i]) msg = msg + lessons[i] + ", ";
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}});
		dlg.show();
	}

	//建構並顯示單選清單式AlertDialog
	private void show_schoiceAlert() {
		AlertDialog.Builder dlg = new AlertDialog.Builder (MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon (R.drawable.ic_launcher);
		//設定使用單選清單，並實作點選清單項目之Click事件監聽器
		dlg.setSingleChoiceItems (lessons, 0, new DialogInterface.OnClickListener () {
			public void onClick (DialogInterface dialog, int which) {
				sel = which;
			}});
		//加入OK按鈕，並實作OK按鈕之Click事件監聽器
		dlg.setPositiveButton ("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// 使用Toast顯示被點選之項目內容
				Toast.makeText(MainActivity.this, lessons[sel], Toast.LENGTH_SHORT).show();
			}});
		dlg.show ();	
	}

	// 建構並顯示文字清單式AlertDialog
	private void show_listAlert() {
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		//設定使用文字清單，並實作點選清單項目之Click事件監聽器
		dlg.setItems(lessons, new DialogInterface.OnClickListener(){
			public void onClick (DialogInterface dialog, int which){
				// 使用Toast顯示被點選之項目內容
				Toast.makeText(MainActivity.this, lessons[which], Toast.LENGTH_SHORT).show();
			}});
		//加入CANCEL按鈕
		dlg.setNeutralButton("CANCEL", null); 
		dlg.show();
	}

	private void getViews() {
		// 取得介面元件參照
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
