package workshop.camera;

import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Camera mCamera;  
	private SurfaceView surfaceview;							//��ܷӬ۹w���e��  
	private SurfaceHolder surfaceHolder;  
	private ImageView imgshot;
	private String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			setEnable(imgshot);									//�ҥΩ�ӫ��s
		}};	

	// �����۰ʹ�J
	private Camera.AutoFocusCallback mAutoFocusListener = new Camera.AutoFocusCallback() {    
	    public void onAutoFocus(boolean success, final Camera camera) {  
	      camera.takePicture(null, null, mPictureListener);  	//�i����
	      new Thread() {
				@Override
				public void run() {							//��ӵe�����d2��
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mCamera.startPreview();					//2��ɶ��פF�A�^�_�w���e��
					handler.sendMessage(new Message());		//�q���D�u�{
				}
			}.start(); 
	    }  
	};   
	 
	//�������  
	private Camera.PictureCallback mPictureListener = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
	    	FileOutputStream fos = null;					
			try {				//�N�Ӥ��s��
				fos = new FileOutputStream( sdCard + "/lab15-6.jpg");
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}     
	};  

	// Called when the activity is first created.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
		getViews();
		mCamera = Camera.open();  
		mCamera.setDisplayOrientation(90);				//�ץ����Y�w���e����V
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//��w�ާ@���������V
		surfaceHolder = surfaceview.getHolder();  
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
		surfaceHolder.addCallback(mSurfaceListener);  
		imgshot.setOnClickListener(new OnClickListener(){	//�Ӭ۫��s��Click�ƥ��ť��
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDisable(imgshot);						//�T�Ω�ӫ��s	
				if(mCamera != null)							//�}�l�۰ʹ�J��� 
					mCamera.autoFocus(mAutoFocusListener);
			}});
	}
	  
	private void getViews() {
		// ���o��������ѷ�
		surfaceview = (SurfaceView)findViewById(R.id.surfaceView1); 
		imgshot = (ImageView)findViewById(R.id.imageView1);  
	}
	
	private SurfaceHolder.Callback mSurfaceListener =  new SurfaceHolder.Callback() {
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
			// TODO Auto-generated method stub	
		}

		public void surfaceCreated(SurfaceHolder holder) {	
			try {  
				mCamera.setPreviewDisplay(holder);  
				initCamera();	
			}  
			catch (Exception e) {  
				e.printStackTrace();  
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
		    mCamera.release();  		//����۾��귽
		    mCamera = null;  
		}
	}; 
 
	protected void initCamera() {
		// TODO Auto-generated method stub
		Camera.Parameters parameters = mCamera.getParameters();  
		parameters.setPictureFormat(PixelFormat.JPEG);	//�Ӥ��榡
		parameters.setJpegQuality(85);					//�Ӥ����Y�~��
		parameters.setRotation(90);						//���V�Ӥ�
		mCamera.setParameters(parameters);  
		mCamera.startPreview();  						//�}�����Y�w��
	}

	private void setEnable(ImageView image) { 			//��ImageView���ҥΪ��A
 		image.setEnabled(true);
 		image.setAlpha(255);
 	}
	private void setDisable(ImageView image) { 		//��ImageView���T�Ϊ��A
 		image.setEnabled(false);
 		image.setAlpha(50);
 	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
