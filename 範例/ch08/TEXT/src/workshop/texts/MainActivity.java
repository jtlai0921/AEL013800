package workshop.texts;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtview = null;
	private AutoCompleteTextView actext = null;
	private MultiAutoCompleteTextView mactext = null;
	private Spinner sptext = null;
	private ArrayAdapter<String> adp1;
	private ArrayAdapter<CharSequence> adp2;
	
	// ��@ItemSelected�ƥ��ť��
	private OnItemSelectedListener spinnerListener = new OnItemSelectedListener(){
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// ���spinner1������ؤ��e
			String course = arg0.getItemAtPosition(arg2).toString();
			txtview.setText("�A�ҿ諸�ҵ{�O-" + course);		
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	getViews();
    	createAdapter();
    	setAdapters();
    	sptext.setOnItemSelectedListener(spinnerListener );
    }

    private void createAdapter() {
    	String[] lessons = getResources().getStringArray(R.array.courses);
    	
    	// �ϥΦr��}�C�إ�ArrayAdapter
    	adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lessons);
    	
    	// �ϥΦr��}�C�귽�إ�ArrayAdapter
    	adp2 = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);		
	}

	private void setAdapters() {
		// ���w����౵��
    	actext.setAdapter(adp1);
    	mactext.setAdapter(adp1);
    	mactext.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    	adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  	
    	sptext.setAdapter(adp2);   	
	}

	private void getViews() {
		// ���o��������ѷ�
    	txtview = (TextView)findViewById(R.id.textView1);
    	actext = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
    	mactext = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
    	sptext = (Spinner)findViewById(R.id.spinner1);  	
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
