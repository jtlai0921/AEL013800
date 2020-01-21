package workshop.listview_ii;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtview = null;
	private ListView lstview = null;
	private SimpleAdapter lstadapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		createAdapter();     
		getViews();
		lstview.setAdapter(lstadapter);  
		lstview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// �N�I�ﶵ�زĤ@�椺�e��ܦbtextView1�W
				TextView txtLine1 = (TextView) arg1.findViewById(android.R.id.text1);
				txtview.setText(txtLine1.getText().toString());				
			}});      
    }

    private void getViews() {
		// ���o��������ѷ�
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);
	}

	private void createAdapter() {
		// Ū���r��}�C�귽�ɤ��e�æs�J�r��}�C
		String[] lessons = getResources().getStringArray(R.array.courses);
		String[] comments = getResources().getStringArray(R.array.descriptions);
		ArrayList<HashMap<String,String>> items = new ArrayList<HashMap<String, String>>();
       // ��lessons��comments���e�ʸˬ�HashMap�A�A�NHashMap���إ[�JArrayList��
		for (int i =0; i<lessons.length; i++){
			HashMap<String, String> item = new HashMap<String, String>(); 
			item.put("lesson", lessons[i]);
			item.put("comment", comments[i]);
			items.add(item);
		}
		// �HArrayList����ƨӷ��إ�SimpleAdapter
		lstadapter = new SimpleAdapter(
			this,
			items,										//��ƨӷ�
			android.R.layout.simple_list_item_2,
			new String[] {"lesson", "comment"},			//��ƶ��ت�key��
			new int[] {android.R.id.text1, android.R.id.text2});
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
