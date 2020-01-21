package workshop.menures;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 為realtivelayout1版面註冊本文功能表
        RelativeLayout layoutview = (RelativeLayout) findViewById(R.id.realtivelayout1);
        registerForContextMenu(layoutview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;
    }

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 使用者點選本文功能表之處理作業
		switch (item.getItemId()) {
		case R.id.menu_quit:
			finish();
			break;
		case R.id.menu_author:
			Toast.makeText(MainActivity.this, R.string.name, Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_corp:
			Toast.makeText(MainActivity.this, R.string.corporation,  Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// 依據R.menu.contextmenu內容建構本文功能表
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle(R.string.app_name);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 使用者點選選項功能表之處理作業
		switch (item.getItemId()) {
		case R.id.menu_quit:
			finish();
			break;
		case R.id.menu_author:
			Toast.makeText(MainActivity.this, R.string.name, Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_corp:
			Toast.makeText(MainActivity.this, R.string.corporation,  Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
}
