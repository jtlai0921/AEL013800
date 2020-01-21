package workshop.alertdialog_ii;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1, btn2, btn3, btn4;
	private String[] lessons;
	int sel = 0;
	boolean[] checked = null; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	lessons = getResources().getStringArray (R.array.courses);
        checked = new boolean[lessons.length];
        getViews();
        setListeners();
    }

    private void setListeners() {
		// �]�w���s����Click�ƥ��ť��
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ��ܤ@��M�榡AlertDialog
				show_listAlert();
			}});
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ��ܳ��M�榡AlertDialog
				show_schoiceAlert();
			}});
		btn3.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ��ܽƿ�M�榡AlertDialog
				show_mchoiceAlert();
			}});
		btn4.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ��ܦۭq����AlertDialog
				show_customizedAlert();
			}});
    }
    
	// �غc����ܦۭq����AlertDialog
	private void show_customizedAlert() {
		//�̾�login.xml�غc��������
		final TableLayout loginLayout = (TableLayout) getLayoutInflater().inflate(R.layout.login, null);
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		//�]�w�ϥ�loginLayout������ܵ�������
		dlg.setView(loginLayout);
		dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// �ϥ�Toast��ܿ�J����Ƥ��e		
				TextView txtname = (TextView) loginLayout.findViewById(R.id.editText1);
				TextView txtpasswd = (TextView) loginLayout.findViewById(R.id.editText2);
				String msg = txtname.getText().toString() + ", " + txtpasswd.getText().toString();
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}});
		dlg.show();
	}
	
	//�غc����ܽƿ�M�榡AlertDialog
	private void show_mchoiceAlert() {
		// TODO Auto-generated method stub
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		// �]�w�ϥνƿ�M��A�ù�@�I��M�涵�ؤ��ƥ��ť��
		dlg.setMultiChoiceItems(lessons, checked, new DialogInterface.OnMultiChoiceClickListener(){
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// �O�����ؤ��Ŀ窱�A
				checked[which] = isChecked;
			}});
		// �[�JOK���s�A�ù�@OK���s��Click�ƥ��ť��
		dlg.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
				// �ϥ�Toast��ܩҦ��Q�Ŀﶵ�ؤ����e
				String msg = "";
				for (int i = 0; i<lessons.length; i++)
					if (checked[i]) msg = msg + lessons[i] + ", ";
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}});
		dlg.show();
	}

	//�غc����ܳ��M�榡AlertDialog
	private void show_schoiceAlert() {
		AlertDialog.Builder dlg = new AlertDialog.Builder (MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon (R.drawable.ic_launcher);
		//�]�w�ϥγ��M��A�ù�@�I��M�涵�ؤ�Click�ƥ��ť��
		dlg.setSingleChoiceItems (lessons, 0, new DialogInterface.OnClickListener () {
			public void onClick (DialogInterface dialog, int which) {
				sel = which;
			}});
		//�[�JOK���s�A�ù�@OK���s��Click�ƥ��ť��
		dlg.setPositiveButton ("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// �ϥ�Toast��ܳQ�I�蠟���ؤ��e
				Toast.makeText(MainActivity.this, lessons[sel], Toast.LENGTH_SHORT).show();
			}});
		dlg.show ();	
	}

	// �غc����ܤ�r�M�榡AlertDialog
	private void show_listAlert() {
		AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
		dlg.setTitle(R.string.app_name);
		dlg.setIcon(R.drawable.ic_launcher);
		//�]�w�ϥΤ�r�M��A�ù�@�I��M�涵�ؤ�Click�ƥ��ť��
		dlg.setItems(lessons, new DialogInterface.OnClickListener(){
			public void onClick (DialogInterface dialog, int which){
				// �ϥ�Toast��ܳQ�I�蠟���ؤ��e
				Toast.makeText(MainActivity.this, lessons[which], Toast.LENGTH_SHORT).show();
			}});
		//�[�JCANCEL���s
		dlg.setNeutralButton("CANCEL", null); 
		dlg.show();
	}

	private void getViews() {
		// ���o��������ѷ�
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
