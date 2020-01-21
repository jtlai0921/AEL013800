package workshop.alertdlg;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1 = null; 
	private Button btn2 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
		setListeners();
    }

    private void setListeners() {
		// �]�w���s��Click�ƥ��ť��
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ���Toast�T��
		    	Toast.makeText (MainActivity.this, R.string.hello_world, Toast.LENGTH_SHORT).show();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ���AlertDialog
				showAlert();
			}});
	}

	private void getViews() {
		// ���o��������ѷ�
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
	}

	private void showAlert() {
		// �غc�����AlertDialog
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.dlg_title);
		dlg.setIcon(R.drawable.ic_launcher);
		dlg.setMessage(R.string.dlg_msg);
		dlg.setPositiveButton("YES", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				// ���YES���s�@�~�T��
				Toast.makeText(MainActivity.this, "�w���UYES���s", Toast.LENGTH_SHORT).show();
				}});
		dlg.setNegativeButton("NO", null);
		dlg.show();

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
