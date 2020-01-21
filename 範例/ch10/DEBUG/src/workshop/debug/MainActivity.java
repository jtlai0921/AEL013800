package workshop.debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView txtmsg;
	private EditText edhours, edmiles;
	private Button btn1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double hours = Double.parseDouble(edhours.getText().toString());
				double miles = Double.parseDouble(edmiles.getText().toString());
				double mph = calcMPH (hours, miles);
				txtmsg.setText (String.valueOf(mph));
		    	String SD_PATH = Environment.getExternalStorageDirectory().toString();
		    	String FILE_PATH = "/sample/";
		    	String INPUT_FILENAME = "test.doc";

		    	try {
		    		File outFile = new File(SD_PATH + FILE_PATH + INPUT_FILENAME);
		    		FileOutputStream fileos = new FileOutputStream(outFile);
		    		fileos.write(String.valueOf(calcMPH (hours, miles)).getBytes());
		    		fileos.close();
		    		Toast.makeText(MainActivity.this, "寫入檔案成功", Toast.LENGTH_SHORT).show();
		    	} 
		    	catch (FileNotFoundException e2) {
		    		e2.printStackTrace();
		    	} 
		    	catch (IOException e) {
		    		e.printStackTrace();
		    	}
			}});
    }

	private double calcMPH(double miles, double hours) {
		// TODO Auto-generated method stub
		return (miles/hours);
	}
	
	private void getViews() {
		// TODO Auto-generated method stub
		txtmsg = (TextView) findViewById(R.id.textResult);
		edhours = (EditText) findViewById(R.id.editHours);
		edmiles = (EditText) findViewById(R.id.editMiles);
		btn1 = (Button) findViewById(R.id.button1);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	private void showDialog(){
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle (getString(R.string.app_name));
		dlg.setIcon(R.drawable.ic_launcher);
		dlg.setMessage (getString(R.string.hello_world));
		dlg.setPositiveButton ("YES", null);

	}
}
