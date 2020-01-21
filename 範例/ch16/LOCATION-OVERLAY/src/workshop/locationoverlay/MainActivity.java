package workshop.locationoverlay;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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
	private ImageView imgSatellite, imgTraffic;
	private MyLocationOverlay locationOverlay = null;
	private List<Overlay> overlays = null;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        imgSatellite.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 切換為衛星地圖模式	
				changeMap(R.id.imageSatellite);
			}});
        imgTraffic.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 切換為一般地圖模式
				changeMap(R.id.imageTraffic);
			}});
		mapview.setBuiltInZoomControls(true);						//啟用內建縮放控制
		mapController = mapview.getController();
    	GeoPoint ursa = new GeoPoint((int)(23.9378*1E6), (int)(120.7009*1E6));       
//    	GeoPoint tgc = new GeoPoint((int)(23.974117*1E6), (int)(120.979801*1E6));
		mapController.setCenter(ursa);
        mapController.setZoom(17);									//地圖大小等級
        overlays  = mapview.getOverlays();
        locationOverlay = new MyLocationOverlay(this, mapview);		//建立定位圖層實體
        locationOverlay.runOnFirstFix(new Runnable(){				//設定定位圖層作業線程
			public void run() {
				mapController.animateTo(locationOverlay.getMyLocation());
			}});
        overlays.add(locationOverlay);	//將定位圖層加到MapView的定位圖層串列
    }

 
	protected void changeMap(int mode) {
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
	}

	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationOverlay.disableCompass();							//停用電子羅盤
		locationOverlay.disableMyLocation();						//停用定位更新
	}

	@Override
	protected void onResume() {
		super.onResume();
        locationOverlay.enableCompass();							//啟用電子羅盤
		locationOverlay.enableMyLocation();							//啟用定位更新
	}
}
