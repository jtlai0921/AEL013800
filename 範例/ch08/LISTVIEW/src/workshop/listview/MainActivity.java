package workshop.listview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtview = null;
	private ListView lstview = null;
	private Button btn = null;
	private ArrayAdapter<String> lstadapter;
	private OnItemClickListener doSelect = new OnItemClickListener(){
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 將點選項目內容顯示在textView1上
			if (lstview.isItemChecked(arg2)) 
				txtview.setText(arg0.getItemAtPosition(arg2).toString());
			else
				txtview.setText(null);			
		}};
	
	// 將listView1中勾選項目內容顯示在textView1上	
	private OnClickListener showSelect = new OnClickListener(){
		public void onClick(View arg0) {
			String selItems = "";
			for (int i=0; i<lstadapter.getCount(); i++)
				if (lstview.isItemChecked(i)) 
					selItems = selItems + lstadapter.getItem(i).toString() + ", "; 
			txtview.setText(selItems);			
		}};
		

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		createAdapter();   
		getViews();
		lstview.setAdapter(lstadapter);
		lstview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);  
		setListeners();
    }

    private void setListeners() {
		// 設定事件監聽器
		lstview.setOnItemClickListener(doSelect);
		btn.setOnClickListener(showSelect );
	}

	private void getViews() {
		// 取得介面元件參照
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);
		btn = (Button)findViewById(R.id.button1);
	}

	private void createAdapter() {
		// 讀取字串陣列資源檔內容並存入lessons字串陣列
		String[] lessons = getResources().getStringArray(R.array.courses);
		// 以courses[]為資料來源建立ArrayAdapter
		lstadapter = new ArrayAdapter<String> (
			this,
			android.R.layout.simple_list_item_checked, 
			lessons);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
