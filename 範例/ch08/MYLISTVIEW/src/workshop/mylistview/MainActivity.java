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
    }

    private void getViews() {
		// 取得介面元件參照
		txtview = (TextView) findViewById(R.id.textView1);
		lstview = (ListView) findViewById(R.id.listView1);

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
