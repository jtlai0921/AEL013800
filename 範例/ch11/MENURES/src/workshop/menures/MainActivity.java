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
        
        // ��realtivelayout1�������U����\���
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
		// �ϥΪ��I�糧��\����B�z�@�~
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
		// �̾�R.menu.contextmenu���e�غc����\���
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle(R.string.app_name);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// �ϥΪ��I��ﶵ�\����B�z�@�~
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
