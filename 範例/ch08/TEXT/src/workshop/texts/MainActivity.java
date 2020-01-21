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
	
	// 實作ItemSelected事件監聽器
	private OnItemSelectedListener spinnerListener = new OnItemSelectedListener(){
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 顯示spinner1選取項目內容
			String course = arg0.getItemAtPosition(arg2).toString();
			txtview.setText("你所選的課程是-" + course);		
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
    	
    	// 使用字串陣列建立ArrayAdapter
    	adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lessons);
    	
    	// 使用字串陣列資源建立ArrayAdapter
    	adp2 = ArrayAdapter.createFromResource(this, R.array.courses, android.R.layout.simple_spinner_item);		
	}

	private void setAdapters() {
		// 指定資料轉接器
    	actext.setAdapter(adp1);
    	mactext.setAdapter(adp1);
    	mactext.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    	adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  	
    	sptext.setAdapter(adp2);   	
	}

	private void getViews() {
		// 取得介面元件參照
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
