package workshop.optionmenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	final int MENU_ABOUT = 100;
	final int MENU_QUIT = 101;
	final int MENU_AUTHOR = 102;
	final int MENU_AFFILIATION = 103;
	final int ICON_ABOUT = android.R.drawable.ic_menu_info_details;
	final int ICON_QUIT = android.R.drawable.ic_menu_close_clear_cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 加入「結束程式」目及「關於…」子功能表項目
		menu.add(0, MENU_QUIT, 0, R.string.mi_quit).setIcon(ICON_QUIT);;
		Menu submenu = menu.addSubMenu(0, MENU_ABOUT, 1, R.string.mi_about).setIcon(ICON_ABOUT);
		submenu.add(0, MENU_AUTHOR, 0, R.string.mi_author);
		submenu.add(0, MENU_AFFILIATION, 0, R.string.mi_affiliation);	

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 使用者點選功能表之處理作業
		switch(item.getItemId()){
		case MENU_AUTHOR:
			Toast.makeText(MainActivity.this, R.string.name, Toast.LENGTH_SHORT).show();
			break;
		case MENU_AFFILIATION:
			Toast.makeText(MainActivity.this, R.string.corporation, Toast.LENGTH_SHORT).show();
			break;		
		case MENU_QUIT:
			finish();
			break;
		}
		return true;
	}

}
