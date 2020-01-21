package workshop.imageswitcher;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;


public class MainActivity extends Activity {
	private Gallery ga = null;
	private ImageSwitcher is = null;
	
	private OnItemSelectedListener gaListener = new OnItemSelectedListener(){
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 更新ImageSwitcher內容
			is.setImageResource(imageIds[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}};

	private class ImageAdapter extends BaseAdapter {
		public int getCount() {
			// 傳回資料轉接器的圖片總數
			return imageIds.length;
		}
		public Object getItem(int arg0) {
			return null;
		}
		public long getItemId(int position) {
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			// 建立用來顯示在position位置圖片之ImageView物件，並設定圖片內容
			ImageView iv;
			if (convertView == null) {
				iv = new ImageView(parent.getContext());
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.setLayoutParams(new Gallery.LayoutParams(100, 64));
				iv.setBackgroundColor(color.black);
				iv.setImageResource(imageIds[position]);
			}
			else
				iv = (ImageView) convertView;
			return iv;		
		}
	}

	private static final int[] imageIds =  {
			R.drawable.nk1, R.drawable.nk2, R.drawable.nk3, 
			R.drawable.nk4, R.drawable.nk5, R.drawable.nk6,
			R.drawable.nk7, R.drawable.nk8, R.drawable.nk9
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		getViews();
		//建立ImageAdapter實 體並指定給gallery1
		ga.setAdapter(new ImageAdapter());
		
		// 設定ImageSwitcher圖片切換動畫效圖	
		is.setInAnimation(AnimationUtils.loadAnimation(this,
		            android.R.anim.fade_in));
		is.setOutAnimation(AnimationUtils.loadAnimation(this,
		            android.R.anim.fade_out));
		
		// 建立ViewFactory實 體並指定給imageSwitcher1
		ViewFactory vf = new ViewFactory(){
			public View makeView() {
				// 建立用來顯示圖片之ImageView物件，並設定圖片參數
				ImageView iv = new ImageView(MainActivity.this);
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return iv;			
			}};
		is.setFactory(vf);
		
		// 設定gallery1之ItemSelected事件監聽器
		ga.setOnItemSelectedListener(gaListener );
    }

    private void getViews() {
		// 取得介面元件參照
		ga = (Gallery) findViewById(R.id.gallery1);
		is = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
