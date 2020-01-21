package workshop.multievents;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private class EditTextListener implements OnKeyListener, OnFocusChangeListener {
		
		// 處理FocusChange事件
		public void onFocusChange(View arg0, boolean arg1) {
			// 依事件來源進行對應作業
			switch (arg0.getId())
			{
				case R.id.editText1:
					if (arg1) txtview.setText("editText1 got focus");
					break;
				case R.id.editText2:
					if (arg1) txtview.setText("editText2 got focus");
					break;
			}
		}

		// 處理Key Press事件
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// 依事件來源進行對應作業
			switch (v.getId())
			{
			case R.id.editText1:
				txtview.setText("Key pressed in editText1");
				break;
			case R.id.editText2:
				txtview.setText("Key pressed in editText2");
				break;
			}
			return false;
		}
	}

	private Button btn = null;
	private TextView txtview = null;
	private EditText edtext1 = null, edtext2 = null;

	private OnClickListener btnListener = new OnClickListener(){
		public void onClick(View arg0) {
			// 顯示button1作業訊息
			txtview.setText("Button1 was Clicked");
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
		EditTextListener edTextListener = new EditTextListener();
		edtext1.setOnKeyListener(edTextListener);
		edtext1.setOnFocusChangeListener(edTextListener);
		edtext2.setOnKeyListener(edTextListener);
		edtext2.setOnFocusChangeListener(edTextListener);	
		btn.setOnClickListener(btnListener );
	}

	private void getViews() {
		// 取得介面元件參照
		txtview = (TextView)findViewById(R.id.textView1);
		btn = (Button)findViewById(R.id.button1);
		edtext1 = (EditText)findViewById(R.id.editText1);
		edtext2 = (EditText)findViewById(R.id.editText2);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
