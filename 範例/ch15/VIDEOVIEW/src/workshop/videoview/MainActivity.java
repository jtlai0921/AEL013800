package workshop.videoview;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioGroup;
import android.widget.VideoView;

public class MainActivity extends Activity {
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 停止撥放
		video.stopPlayback();
	}

	private RadioGroup rg;
	private Button btn;
	private VideoView video;
	String sdcardDir = Environment.getExternalStorageDirectory().toString();
	String videofile;	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        video.setMediaController(new MediaController(MainActivity.this));
        btn.setOnClickListener(new OnClickListener(){
			// 撥放radioGroup1被點選的影片
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (rg.getCheckedRadioButtonId()){
				case R.id.radio0:
					 videofile = sdcardDir + "/scrat_1.mp4";
					break;
				case R.id.radio1:
					 videofile = sdcardDir + "/scrat_2.mp4";
					break;
				case R.id.radio2:
					 videofile = sdcardDir + "/scrat_3.mp4";
					break;
				}
				video.setVideoPath(videofile); 	//設定影片路徑
				video.start(); 					//開始播放	
			}});
    }

    private void getViews() {
		// 取得介面元件參照
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		btn = (Button) findViewById(R.id.button1);
		video = (VideoView) findViewById(R.id.videoView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
