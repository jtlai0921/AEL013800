package workshop.locationmanager;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends MapActivity {
	private MapView mapview = null;
	private MapController mapController = null;
	private LocationManager locationMgr = null;
	private String bestProvider;
	private ImageView imgSatellite, imgTraffic;
	private TextView txtLocation;
	
	private LocationListener locationListener = new LocationListener(){
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			double latitude = location.getLatitude(); 	//緯度
			double longitude = location.getLongitude(); //經度
	    	GeoPoint gp = new GeoPoint((int)(latitude*1E6), (int)(longitude*1E6));
			mapController.animateTo(gp);				//重新定位地圖中心點
			txtLocation.setText("(" + String.format("%.4f", latitude) + ", " + String.format("%.4f", longitude) + ")");
			
		}
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
	        bestProvider = locationMgr.getBestProvider(new Criteria(), true);			
		}};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        imgSatellite.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeMap(R.id.imageSatellite);
			}});
        imgTraffic.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changeMap(R.id.imageTraffic);
			}});
		mapview.setBuiltInZoomControls(true);		//啟用內建縮放控制
        mapController = mapview.getController();
    	GeoPoint tgc = new GeoPoint((int)(23.974117*1E6), (int)(120.979801*1E6));
		mapController.setCenter(tgc);
        mapController.setZoom(17);					//地圖大小等級
        locationMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
/*        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        locationMgr.getBestProvider(criteria, true);*/
        bestProvider = locationMgr.getBestProvider(new Criteria(), true);
        locationMgr.requestLocationUpdates(bestProvider, 3000, 0, locationListener);
    }

    protected void changeMap(int mode) {
		// TODO Auto-generated method stub
		switch (mode) {
		case R.id.imageSatellite:		//切換為衛星地圖模式		
			mapview.setTraffic(false);
			mapview.setSatellite(true);
			break;
		case R.id.imageTraffic:			//切換為一般地圖模式
			mapview.setTraffic(true);
			mapview.setSatellite(false);
			break;
		}
	}

	private void getViews() {
		// 取得介面元件參照
        mapview = (MapView) findViewById(R.id.mapView);
        imgSatellite = (ImageView) findViewById(R.id.imageSatellite);
        imgTraffic = (ImageView) findViewById(R.id.imageTraffic);
        txtLocation = (TextView) findViewById(R.id.textLocation);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationMgr.removeUpdates(locationListener);		//停止定位處理作業
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locationMgr.requestLocationUpdates(bestProvider, 3000, 0, locationListener);
	}

}
