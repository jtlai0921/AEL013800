package workshop.bars;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private RatingBar rb1 = null, rb2 = null, rb3 = null;
	private SeekBar sb = null;
	private Button btn_inc = null, btn_dec = null;
	private TextView txtview = null;
	private ProgressBar pb = null;       
	private int subprogress = 0;
	private OnRatingBarChangeListener rbListener = new OnRatingBarChangeListener(){
		public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
			// �P�B��sratingBar2��ratingBar3��
			rb2.setRating(arg1);
			rb3.setRating(arg1);
		}};
	
	//�NseekBar1���A��ܦbtextView1����W	
	private OnSeekBarChangeListener sbListener = new OnSeekBarChangeListener(){
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		    txtview.setText("seekBar1���ثe��m�G" + progress);
		}
		public void onStartTrackingTouch(SeekBar seekBar) {
        	txtview.setText("�}�l�վ�seekBar1");
		}
		public void onStopTrackingTouch(SeekBar seekBar) {
        	txtview.setText("����վ�seekBar1");
		}};
		
	private OnClickListener increase =  new OnClickListener(){
		public void onClick(View v) {
			// �NprogressBar2�����i�׭Ȼ��W10
			if (subprogress == 100){
				pb.incrementProgressBy(10);
				subprogress = 10;
				pb.setSecondaryProgress(subprogress);
			}
			else {
				subprogress += 10;
				pb.setSecondaryProgress(subprogress);
			}		
		}};
		
	private OnClickListener decrease= new OnClickListener(){
		public void onClick(View v) {
			// �NprogressBar2�����i�׭Ȼ���10
			if (subprogress==0){
				pb.incrementProgressBy(-10);
				subprogress=90;
				pb.setSecondaryProgress(subprogress);
			}
			else {
				subprogress-=10;
				pb.setSecondaryProgress(subprogress);
			}			
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
    	subprogress = pb.getSecondaryProgress();
    	setListeners();
    }

    private void setListeners() {
		// �]�w�ƥ��ť��
    	rb1.setOnRatingBarChangeListener(rbListener); 
    	sb.setOnSeekBarChangeListener(sbListener);
    	btn_inc.setOnClickListener(increase);
    	btn_dec.setOnClickListener(decrease);
	}

	private void getViews() {
		// ���o��������ѷ�
    	txtview = (TextView)findViewById(R.id.textView1);
    	rb1 = (RatingBar)findViewById(R.id.ratingBar1);
    	rb2 = (RatingBar)findViewById(R.id.ratingBar2);
    	rb3 = (RatingBar)findViewById(R.id.ratingBar3);
    	sb = (SeekBar)findViewById(R.id.seekBar1);
    	btn_inc = (Button)findViewById(R.id.button1);
    	btn_dec = (Button)findViewById(R.id.button2);
    	pb = (ProgressBar) findViewById(R.id.progressBar2);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
