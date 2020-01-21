package workshop.musicplayer;

import java.io.IOException;

import android.media.MediaPlayer;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageView imgLatest, imgStop, imgPlay, imgPause, imgNext, imgTurnoff;
	private ListView lstMusic;
	private TextView txtMusic;
	private MediaPlayer mediaplayer = new MediaPlayer();
	String sdcardDir  = Environment.getExternalStorageDirectory().toString()+"/";
	String[] musicname;					//音樂名稱
	String[] musicfile;					//音樂檔名
	private int songindex = 0; 			//目前播放音樂索引
	private Boolean flagPause = false; 	//暫停作業旗標
	
	private OnClickListener mpOperation = new OnClickListener(){
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
            case R.id.imageLatest:  	//上一首, 若已是第一首就移到最後一首
        		songindex --;
        		if (songindex < 0) songindex = musicfile.length-1; 
				playSong();
				break;
			case R.id.imageStop:  		//停止
				mediaplayer.stop(); 
              	break;
			case R.id.imagePlay:  		//播放
            	if(flagPause) { 		//如果是暫停狀態就繼續播放
					mediaplayer.start(); 
            		flagPause=false;
            	}
            	else playSong();
               	break;
            case R.id.imagePause:  		//暫停
            	if (mediaplayer.isPlaying()) {
            		mediaplayer.pause();
            		flagPause=true;
            	}
            	break;
            case R.id.imageNext:  		//下一首, 若已是最後一首就移到第一首
				songindex ++;
				if (songindex >= musicfile.length) songindex = 0;
				playSong();
              	break;
            case R.id.imageTurnoff:  	//結束撥放程式
            	mediaplayer.release();
            	finish();
              	break;
			}
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicname = getResources().getStringArray(R.array.musicname);
        musicfile = getResources().getStringArray(R.array.musicfile);
        getViews();
        
        // 顯示歌曲清單
        ArrayAdapter<String> adpSong=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicname);
		lstMusic.setAdapter(adpSong);
        
		setListeners();
    }

    private void setListeners() {
		// 設定操作圖示之事件監聽器
    	imgLatest.setOnClickListener(mpOperation);
    	imgStop.setOnClickListener(mpOperation);
    	imgPlay.setOnClickListener(mpOperation);
    	imgPause.setOnClickListener(mpOperation);
    	imgNext.setOnClickListener(mpOperation);
    	imgTurnoff.setOnClickListener(mpOperation);
    	lstMusic.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				songindex = arg2; 					//取得點選位置
				playSong(); 						//播放點選歌曲 
			}});
		mediaplayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				songindex ++;
				if (songindex >= musicfile.length) songindex = 0; //若到最後就移到第一首 
				playSong();
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
		lstMusic=(ListView)findViewById(R.id.listView1); 
		txtMusic=(TextView)findViewById(R.id.textView1); 
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
    	mediaplayer.release();
	}
	
	private void playSong() {
		try
 		{
			String file = sdcardDir  + musicfile[songindex];
 			mediaplayer.reset();
  			mediaplayer.setDataSource(file); 					//播放歌曲路徑
 			mediaplayer.prepare();
			txtMusic.setText("歌名：" + musicname[songindex]); 	//更新歌名
			mediaplayer.start(); 								//開始播放
 		} catch (IOException e) {}
 	}
}
