package workshop.gridview;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private GridView gv = null;
	private ImageView iv = null;
	private OnItemClickListener gvListener = new OnItemClickListener(){
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 更新ImageView內容
			iv.setImageResource(imageIds[arg2]);
		}};;

	private class ImageAdapter extends BaseAdapter {
		public int getCount() {
			// 傳回資料轉接器的圖片總數
			return imageIds.length;
		}
		public Object getItem(int arg0) {
			return null;
		}
		public long getItemId(int arg0) {
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			// 建立用來顯示在position位置圖片之ImageView物件，並設定圖片內容
			ImageView iv;
			if  (convertView == null)
			{
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);
				int w = dm.widthPixels / 3 - 20;
				int h = (int) (w * 0.75);
				iv = new ImageView(parent.getContext());
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.setLayoutParams(new GridView.LayoutParams(w,h));
				iv.setImageResource(imageIds[position]);
			}
			else {
				iv = (ImageView) convertView;
			}
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
        gv.setAdapter(new ImageAdapter());  
        // 設定gallery1之ItemSelected事件監聽器
		gv.setOnItemClickListener(gvListener );
    }

    private void getViews() {
		// 取得介面元件參照
        gv = (GridView) findViewById(R.id.gridView1);
        iv = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
