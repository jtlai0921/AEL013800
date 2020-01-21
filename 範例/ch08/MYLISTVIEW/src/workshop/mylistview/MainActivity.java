package workshop.mylistview;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtview = null;
	private ListView lstview = null;
	private String[] lessons = null;
	private String[] comments = null;
	private int[] imgIds = {
			R.drawable.java, R.drawable.oop, 
			R.drawable.android, R.drawable.androidgame};
	private ArrayList<Item> itemList=new ArrayList<Item>();
	private MyListAdapter lstAdapter;
	
	private class MyListAdapter extends BaseAdapter {

		public int getCount() {
			// �Ǧ^����౵��������`��
			return itemList.size();
		}

		public Object getItem(int position) {
			// �Ǧ^��position��m���
			return null;
		}

		public long getItemId(int position) {
			// �Ǧ^��position��m��ƥN�X
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// �̾�mylistitem.xml�����]�p�ɤ��e�غcUI����A�ó]�w���e
			convertView = getLayoutInflater().inflate(R.layout.mylistitem, null);
			ImageView imgLogo = (ImageView) convertView.findViewById(R.id.imageView1);
			TextView txtLesson = (TextView) convertView.findViewById(R.id.textView1);
			TextView txtComment = (TextView) convertView.findViewById(R.id.textView2);
			imgLogo.setImageResource(itemList.get(position).getimgId());
			txtLesson.setText(itemList.get(position).getlesson());
			txtComment.setText(itemList.get(position).getcomment());
			return convertView;
		}
	}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		getViews();
		
		// Ū���r��}�C�귽�ɤ��e�æs�J�r��}�C
		lessons = getResources().getStringArray(R.array.courses);
		comments = getResources().getStringArray(R.array.descriptions);   		
		
		// �إ߲M�涵�ظ�Ʀ�C
		for (int i=0; i<lessons.length; i++){
	        Item item = new Item();
	        item.setimgId(imgIds[i]);
	        item.setlesson(lessons[i]);
	        item.setcomment(comments[i]);
	        itemList.add(item);
		}
		// �إ�MyListAdapter����ó]�w�Ӫ��󰵬�listView1���󤧸���౵��
		lstAdapter = new MyListAdapter();
		lstview.setAdapter(lstAdapter);
		
		// �]�wListView��ItemClick�ƥ��ť��
		lstview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// �NlistView1���Q�I�ﶵ�زĤ@�椺�e��ܦbtextView1�W
				TextView txtLine1 = (TextView) arg1.findViewById(R.id.textView1);
				txtview.setText(txtLine1.getText().toString());								
			}});     
    }

    private void getViews() {
		// ���o��������ѷ�
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
