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
			// ��sImageSwitcher���e
			is.setImageResource(imageIds[arg2]);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}};

	private class ImageAdapter extends BaseAdapter {
		public int getCount() {
			// �Ǧ^����౵�����Ϥ��`��
			return imageIds.length;
		}
		public Object getItem(int arg0) {
			return null;
		}
		public long getItemId(int position) {
			return 0;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			// �إߥΨ���ܦbposition��m�Ϥ���ImageView����A�ó]�w�Ϥ����e
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
		//�إ�ImageAdapter�� ��ë��w��gallery1
		ga.setAdapter(new ImageAdapter());
		
		// �]�wImageSwitcher�Ϥ������ʵe�Ĺ�	
		is.setInAnimation(AnimationUtils.loadAnimation(this,
		            android.R.anim.fade_in));
		is.setOutAnimation(AnimationUtils.loadAnimation(this,
		            android.R.anim.fade_out));
		
		// �إ�ViewFactory�� ��ë��w��imageSwitcher1
		ViewFactory vf = new ViewFactory(){
			public View makeView() {
				// �إߥΨ���ܹϤ���ImageView����A�ó]�w�Ϥ��Ѽ�
				ImageView iv = new ImageView(MainActivity.this);
				iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
				iv.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return iv;			
			}};
		is.setFactory(vf);
		
		// �]�wgallery1��ItemSelected�ƥ��ť��
		ga.setOnItemSelectedListener(gaListener );
    }

    private void getViews() {
		// ���o��������ѷ�
		ga = (Gallery) findViewById(R.id.gallery1);
		is = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
