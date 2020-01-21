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
	String[] videoname;					//�v���W��
	String[] videofile;					//�v���ɦW
	private int videoindex = 0; 		//�ثe����v������
	private Boolean flagPause = false; 	//�Ȱ��@�~�X��
	private OnClickListener mpOperation = new OnClickListener(){
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.imageLatest:  	//�W�@��, �Y�w�O�Ĥ@���N����̫�@��
        		videoindex --;
        		if (videoindex < 0) videoindex = videofile.length-1; 
				playVideo();
				break;
			case R.id.imageStop:  		//����
				mediaplayer.stop(); 
				break;
			case R.id.imagePlay:  		//����
				if(flagPause) { 		//�p�G�O�Ȱ����A�N�~�򼽩�
					mediaplayer.start(); 
            		flagPause=false;
				}
				else playVideo();
				break;
			case R.id.imagePause:  	//�Ȱ�
				if (mediaplayer.isPlaying()) {
            		mediaplayer.pause();
            		flagPause=true;
				}
				break;
			case R.id.imageNext:  		//�U�@��, �Y�w�O�̫�@���N����Ĥ@��
				videoindex ++;
				if (videoindex >= videofile.length) videoindex = 0;
				playVideo();
				break;
			case R.id.imageTurnoff:  	//��������{��
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
		
		// �blistView1����W��ܼv���M��
		ArrayAdapter<String> adpVideo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, videoname);
		lstVideo.setAdapter(adpVideo);
		
		//�NSurfaceView�����IMediaPlayer������ܤ���
		sufHolder=sufVideo.getHolder();
		sufHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mediaplayer.setDisplay(sufHolder);
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		setListeners();
    }

    private void setListeners() {
		// �]�w�ާ@�ϥܤ�Click�ƥ��ť��
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
				videoindex = arg2; 					//���o�I���m
				playVideo(); 						//�����I��v�� 			
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
		// ���o��������ѷ�
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
    
	// �ϥ�MediaPlayer���󼷩�videofile[videoindex]�v����
	private void playVideo() {
		try
 		{
			String file = sdcardDir  + videofile[videoindex];
 			mediaplayer.reset();
  			mediaplayer.setDataSource(file); 					//����q�����|
 			mediaplayer.prepare();
			txtVideo.setText("���W�G" + videoname[videoindex]); 	//��s�q�W
			mediaplayer.start(); 								//�}�l����
 		} catch (IOException e) {}
 	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
    	mediaplayer.release();
	}
}
