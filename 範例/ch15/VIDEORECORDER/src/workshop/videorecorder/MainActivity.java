package workshop.videorecorder;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imgRecord, imgStop, imgPlay, imgTurnoff;	   
    private MediaRecorder mediarecorder = new MediaRecorder(); 	
    private MediaPlayer mediaplayer = new MediaPlayer();
    private SurfaceView surfaceview;							//顯示視頻介面  
    private SurfaceHolder surfaceHolder;  
    
    private SurfaceHolder.Callback mSurfaceListener = new SurfaceHolder.Callback() {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
		}

		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
	        surfaceHolder = null;  
	        surfaceview = null;  
		}};
		
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getViews();
        setListeners();
        surfaceHolder = surfaceview.getHolder();		//取得SurfaceHolder  
        surfaceHolder.addCallback(mSurfaceListener); 	//surfaceHolder加入回呼介面  
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
		mediaplayer.setDisplay(surfaceHolder);
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		setDisable(imgStop);							//禁用停止按鈕
    }

	// 設定操作圖示之Click事件監聽器
    private void setListeners() {
        imgRecord.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				record();					// 開始錄影
			}});  
        imgStop.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				stop();						// 停止錄影或放影	
			}});  
        imgPlay.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
	            play();						// 開始放影
			}});
        imgTurnoff.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
	            mediarecorder.release();
	        	mediaplayer.release();
				finish();					// 結束程式
			}});
		mediaplayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				setDisable(imgStop);				//禁用停止按鈕	
				setEnable(imgRecord);				//啟用錄影按鈕
			}});
	}

	private void getViews() {
		// 取得介面元件參照
    	imgRecord = (ImageView) findViewById(R.id.imageRecord);  
    	imgStop = (ImageView) this.findViewById(R.id.imageStop);  
    	imgPlay = (ImageView) this.findViewById(R.id.imagePlay);  
    	imgTurnoff = (ImageView) this.findViewById(R.id.imageTurnoff);  
        surfaceview = (SurfaceView) this.findViewById(R.id.surfaceView1); 
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
       
    private void stop(){
    	if (mediaplayer.isPlaying())
    		mediaplayer.stop();				//停止放影
    	else
            mediarecorder.stop();  			//停止錄影 
		setEnable(imgRecord);				//啟用錄影按鈕
		setEnable(imgPlay);					//啟用放影按鈕		
		setDisable(imgStop);				//禁用停止按鈕
    }
    
    private void play() {
		mediaplayer.reset();
		try {
			mediaplayer.setDataSource("/sdcard/lab15-5.mp4");	//播放影片路徑
			mediaplayer.prepare();	
			mediaplayer.start(); 				//開始播放
    		setEnable(imgStop);					//啟用停止按鈕
    		setDisable(imgRecord);				//禁用錄影按鈕
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    }
    
    private void record(){ 
        mediarecorder.reset();
        mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);  	//設定以相機為視訊來源 	
        mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);	//採用mp4檔案封裝格式     
        mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);  	//採用h264編碼 
        mediarecorder.setVideoSize(320, 240);  								//解析度320*240 
        mediarecorder.setVideoFrameRate(20);  								//每秒20影格
        mediarecorder.setPreviewDisplay(surfaceHolder.getSurface());  
        mediarecorder.setOutputFile("/sdcard/lab15-5.mp4");  
        try {   
            mediarecorder.prepare();  			//準備錄製 
            mediarecorder.start();				// 開始錄製 
    		setEnable(imgStop);					//啟用停止按鈕
    		setDisable(imgPlay);				//禁用撥放按鈕
     
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }   
    }
    
	private void setEnable(ImageView image) { 	//使ImageView為啟用狀態
 		image.setEnabled(true);
 		image.setAlpha(255);
 	}
	private void setDisable(ImageView image) { 	//使ImageView為禁用狀態
 		image.setEnabled(false);
 		image.setAlpha(50);
 	}

}
