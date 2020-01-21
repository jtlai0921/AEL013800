package workshop.simple_cursor_adapter;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private SQLiteDatabase db;
	private DBManager dbManager; 
	private Button btn1;
	private EditText txtquery;
	private ListView lstresult;
    @Override
	protected void onDestroy() {
		super.onDestroy();
		// 關閉資料庫
		db.close();
	}

	private void getViews() {
		// 取得MainActivity介面元件參照
		btn1 = (Button) findViewById(R.id.button1);
		txtquery = (EditText) findViewById(R.id.editText1);
		lstresult = (ListView) findViewById(R.id.listView1);
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		dbManager = new DBManager(this, "books.db", null, 1);
		db = dbManager.getReadableDatabase();
		getViews();
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 進行資料查詢作業
				queryDB();
			}});
    }

	// 以editText1內容進行查詢，並將結果顯示listView1
    protected void queryDB() {
		Cursor cursor = db.rawQuery(txtquery.getText().toString(), null);
				
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				MainActivity.this,
				R.layout.entry,
				cursor,
				new String[]{"publisher", "year", "title"},
				new int[]{R.id.textView1, R.id.textView2, R.id.textView3},
				0);
		lstresult.setAdapter(simpleCursorAdapter);

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
