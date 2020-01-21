package workshop.file_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btn1, btn2, btn3, btn4, btn5;
	private TextView txtview1 = null;
	private EditText edtext1 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
		setListeners();
    }
    
    // 	設定按鈕之Click事件監聽器
    private void setListeners() {
    	btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				writefile();
			}});
    	btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				readfile();
			}});
    	btn3.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				writeSDfile();
			}});
    	btn4.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				readSDfile();
			}});	
    	btn5.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				readAssetsfile();
			}});
	}

	protected void readAssetsfile() {
		// 由應用程式assets目錄下的assetfile.txt檔讀取資料
		try {
			InputStream is = getAssets().open("assetfile.txt");
			byte[] buffer = new byte[256];
			int count = is.read(buffer,0,255);
			String msg = new String(buffer, 0, count);
			txtview1.setText(msg);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeSDfile() {
		// 寫入資料到SD記憶卡下的sdfile.txt檔
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		String myfile = sdcardDir+"/sdfile.txt";
		try {
			FileOutputStream fos = new FileOutputStream(myfile, true);
			String msg = "\n"+ edtext1.getText().toString();
			fos.write(msg.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	protected void readSDfile() {
		// 由SD記憶卡下的sdfile.txt檔讀取資料
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		String myfile = sdcardDir+"/sdfile.txt";
		try {
			FileInputStream fis = new FileInputStream(myfile);
			byte[] buffer = new byte[256];
			int count = fis.read(buffer,0,256);
			String msg = new String(buffer, 0, count);
			txtview1.setText(msg);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void readfile() {
		// 由應用程式資料目錄下的file.txt檔讀取資料
		try {
			FileInputStream fis = openFileInput("file.txt");
			byte[] buffer = new byte[256];
			int count = fis.read(buffer);
			String msg = new String(buffer, 0, count);
			txtview1.setText(msg);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writefile() {
		// 寫入資料到應用程式資料目錄下的file.txt檔
		try {
			FileOutputStream fos = openFileOutput("file.txt", Activity.MODE_PRIVATE);
			String msg = edtext1.getText().toString();
			fos.write(msg.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getViews() {
		// 取得介面元件參照
		btn1 = (Button)findViewById(R.id.button1);
    	btn2 = (Button)findViewById(R.id.button2);
    	btn3 = (Button)findViewById(R.id.button3);
    	btn4 = (Button)findViewById(R.id.button4);
    	btn5 = (Button)findViewById(R.id.button5);
     	txtview1 = (TextView)findViewById(R.id.textView1);
    	edtext1 = (EditText)findViewById(R.id.editText1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
