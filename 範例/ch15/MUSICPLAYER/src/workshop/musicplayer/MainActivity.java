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
	String[] musicname;					//���֦W��
	String[] musicfile;					//�����ɦW
	private int songindex = 0; 			//�ثe���񭵼֯���
	private Boolean flagPause = false; 	//�Ȱ��@�~�X��
	
	private OnClickListener mpOperation = new OnClickListener(){
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
            case R.id.imageLatest:  	//�W�@��, �Y�w�O�Ĥ@���N����̫�@��
        		songindex --;
        		if (songindex < 0) songindex = musicfile.length-1; 
				playSong();
				break;
			case R.id.imageStop:  		//����
				mediaplayer.stop(); 
              	break;
			case R.id.imagePlay:  		//����
            	if(flagPause) { 		//�p�G�O�Ȱ����A�N�~�򼽩�
					mediaplayer.start(); 
            		flagPause=false;
            	}
            	else playSong();
               	break;
            case R.id.imagePause:  		//�Ȱ�
            	if (mediaplayer.isPlaying()) {
            		mediaplayer.pause();
            		flagPause=true;
            	}
            	break;
            case R.id.imageNext:  		//�U�@��, �Y�w�O�̫�@���N����Ĥ@��
				songindex ++;
				if (songindex >= musicfile.length) songindex = 0;
				playSong();
              	break;
            case R.id.imageTurnoff:  	//��������{��
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
        
        // ��ܺq���M��
        ArrayAdapter<String> adpSong=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicname);
		lstMusic.setAdapter(adpSong);
        
		setListeners();
    }

    private void setListeners() {
		// �]�w�ާ@�ϥܤ��ƥ��ť��
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
				songindex = arg2; 					//���o�I���m
				playSong(); 						//�����I��q�� 
			}});
		mediaplayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				songindex ++;
				if (songindex >= musicfile.length) songindex = 0; //�Y��̫�N����Ĥ@�� 
				playSong();
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
  			mediaplayer.setDataSource(file); 					//����q�����|
 			mediaplayer.prepare();
			txtMusic.setText("�q�W�G" + musicname[songindex]); 	//��s�q�W
			mediaplayer.start(); 								//�}�l����
 		} catch (IOException e) {}
 	}
}
