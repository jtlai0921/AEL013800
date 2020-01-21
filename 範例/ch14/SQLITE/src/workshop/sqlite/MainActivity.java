package workshop.sqlite;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private SQLiteDatabase db;
	private DBManager dbManager; 
	private Button btn1;
	private EditText txtquery;
	private TextView txtresult;

    @Override
	protected void onDestroy() {
		super.onDestroy();
		// 關閉資料庫
		db.close();
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
				// 執行查詢作業
				queryDB(); 
			}});
    }

	// 使用editText1之SQL指令查詢book資料表，並將查詢結果在textView1上
    protected void queryDB() {
		Cursor cursor = db.rawQuery(txtquery.getText().toString(), null);
		cursor.moveToFirst();
		String result = "";
		for (int i=1; i<=cursor.getCount() ;i++, cursor.moveToNext()) 
			result += cursor.getString(cursor.getColumnIndex("title")) + "\n" +
					cursor.getString(cursor.getColumnIndex("publisher")) + " " +
					cursor.getString(cursor.getColumnIndex("year")) + "\n" +
					cursor.getString(cursor.getColumnIndex("isbn")) + "\n\n";
		txtresult.setText(result);
	}

	private void getViews() {
		// 取得介面元件參照
		btn1 = (Button) findViewById(R.id.button1);
		txtquery = (EditText) findViewById(R.id.editText1);
		txtresult = (TextView) findViewById(R.id.textView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
