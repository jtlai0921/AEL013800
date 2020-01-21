package workshop.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	@Override
	protected void onDestroy() {
		super.onDestroy();
		tmTask.cancel();		// �פ�TimerTask�@�~
	}

	private ToggleButton btn_t = null;
	private TextView txtview = null;
	private DatePicker dp = null;
	private TimePicker tp = null;
	private Chronometer cm = null;
	private int counter = 0;
	private OnClickListener btnListener = new OnClickListener(){
		public void onClick(View v) {
			// ��toggleButton1���A�Ұ�/����chronometer1�p�ɾ�
			if (btn_t.isChecked()) {
				cm.setBase(SystemClock.elapsedRealtime()); //�k�s
				counter = 0;
				cm.start();
			} else {
				cm.stop();
			}
		}};

	private OnTimeChangedListener tpListener = new OnTimeChangedListener(){
		public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
			showDateTime();
		}};
		
	private OnChronometerTickListener cmListener = new OnChronometerTickListener(){
		public void onChronometerTick(Chronometer arg0) {
			counter++;
			if (counter % 5 == 0) {
				// �C5��n���檺�@�~
				txtview.setText("cunter = " + counter);
			}
		}};
	
	private OnDateChangedListener dpListener = new OnDateChangedListener(){
		public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
			showDateTime();
		}};

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {			
			super.handleMessage(msg);
			//�B�zMessage�T��
			switch (msg.what){
				case 1:
					Toast.makeText(MainActivity.this, "Timer Task", Toast.LENGTH_SHORT).show();
					break;
				default:
			}
		}};	
	
		
	private TimerTask tmTask = new TimerTask(){
		@Override
		public void run() {
			// �Ǧ^Message
			Message msg = new Message();
			msg.what = 1;
			handler.sendMessage(msg);		
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        
        // ����datePicker1�~����
    	((ViewGroup) dp.getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
    	
    	// �]�wtimePicker1��24�p�ɨ�
    	tp.setIs24HourView(true);
    	
    	setListeners();
		Timer tm = new Timer();
		tm.schedule(tmTask, 3000, 5000);
    }
	private void setListeners() {
		// �]�w�ƥ��ť��
    	btn_t.setOnClickListener(btnListener);
    	Calendar c = Calendar.getInstance();
    	dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), dpListener);
    	tp.setOnTimeChangedListener(tpListener);
    	cm.setOnChronometerTickListener(cmListener);
	}
	
	//Ū��datePicker1��timePicker1��ơA�ñN����ܦbtextView1����W
	private void showDateTime(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(dp.getYear(),dp.getMonth(), dp.getDayOfMonth(), tp.getCurrentHour(), tp.getCurrentMinute());
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy�~MM��dd�� hh:mm");
    	txtview.setText(sdf.format(calendar.getTime()));
	}

    private void getViews() {
		// ���o��������ѷ�
    	btn_t = (ToggleButton)findViewById(R.id.toggleButton1);
    	txtview = (TextView)findViewById(R.id.textView1);
    	dp = (DatePicker)findViewById(R.id.datePicker1);
    	tp = (TimePicker)findViewById(R.id.timePicker1);
    	cm = (Chronometer)findViewById(R.id.chronometer1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
