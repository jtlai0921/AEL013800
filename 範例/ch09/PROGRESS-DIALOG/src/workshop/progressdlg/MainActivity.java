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
		private int progress;    	// �i�׭� 
		private int type;			// �i�תO�˦��N�X
		ProgressDialog progressDialog; 
		public AsyncUpdateProgress(int style){
			type = style;						//ProgressDialog�i�תO�˦�
		}

		@Override
		// �i��AsyncTask�����@�~		
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.dismiss();			// ����ProgressDialog
			txtmsg.setText(result);				// �btextView1���AsyncTask���浲�G
		}
		
		@Override
		// �i��AsyncTask�_�l�@�~
		protected void onPreExecute() {
			super.onPreExecute();
			progress = 0;   					//�]�w�i�ת�l��
			
			// �غc�����ProgressDialog
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setTitle("Progress Dialog");
			progressDialog.setMessage("Wait!");
			progressDialog.setProgressStyle(type);
			progressDialog.show();
		}

		@Override
		// �o�Ӥ�k�bdoInBackground�ե�publishProgress�ɳQĲ�o
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);	//��sProgressDialog�i�׭�
		}

		@Override
		// �i��AsyncTask�I���@�~
		protected String doInBackground(Void... params) {
			while(progress<100){    
				progress++; 					// ��s�i�׭�
				publishProgress(progress);		// �q����s�i�ת��A
				SystemClock.sleep(20);			// ��v20ms
			}
			return "�@�~����";					// �Ǧ^"�@�~����"�T��
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
		// �]�w���s��Click�ƥ��ť��
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// �_�l�@�����ζi�׵�����AsyncTask
				txtmsg.setText("�}�l�@�~");
				new AsyncUpdateProgress(ProgressDialog.STYLE_SPINNER).execute();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// �_�l�@�Ӥ��������i�׵�����AsyncTask
				txtmsg.setText("�}�l�@�~");
				new AsyncUpdateProgress(ProgressDialog.STYLE_HORIZONTAL).execute();
			}});
	}

	private void getViews() {
		// ���o��������ѷ�
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
