package workshop.progressdlg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private class AsyncUpdateProgress extends AsyncTask<Void, Integer, String> {
		private int progress;    	// 進度值 
		private int type;			// 進度板樣式代碼
		ProgressDialog progressDialog; 
		public AsyncUpdateProgress(int style){
			type = style;						//ProgressDialog進度板樣式
		}

		@Override
		// 進行AsyncTask結束作業		
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.dismiss();			// 關閉ProgressDialog
			txtmsg.setText(result);				// 在textView1顯示AsyncTask執行結果
		}
		
		@Override
		// 進行AsyncTask起始作業
		protected void onPreExecute() {
			super.onPreExecute();
			progress = 0;   					//設定進度初始值
			
			// 建構並顯示ProgressDialog
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setTitle("Progress Dialog");
			progressDialog.setMessage("Wait!");
			progressDialog.setProgressStyle(type);
			progressDialog.show();
		}

		@Override
		// 這個方法在doInBackground調用publishProgress時被觸發
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);	//更新ProgressDialog進度值
		}

		@Override
		// 進行AsyncTask背景作業
		protected String doInBackground(Void... params) {
			while(progress<100){    
				progress++; 					// 更新進度值
				publishProgress(progress);		// 通知更新進度狀態
				SystemClock.sleep(20);			// 休眠20ms
			}
			return "作業完成";					// 傳回"作業完成"訊息
		}
	}

	private Button btn1, btn2;
	private TextView txtmsg;

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
			public void onClick(View arg0) {
				// 起始一個環形進度視窗的AsyncTask
				txtmsg.setText("開始作業");
				new AsyncUpdateProgress(ProgressDialog.STYLE_SPINNER).execute();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// 起始一個水平條狀進度視窗的AsyncTask
				txtmsg.setText("開始作業");
				new AsyncUpdateProgress(ProgressDialog.STYLE_HORIZONTAL).execute();
			}});
	}

	private void getViews() {
		// 取得介面元件參照
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		txtmsg = (TextView) findViewById(R.id.textView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
