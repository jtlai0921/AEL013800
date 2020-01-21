package workshop.mylistview;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
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
	private MyListAdapter lstAdapter;
	private ArrayList<Item> itemList=new ArrayList<Item>();
	private ArrayList<Item> orginal_itemList = new ArrayList<Item>();
	private EditText edtext = null;

	private class MyListAdapter extends BaseAdapter implements Filterable{

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

		public Filter getFilter() {
			// �إߤ@��Filter����æ^��
			return new Filter(){
				@Override
				protected FilterResults performFiltering(CharSequence arg0) {
					// TODO Auto-generated method stub
					FilterResults results = new FilterResults();
					ArrayList<Item> filtered_itemList = new ArrayList<Item>();
					if (arg0!= null && arg0.toString().length() > 0) {
						// �v������ƶ����e�O�ŦX�L�o�r��A�ñN�ŦX���إ[��filtered_itemList
						for (int index = 0; index < orginal_itemList.size(); index++) {
							Item item = orginal_itemList.get(index);
							//�N�ŦX���إ[��filtered_itemList
							if (item.getlesson().contains(arg0.toString())) filtered_itemList.add(item);
						}
						results.values = filtered_itemList;	//�ϥιL�o��M�椺�e
						results.count = filtered_itemList.size();                   
					}
					else{
						results.values = orginal_itemList;	//�ϥέ�l�M�椺�e	
						results.count = orginal_itemList.size();
					}
					return results;
				}
				
				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					// ��s�M���ƨóq����s	
					itemList = (ArrayList<Item>) results.values;
					MyListAdapter.this.notifyDataSetChanged();
				}};
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
		// �إ߲M�涵�ظ�Ʀ�C
		orginal_itemList = itemList;
		
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
		
		//�]�weditText1��TextChanged�ƥ��ť��
		edtext.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable arg0) {
				// ���ݳB�z
			}
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// ���ݳB�z
			}
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// editText1���e�ܰʮɡA�i��M�涵�عL�o�@�~
				lstAdapter.getFilter().filter(arg0);
			}});

    }

    private void getViews() {
		// TODO Auto-generated method stub
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);
		edtext=(EditText) findViewById(R.id.editText1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
