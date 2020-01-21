package workshop.datetime_dialog;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	TextView txtmsg = null;
	Button btn1, btn2;

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
				// 顯示DatePickerDialog對話視窗
				showDatepicker();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 顯示TimePickerDialog對話視窗
				showTimepicker();
			}});

	}

    // 以目前時間為初始值開啟TimePickerDialog
	protected void showTimepicker() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		TimePickerDialog timeDlg = new TimePickerDialog(
			MainActivity.this,
			new OnTimeSetListener(){
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					// 將DatePickerDialog之日期設定值顯示在textView1元件上
					txtmsg.setText(arg1 + ":" + arg2);
				}},
			c.get(Calendar.HOUR_OF_DAY),
			c.get(Calendar.MINUTE),
			false);
		timeDlg.show();
	}

	// 以目前日期為初始值開啟DatePickerDialog
	protected void showDatepicker() {
		final Calendar c = Calendar.getInstance();    
		DatePickerDialog dateDlg = new DatePickerDialog (
			MainActivity.this, 
			new OnDateSetListener(){
				public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
					// 將DatePickerDialog之日期設定值顯示在textView1元件上
					txtmsg.setText(arg1 + "/" + arg2 + "/" + arg3);
				}}, 
			c.get(Calendar.YEAR), 
			c.get(Calendar.MONTH), 
			c.get(Calendar.DAY_OF_MONTH));
		dateDlg.show();
	}

	private void getViews() {
		// 取得介面元件參照
		txtmsg = (TextView) findViewById(R.id.textView1);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
