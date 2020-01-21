package workshop.webview;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	WebView m_myBrowser = null;
    @SuppressLint("SetJavaScriptEnabled")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		m_myBrowser = (WebView) findViewById(R.id.webView1);	
//        String home_url = getString(R.string.ursa_homepage);	
/*
		WebSettings websettings = m_myBrowser.getSettings();  
        websettings.setSupportZoom(true);  							// �䴩�����Y��
        websettings.setBuiltInZoomControls(true);   				// �ҥΤ����Y�񱱨�
        websettings.setJavaScriptEnabled(true);				
        websettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        websettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);	// �˵��̤j����
*/        
        m_myBrowser.setWebViewClient(new WebViewClient());			// ��WebView�B�z�I���s���@�~
        m_myBrowser.loadUrl("http://www.google.com");  				// ���J����

    }

    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
        if ((keyCode == KeyEvent.KEYCODE_BACK) && m_myBrowser.canGoBack()) {   
        	m_myBrowser.goBack(); 			//��^WebView���W�@����   
            return true;   
        } 
		return super.onKeyDown(keyCode, event);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		m_myBrowser = null;
		super.onDestroy();
	}
	
}
