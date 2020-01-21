package workshop.mapview;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends MapActivity {
	private MapView mapview = null;
	private MapController mapController = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapview = (MapView) findViewById(R.id.mapView);
        mapController = mapview.getController();
		mapview.setBuiltInZoomControls(true);		//币ノず罽北
        GeoPoint ursa = new GeoPoint((int)(23.937591*1000000), (int)(120.700807*1000000));
		mapController.setCenter(ursa);				//(23.937591, 120.700807)いみ翴
        mapController.setZoom(17);					//瓜单
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.menu_traffic:					//ち传瓜家Α
			mapview.setTraffic(true);
			mapview.setSatellite(false);
			break;
		case R.id.menu_satellite:				//ち传矫琍瓜家Α
			mapview.setTraffic(false);
			mapview.setSatellite(true);
			break;		
		case R.id.menu_exit:					//挡祘Α
			finish();
			break;
		}
		return true;
	}    
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
