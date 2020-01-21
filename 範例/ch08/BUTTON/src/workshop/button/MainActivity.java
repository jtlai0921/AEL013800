package workshop.button;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private TextView txtview = null;
	private ToggleButton t_btn = null;
	private RadioGroup rg = null;
	private CheckBox chk1 = null, chk2 = null;
	private OnClickListener btnListener = new OnClickListener(){
		public void onClick(View arg0) {
			// 檢查toggleButton1的狀態
			if (t_btn.isChecked())
				txtview.setText("開開已開啟");
			else
				txtview.setText("開關已關閉");
		}};

	private OnCheckedChangeListener rgListener = new OnCheckedChangeListener(){
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// 檢查radioGroup1內的選取狀態
			switch  (arg1) {
				case R.id.radio0:
					txtview.setText("Sunrise");
					break;
				case R.id.radio1:
					txtview.setText("Sunset");
					break;
			}
		}};
		
	private OnClickListener chk1Listener = new OnClickListener(){
		public void onClick(View v) {
			// 檢查checkBox1狀態
			if (chk1.isChecked())
				txtview.setText(chk1.getText() + "was checked");	
			else
				txtview.setText("");			
		}};
		
	private OnClickListener chk2Listener = new OnClickListener(){
		public void onClick(View v) {
			// 檢查checkBox2狀態
			if (chk2.isChecked())
				txtview.setText(chk2.getText() + "was checked");	
			else
				txtview.setText("");
		}};		

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        setListeners();
    }

    private void setListeners() {
		// 設定事件監聽器
    	t_btn.setOnClickListener(btnListener);
    	rg.setOnCheckedChangeListener(rgListener);
    	chk1.setOnClickListener(chk1Listener);
    	chk2.setOnClickListener(chk2Listener);
	}

	private void getViews() {
		// 取得介面元件參照
    	txtview = (TextView)findViewById(R.id.textView1);
    	t_btn = (ToggleButton)findViewById(R.id.toggleButton1);
    	rg = (RadioGroup)findViewById(R.id.radioGroup1);
    	chk1 = (CheckBox)findViewById(R.id.checkBox1);
    	chk2 = (CheckBox)findViewById(R.id.checkBox2);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
