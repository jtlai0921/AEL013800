package workshop.audiorecorder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView imgRecord, imgStop, imgTurnoff;
	private ListView lstRecord;
	String sdcardDir = Environment.getExternalStorageDirectory().toString()+"/";
	private ArrayList<String> lstAMRfiles = new ArrayList<String>(); 	//錄音檔檔名陣列
	private MediaRecorder mediarecorder = new MediaRecorder();
	private MediaPlayer mediaplayer = new MediaPlayer();
	private int recindex = 0; 		//目前播放錄音
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        setListeners();
        mediarecorder.reset();
        showRecordfile();
        setDisable(imgStop);
    }

    private void setListeners() {
		// 設定操作圖示之Click事件監聽器
    	imgStop.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				stop();		//停止錄/放音
			}});
    	imgRecord.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				record();	//開始錄音
			}});
    	imgTurnoff.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();	//結束程式
			}});
    	lstRecord.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				recindex = arg2;
				play();		// 開始放音
			}});
	}
    
	// 撥放錄音檔
	protected void play() {
 		try
 		{
 			mediaplayer.reset();
			mediaplayer.setDataSource(sdcardDir + lstAMRfiles.get(recindex)); 
 			mediaplayer.prepare();
 			mediaplayer.start(); 				//開始播放
	    	Toast.makeText(MainActivity.this, "開始撥放錄音檔", Toast.LENGTH_SHORT).show();
 			setDisable(imgRecord);				//禁用錄音按鈕
 			setEnable(imgStop);					//啟用停止按鈕
			mediaplayer.setOnCompletionListener(new OnCompletionListener() {
 				public void onCompletion(MediaPlayer arg0) {
 			    	Toast.makeText(MainActivity.this, "錄音檔撥放完畢", Toast.LENGTH_SHORT).show();
 					setEnable(imgRecord);		//啟用錄音按鈕
 					setDisable(imgStop);		//禁用停止按鈕
 				}
 			});
  		} catch (IOException e) {}
	}

	protected void stop() {
		// TODO Auto-generated method stub
		if (mediaplayer.isPlaying()) { 		//停止放音
			mediaplayer.stop();
	    	Toast.makeText(MainActivity.this, "中止撥放錄音檔", Toast.LENGTH_SHORT).show();
		} else  { 							//停止錄音
    		mediarecorder.stop();
    		showRecordfile();				//更新錄音檔清單
	    	Toast.makeText(MainActivity.this, "結束錄音", Toast.LENGTH_SHORT).show();
    	}
		setEnable(imgRecord);				//啟用錄音按鈕
		setDisable(imgStop);				//禁用停止按鈕
	}

	private void getViews() {
		// TODO Auto-generated method stub
		imgStop=(ImageView)findViewById(R.id.imageStop);
		imgRecord=(ImageView)findViewById(R.id.imageRecord);
		imgTurnoff=(ImageView)findViewById(R.id.imageTurnoff); 
		lstRecord=(ListView)findViewById(R.id.listView1); 
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
   
	// 由MIC錄製音訊，並使用目前時間戳章為錄音檔檔名
    private void record(){
    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MMdd.hhmmss");
    	String filename = sdf.format(calendar.getTime()) + ".amr";		//組合檔名
    	try {
 		    mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
 		    mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
 		    mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);   
        	mediarecorder.setOutputFile(sdcardDir + filename);
			mediarecorder.prepare();
	    	mediarecorder.start();
	    	Toast.makeText(MainActivity.this, "開始錄音", Toast.LENGTH_SHORT).show();
 			setDisable(imgRecord);				//禁用錄音按鈕
 			setEnable(imgStop);					//啟用停止按鈕
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public void showRecordfile() {
		lstAMRfiles.clear(); 			//清空lstAMRfiles串列
		//將SD記憶卡內附檔名為.amr之檔名加到lstAMRfiles串列。
		for(File file:Environment.getExternalStorageDirectory().listFiles()) {
			if(file.getName().toLowerCase().endsWith(".amr")) {
				lstAMRfiles.add(file.getName());
			}
		}
		//更新錄音檔清單內容
 		ArrayAdapter<String> adpRecord =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstAMRfiles);
 		lstRecord.setAdapter(adpRecord);
 	}
	
 	private void setEnable(ImageView image) { 	//使按鈕為啟用狀態
 		image.setEnabled(true);
 		image.setAlpha(255);
 	}

 	private void setDisable(ImageView image) { 	//使按鈕為禁用狀態
 		image.setEnabled(false);
 		image.setAlpha(50);
 	}
}
