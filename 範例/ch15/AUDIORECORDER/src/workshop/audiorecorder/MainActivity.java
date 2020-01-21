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
	private ArrayList<String> lstAMRfiles = new ArrayList<String>(); 	//�������ɦW�}�C
	private MediaRecorder mediarecorder = new MediaRecorder();
	private MediaPlayer mediaplayer = new MediaPlayer();
	private int recindex = 0; 		//�ثe�������
	
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
		// �]�w�ާ@�ϥܤ�Click�ƥ��ť��
    	imgStop.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				stop();		//�����/��
			}});
    	imgRecord.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				record();	//�}�l����
			}});
    	imgTurnoff.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();	//�����{��
			}});
    	lstRecord.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				recindex = arg2;
				play();		// �}�l��
			}});
	}
    
	// ���������
	protected void play() {
 		try
 		{
 			mediaplayer.reset();
			mediaplayer.setDataSource(sdcardDir + lstAMRfiles.get(recindex)); 
 			mediaplayer.prepare();
 			mediaplayer.start(); 				//�}�l����
	    	Toast.makeText(MainActivity.this, "�}�l���������", Toast.LENGTH_SHORT).show();
 			setDisable(imgRecord);				//�T�ο������s
 			setEnable(imgStop);					//�ҥΰ�����s
			mediaplayer.setOnCompletionListener(new OnCompletionListener() {
 				public void onCompletion(MediaPlayer arg0) {
 			    	Toast.makeText(MainActivity.this, "�����ɼ��񧹲�", Toast.LENGTH_SHORT).show();
 					setEnable(imgRecord);		//�ҥο������s
 					setDisable(imgStop);		//�T�ΰ�����s
 				}
 			});
  		} catch (IOException e) {}
	}

	protected void stop() {
		// TODO Auto-generated method stub
		if (mediaplayer.isPlaying()) { 		//�����
			mediaplayer.stop();
	    	Toast.makeText(MainActivity.this, "����������", Toast.LENGTH_SHORT).show();
		} else  { 							//�������
    		mediarecorder.stop();
    		showRecordfile();				//��s�����ɲM��
	    	Toast.makeText(MainActivity.this, "��������", Toast.LENGTH_SHORT).show();
    	}
		setEnable(imgRecord);				//�ҥο������s
		setDisable(imgStop);				//�T�ΰ�����s
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
   
	// ��MIC���s���T�A�èϥΥثe�ɶ��W�����������ɦW
    private void record(){
    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MMdd.hhmmss");
    	String filename = sdf.format(calendar.getTime()) + ".amr";		//�զX�ɦW
    	try {
 		    mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
 		    mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
 		    mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);   
        	mediarecorder.setOutputFile(sdcardDir + filename);
			mediarecorder.prepare();
	    	mediarecorder.start();
	    	Toast.makeText(MainActivity.this, "�}�l����", Toast.LENGTH_SHORT).show();
 			setDisable(imgRecord);				//�T�ο������s
 			setEnable(imgStop);					//�ҥΰ�����s
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public void showRecordfile() {
		lstAMRfiles.clear(); 			//�M��lstAMRfiles��C
		//�NSD�O�Хd�����ɦW��.amr���ɦW�[��lstAMRfiles��C�C
		for(File file:Environment.getExternalStorageDirectory().listFiles()) {
			if(file.getName().toLowerCase().endsWith(".amr")) {
				lstAMRfiles.add(file.getName());
			}
		}
		//��s�����ɲM�椺�e
 		ArrayAdapter<String> adpRecord =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstAMRfiles);
 		lstRecord.setAdapter(adpRecord);
 	}
	
 	private void setEnable(ImageView image) { 	//�ϫ��s���ҥΪ��A
 		image.setEnabled(true);
 		image.setAlpha(255);
 	}

 	private void setDisable(ImageView image) { 	//�ϫ��s���T�Ϊ��A
 		image.setEnabled(false);
 		image.setAlpha(50);
 	}
}
