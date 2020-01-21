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
			
			// Ū���������魫�ȡA�íp��bmi��
			h = Double.parseDouble(edheight.getText().toString());
			w = Double.parseDouble(edweight.getText().toString());
			bmi = w * 100 * 100 / (h * h);
			
			// �̾کʧO�p��z�Q�魫��
			RadioButton r = (RadioButton)findViewById(R.id.radioMan);
			if (r.isChecked()==true)
				stdweight = (h-80)* 0.7;
			else
				stdweight =  (h-70)* 0.6;
			
			// ��ܭp�⵲�G
			DecimalFormat nf = new DecimalFormat("0.0");
			txtcomment.setText("BMI�ȬO:" + nf.format(bmi)+ "\n" + "�z�Q�魫�Ȭ�:" + nf.format(stdweight));
		}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        getViews();
        btnok.setOnClickListener(calcBMI );
    }

    private void getViews() {
		// ���o��������ѷ�
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
