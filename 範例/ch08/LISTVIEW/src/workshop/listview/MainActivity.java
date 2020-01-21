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
			// �N�I�ﶵ�ؤ��e��ܦbtextView1�W
			if (lstview.isItemChecked(arg2)) 
				txtview.setText(arg0.getItemAtPosition(arg2).toString());
			else
				txtview.setText(null);			
		}};
	
	// �NlistView1���Ŀﶵ�ؤ��e��ܦbtextView1�W	
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
		// �]�w�ƥ��ť��
		lstview.setOnItemClickListener(doSelect);
		btn.setOnClickListener(showSelect );
	}

	private void getViews() {
		// ���o��������ѷ�
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);
		btn = (Button)findViewById(R.id.button1);
	}

	private void createAdapter() {
		// Ū���r��}�C�귽�ɤ��e�æs�Jlessons�r��}�C
		String[] lessons = getResources().getStringArray(R.array.courses);
		// �Hcourses[]����ƨӷ��إ�ArrayAdapter
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
