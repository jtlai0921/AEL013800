package workshop.bmi;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText edheight;
	private EditText edweight;
	private TextView txtcomment;
	private Button btnok;
	private OnClickListener calcBMI = new OnClickListener(){
		public void onClick(View v) {
			double h, w, bmi, stdweight;
			
			// 讀取身高及體重值，並計算bmi值
			h = Double.parseDouble(edheight.getText().toString());
			w = Double.parseDouble(edweight.getText().toString());
			bmi = w * 100 * 100 / (h * h);
			
			// 依據性別計算理想體重值
			RadioButton r = (RadioButton)findViewById(R.id.radioMan);
			if (r.isChecked()==true)
				stdweight = (h-80)* 0.7;
			else
				stdweight =  (h-70)* 0.6;
			
			// 顯示計算結果
			DecimalFormat nf = new DecimalFormat("0.0");
			txtcomment.setText("BMI值是:" + nf.format(bmi)+ "\n" + "理想體重值為:" + nf.format(stdweight));
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        getViews();
        btnok.setOnClickListener(calcBMI );
    }

    private void getViews() {
		// 取得介面元件參照
        edheight = (EditText)findViewById(R.id.editHeight);
        edweight = (EditText)findViewById(R.id.editWeight);
        txtcomment = (TextView)findViewById(R.id.textResult);
        btnok = (Button)findViewById(R.id.btnDo);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
