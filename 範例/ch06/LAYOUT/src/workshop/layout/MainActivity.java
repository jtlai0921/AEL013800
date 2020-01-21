package workshop.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
 		//��@�m��6.1 
        setContentView(R.layout.linear);
		this.setTitle("Lab 6.1 - LinearLayout");
		
		//��@�m��6.2 
		setContentView(R.layout.table);
		this.setTitle("Lab 6.2 - TableLayout");
		
		��@�m��6.3 
		setContentView(R.layout.relative);
		this.setTitle("Lab 6.3 - RelativeLayout");
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
