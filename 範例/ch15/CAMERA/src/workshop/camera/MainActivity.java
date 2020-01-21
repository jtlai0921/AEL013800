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
	private SurfaceView surfaceview;							//顯示照相預覽畫面  
	private SurfaceHolder surfaceHolder;  
	private ImageView imgshot;
	private String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			setEnable(imgshot);									//啟用拍照按鈕
		}};	

	// 完成自動對焦
	private Camera.AutoFocusCallback mAutoFocusListener = new Camera.AutoFocusCallback() {    
	    public void onAutoFocus(boolean success, final Camera camera) {  
	      camera.takePicture(null, null, mPictureListener);  	//進行拍照
	      new Thread() {
				@Override
				public void run() {							//拍照畫面停留2秒
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mCamera.startPreview();					//2秒時間終了，回復預覽畫面
					handler.sendMessage(new Message());		//通知主線程
				}
			}.start(); 
	    }  
	};   
	 
	//完成拍照  
	private Camera.PictureCallback mPictureListener = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
	    	FileOutputStream fos = null;					
			try {				//將照片存檔
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
		mCamera.setDisplayOrientation(90);				//修正鏡頭預覽畫面方向
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//鎖定操作介面為直向
		surfaceHolder = surfaceview.getHolder();  
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
		surfaceHolder.addCallback(mSurfaceListener);  
		imgshot.setOnClickListener(new OnClickListener(){	//照相按鈕之Click事件監聽器
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDisable(imgshot);						//禁用拍照按鈕	
				if(mCamera != null)							//開始自動對焦拍照 
					mCamera.autoFocus(mAutoFocusListener);
			}});
	}
	  
	private void getViews() {
		// 取得介面元件參照
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
		    mCamera.release();  		//釋放相機資源
		    mCamera = null;  
		}
	}; 
 
	protected void initCamera() {
		// TODO Auto-generated method stub
		Camera.Parameters parameters = mCamera.getParameters();  
		parameters.setPictureFormat(PixelFormat.JPEG);	//照片格式
		parameters.setJpegQuality(85);					//照片壓縮品質
		parameters.setRotation(90);						//直向照片
		mCamera.setParameters(parameters);  
		mCamera.startPreview();  						//開啟鏡頭預覽
	}

	private void setEnable(ImageView image) { 			//使ImageView為啟用狀態
 		image.setEnabled(true);
 		image.setAlpha(255);
 	}
	private void setDisable(ImageView image) { 		//使ImageView為禁用狀態
 		image.setEnabled(false);
 		image.setAlpha(50);
 	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
