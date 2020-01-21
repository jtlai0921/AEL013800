package workshop.videoplayer;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageView imgLatest, imgStop, imgPlay, imgPause, imgNext, imgTurnoff;
	private ListView lstVideo;
	private TextView txtVideo;
	private SurfaceView sufVideo;
	private MediaPlayer mediaplayer = new MediaPlayer();
	private SurfaceHolder sufHolder;
	String sdcardDir = Environment.getExternalStorageDirectory().toString()+"/";
	String[] videoname;					//影片名稱
	String[] videofile;					//影片檔名
	private int videoindex = 0; 		//目前播放影片索引
	private Boolean flagPause = false; 	//暫停作業旗標
	private OnClickListener mpOperation = new OnClickListener(){
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.imageLatest:  	//上一首, 若已是第一部就移到最後一部
        		videoindex --;
        		if (videoindex < 0) videoindex = videofile.length-1; 
				playVideo();
				break;
			case R.id.imageStop:  		//停止
				mediaplayer.stop(); 
				break;
			case R.id.imagePlay:  		//播放
				if(flagPause) { 		//如果是暫停狀態就繼續播放
					mediaplayer.start(); 
            		flagPause=false;
				}
				else playVideo();
				break;
			case R.id.imagePause:  	//暫停
				if (mediaplayer.isPlaying()) {
            		mediaplayer.pause();
            		flagPause=true;
				}
				break;
			case R.id.imageNext:  		//下一部, 若已是最後一部就移到第一部
				videoindex ++;
				if (videoindex >= videofile.length) videoindex = 0;
				playVideo();
				break;
			case R.id.imageTurnoff:  	//結束撥放程式
				finish();
				break;
			}
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		videoname = getResources().getStringArray(R.array.videoname);
		videofile = getResources().getStringArray(R.array.videofile);
		getViews();
		
		// 在listView1元件上顯示影片清單
		ArrayAdapter<String> adpVideo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, videoname);
		lstVideo.setAdapter(adpVideo);
		
		//將SurfaceView元件交付MediaPlayer做為顯示介面
		sufHolder=sufVideo.getHolder();
		sufHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mediaplayer.setDisplay(sufHolder);
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		setListeners();
    }

    private void setListeners() {
		// 設定操作圖示之Click事件監聽器
    	imgLatest.setOnClickListener(mpOperation );
    	imgStop.setOnClickListener(mpOperation);
    	imgPlay.setOnClickListener(mpOperation);
    	imgPause.setOnClickListener(mpOperation);
    	imgNext.setOnClickListener(mpOperation);
    	imgTurnoff.setOnClickListener(mpOperation);
    	lstVideo.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				videoindex = arg2; 					//取得點選位置
				playVideo(); 						//播放點選影片 			
			}});
		mediaplayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				videoindex ++;
				if (videoindex >= videofile.length) videoindex = 0;
				playVideo();
			}});
	}

	private void getViews() {
		// 取得介面元件參照
		imgLatest=(ImageView)findViewById(R.id.imageLatest); 
		imgStop=(ImageView)findViewById(R.id.imageStop);
		imgPlay=(ImageView)findViewById(R.id.imagePlay); 
		imgPause=(ImageView)findViewById(R.id.imagePause);
		imgNext=(ImageView)findViewById(R.id.imageNext); 
		imgTurnoff=(ImageView)findViewById(R.id.imageTurnoff); 
		lstVideo=(ListView)findViewById(R.id.listView1); 
		txtVideo=(TextView)findViewById(R.id.textView1); 
		sufVideo = (SurfaceView) findViewById(R.id.surfaceView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	// 使用MediaPlayer物件撥放videofile[videoindex]影片檔
	private void playVideo() {
		try
 		{
			String file = sdcardDir  + videofile[videoindex];
 			mediaplayer.reset();
  			mediaplayer.setDataSource(file); 					//播放歌曲路徑
 			mediaplayer.prepare();
			txtVideo.setText("片名：" + videoname[videoindex]); 	//更新歌名
			mediaplayer.start(); 								//開始播放
 		} catch (IOException e) {}
 	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
    	mediaplayer.release();
	}
}
