package workshop.expandablelist;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	ArrayList<String> group = new ArrayList<String>(); 							//學院群組  
	ArrayList<ArrayList<String>> child = new ArrayList<ArrayList<String>>(); 	//系所清單  
    ExpandableListView expandablelist = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItem("電資學院",new String[]{"電子系","電機系","資訊系","電通系"});  
        addItem("工程學院",new String[]{"機械系","自動化系","工管系"});
        expandablelist = (ExpandableListView) findViewById(R.id.expandableListView1);
        expandablelist.setAdapter(new ExpListAdapter(MainActivity.this, group, child));   
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
      
    private void addItem(String g,String[] c){  
        group.add(g);  
        ArrayList<String> childitem = new ArrayList<String>();  
        for(int i=0;i<c.length;i++){  
            childitem.add(c[i]);  
        }  
        child.add(childitem);  
    }  

}
