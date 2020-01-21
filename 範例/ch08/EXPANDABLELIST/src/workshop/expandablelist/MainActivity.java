package workshop.expandablelist;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
	ArrayList<String> group = new ArrayList<String>(); 							//�ǰ|�s��  
	ArrayList<ArrayList<String>> child = new ArrayList<ArrayList<String>>(); 	//�t�ҲM��  
    ExpandableListView expandablelist = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItem("�q��ǰ|",new String[]{"�q�l�t","�q���t","��T�t","�q�q�t"});  
        addItem("�u�{�ǰ|",new String[]{"����t","�۰ʤƨt","�u�ިt"});
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
