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
		// �]�w���s��Click�ƥ��ť��
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ���DatePickerDialog��ܵ���
				showDatepicker();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ���TimePickerDialog��ܵ���
				showTimepicker();
			}});

	}

    // �H�ثe�ɶ�����l�ȶ}��TimePickerDialog
	protected void showTimepicker() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		TimePickerDialog timeDlg = new TimePickerDialog(
			MainActivity.this,
			new OnTimeSetListener(){
				public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
					// �NDatePickerDialog������]�w����ܦbtextView1����W
					txtmsg.setText(arg1 + ":" + arg2);
				}},
			c.get(Calendar.HOUR_OF_DAY),
			c.get(Calendar.MINUTE),
			false);
		timeDlg.show();
	}

	// �H�ثe�������l�ȶ}��DatePickerDialog
	protected void showDatepicker() {
		final Calendar c = Calendar.getInstance();    
		DatePickerDialog dateDlg = new DatePickerDialog (
			MainActivity.this, 
			new OnDateSetListener(){
				public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
					// �NDatePickerDialog������]�w����ܦbtextView1����W
					txtmsg.setText(arg1 + "/" + arg2 + "/" + arg3);
				}}, 
			c.get(Calendar.YEAR), 
			c.get(Calendar.MONTH), 
			c.get(Calendar.DAY_OF_MONTH));
		dateDlg.show();
	}

	private void getViews() {
		// ���o��������ѷ�
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
