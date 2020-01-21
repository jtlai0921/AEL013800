package workshop.csvfile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtbooks;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		txtbooks = (TextView) findViewById(R.id.textView1);
		readCSVfile();
    }

    private void readCSVfile() {
		// 讀取SD卡中的books.csv檔，將解析結果顯示在textView1
		String sdcardDir = Environment.getExternalStorageDirectory().toString();
		String csvfile = sdcardDir+"/books.csv";
		StringBuilder sb = new StringBuilder ("References:\n");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvfile)));
			String data = "";
			while ((data = br.readLine()) != null) {
				String[] sarray = data.split(",");
				sb.append(sarray[0] + "\n");					//book title
				sb.append("author: " + sarray[3] + "\n");		//author
				sb.append("isbn: " + sarray[4] + "\n");			//isbn
				sb.append("publisher: " + sarray[1] + " " + sarray[2] + "\n\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtbooks.setText(sb.toString());
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
