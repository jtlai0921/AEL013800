package workshop.contextmenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	final int MENU_ABOUT = 100;
	final int MENU_QUIT = 101;
	final int MENU_AUTHOR = 102;
	final int MENU_AFFILIATION = 103;
	final int ICON_ABOUT = android.R.drawable.ic_menu_info_details;
	final int ICON_QUIT = android.R.drawable.ic_menu_close_clear_cancel;

    @Override
	public boolean onContextItemSelected(MenuItem item) {
		// 使用者點選功能表之處理作業
		switch(item.getItemId()){
		case MENU_AUTHOR:
			Toast.makeText(MainActivity.this, 
						R.string.name, Toast.LENGTH_SHORT).show();
			break;
		case MENU_AFFILIATION:
			Toast.makeText(MainActivity.this, 
						R.string.corporation, Toast.LENGTH_SHORT).show();
			break;		
		case MENU_QUIT:
			finish();
			break;
		}	
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 在本文功能表加上「結束程式」及一個「關於…」子功能表項目
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.setHeaderTitle(R.string.app_name); 
		menu.add(0, MENU_QUIT, 0, R.string.mi_quit);
		Menu submenu = menu.addSubMenu(0, MENU_ABOUT, 0, R.string.mi_about);
		submenu.add(0, MENU_AUTHOR, 0, R.string.mi_author);
		submenu.add(0, MENU_AFFILIATION, 0, R.string.mi_affiliation);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 為relativelayout1版面註冊本文功能表
		RelativeLayout layoutview = (RelativeLayout)findViewById(R.id.relativelayout1);        
		registerForContextMenu(layoutview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
