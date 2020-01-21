package workshop.gallery;

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

	private class ImageAdapter extends BaseAdapter {
		public int getCount() {
			// 傳回資料轉接器之圖片總數
			return imageIds.length;
		}
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			// 建立用來顯示在position位置圖片之ImageView物件，並設定圖片內容
			ImageView iv;
			if (convertView == null) {
				iv = new ImageView(parent.getContext());
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
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

        // 取得介面元件參照
        Gallery ga = (Gallery) findViewById(R.id.gallery1);
		
		//建立資料轉接器，並指定給Gallery
		ga.setAdapter(new ImageAdapter());
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
