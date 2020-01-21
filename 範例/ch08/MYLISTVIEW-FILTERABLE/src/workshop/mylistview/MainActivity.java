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
			// 傳回資料轉接器之資料總數
			return itemList.size();
		}

		public Object getItem(int position) {
			// 傳回第position位置資料
			return null;
		}

		public long getItemId(int position) {
			// 傳回第position位置資料代碼 
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// 依據mylistitem.xml版面設計檔內容建構UI元件，並設定內容
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
			// 建立一個Filter實體並回傳
			return new Filter(){
				@Override
				protected FilterResults performFiltering(CharSequence arg0) {
					// TODO Auto-generated method stub
					FilterResults results = new FilterResults();
					ArrayList<Item> filtered_itemList = new ArrayList<Item>();
					if (arg0!= null && arg0.toString().length() > 0) {
						// 逐項比對資料項內容是符合過濾字串，並將符合項目加到filtered_itemList
						for (int index = 0; index < orginal_itemList.size(); index++) {
							Item item = orginal_itemList.get(index);
							//將符合項目加到filtered_itemList
							if (item.getlesson().contains(arg0.toString())) filtered_itemList.add(item);
						}
						results.values = filtered_itemList;	//使用過濾後清單內容
						results.count = filtered_itemList.size();                   
					}
					else{
						results.values = orginal_itemList;	//使用原始清單內容	
						results.count = orginal_itemList.size();
					}
					return results;
				}
				
				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					// 更新清單資料並通知更新	
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

		// 讀取字串陣列資源檔內容並存入字串陣列
        lessons = getResources().getStringArray(R.array.courses);
		comments = getResources().getStringArray(R.array.descriptions);   

		// 建立清單項目資料串列
		for (int i=0; i<lessons.length; i++){
	        Item item = new Item();
	        item.setimgId(imgIds[i]);
	        item.setlesson(lessons[i]);
	        item.setcomment(comments[i]);
	        itemList.add(item);
		}
		// 建立清單項目資料串列
		orginal_itemList = itemList;
		
		// 建立MyListAdapter實體並設定該物件做為listView1元件之資料轉接器
		lstAdapter = new MyListAdapter();
		lstview.setAdapter(lstAdapter);
		
		// 設定ListView之ItemClick事件監聽器
		lstview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 將listView1中被點選項目第一行內容顯示在textView1上
				TextView txtLine1 = (TextView) arg1.findViewById(R.id.textView1);
				txtview.setText(txtLine1.getText().toString());								
			}});   
		
		//設定editText1之TextChanged事件監聽器
		edtext.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable arg0) {
				// 不需處理
			}
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// 不需處理
			}
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// editText1內容變動時，進行清單項目過濾作業
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
