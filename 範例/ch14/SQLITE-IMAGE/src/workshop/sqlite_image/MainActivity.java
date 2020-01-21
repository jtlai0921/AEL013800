package workshop.sqlite_image;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
//	private String[][] books = new String[10][];
	private Button btn;
	private EditText txtquery;
	private ListView lstresult;
	private Cursor cursor; 
	private SQLiteDatabase db;
	DBManager dbManager; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		dbManager = new DBManager(this, "books.db", null, 1);
		db = dbManager.getReadableDatabase();
        getViews();
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 進行資料查詢作業
				queryDB();
			}});
    }
    
    protected void queryDB() {
		// 依editText1內容查詢資料，並將結果顯示在listView1
		cursor = db.rawQuery(txtquery.getText().toString(), null);
		MyCursorAdapter myCursorAdapter = new MyCursorAdapter(MainActivity.this, cursor, true);
		lstresult.setAdapter(myCursorAdapter);
	}

	private void getViews() {
		// 取得MainActivity介面元件參照
		btn = (Button) findViewById(R.id.button1);
		txtquery = (EditText) findViewById(R.id.editText1);
		lstresult = (ListView) findViewById(R.id.listView1);
	}
   
    @Override
	protected void onDestroy() {
		super.onDestroy();
		// 關閉資料庫
		db.close();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
