package workshop.xmlfile;

import java.io.FileInputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btn;
	private TextView txtbooks;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				parseXML();
			}});
    }

    protected void parseXML() {		
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		try
		{
			// 執行解析XML文件作業
			BookHandler xmlHandler = new BookHandler();
			FileInputStream fis = new FileInputStream(sdcardDir + "/books.xml");
			android.util.Xml.parse(fis, android.util.Xml.Encoding.UTF_8, xmlHandler);
			ArrayList<Book> booklist = xmlHandler.getBooks();
			
			// 將解析結果顯示在textView1
			String msg = "";
			for (Book book : booklist)
				msg += book.getTitle() + "\n" + book.getPublisher() + " " + book.getYear() + "\nISBN：" + book.getIsbn() + "\n\n";
			txtbooks.setText(msg);
		}
		catch (Exception e) {}
	}

	private void getViews() {
		// 取得介面元件參照
		btn = (Button) findViewById(R.id.button1);
		txtbooks = (TextView) findViewById(R.id.textView1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
