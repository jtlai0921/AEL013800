package workshop.itemizedoverlay;

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

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends MapActivity {


	private class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
		private ArrayList<OverlayItem> mOverlayItems = new ArrayList<OverlayItem> ();
		public MyItemizedOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected boolean onTap(int index) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, mOverlayItems.get(index).getTitle(), Toast.LENGTH_SHORT).show();
			return super.onTap(index);
		}
		

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return mOverlayItems.get(i);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return mOverlayItems.size();
		}
		
		public void addOverlayItem(OverlayItem overlayItem){
			mOverlayItems.add(overlayItem);
			populate();
		}
	}

	private MapView mapview = null;
	private MapController mapController = null;
	private ImageView imgSatellite, imgTraffic;
	private MyLocationOverlay locationOverlay = null;
	private MyItemizedOverlay itemizedOverlay = null;
	private List<Overlay> overlays = null;
	
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
	   	GeoPoint ursa = new GeoPoint((int)(23.937589*1E6), (int)(120.700813*1E6));
		mapController.setCenter(ursa);
        mapController.setZoom(17);					//地圖大小等級
        overlays  = mapview.getOverlays();
        locationOverlay = new MyLocationOverlay(this, mapview);		//建立定位圖層
        overlays.add(locationOverlay);				//將定位圖層加到MapView
        createItemizedOverlay();
        overlays.add(itemizedOverlay);				//將地標圖層加到MapView
    }

    private void createItemizedOverlay() {
		// TODO Auto-generated method stub
    	OverlayItem overlayItem;
    	GeoPoint gp;
        Drawable icon = getResources().getDrawable(R.drawable.ic_itemized_m);
        icon.setAlpha(128);						//設定地標圖示透明度
        int W = icon.getMinimumWidth();
        int H = icon.getMinimumHeight();
        icon.setBounds(-W/2, -H, W/2, 0);		//設定地標圖示的限界
        itemizedOverlay = new MyItemizedOverlay(icon);
    	gp = new GeoPoint((int)(23.937589*1E6), (int)(120.700813*1E6));
    	overlayItem = new OverlayItem(gp, "耕雲數位", "http://www.ursapictor.com.tw/");
    	itemizedOverlay.addOverlayItem(overlayItem);
    	gp = new GeoPoint((int)(23.979122*1E6), (int)(120.696781*1E6));
    	overlayItem = new OverlayItem(gp, "南開科大", "http://www.nkut.edu.tw/");
    	itemizedOverlay.addOverlayItem(overlayItem);
    	gp = new GeoPoint((int)(23.956681*1E6), (int)(120.685752*1E6));
    	overlayItem = new OverlayItem(gp, "中興新村牌樓", "http://en.wikipedia.org/wiki/Zhongxing_New_Village");
    	itemizedOverlay.addOverlayItem(overlayItem);
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		locationOverlay.disableCompass();							//停用電子羅盤
		locationOverlay.disableMyLocation();						//停用定位更新
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        locationOverlay.enableCompass();							//啟用電子羅盤
		locationOverlay.enableMyLocation();							//啟用定位更新
	}
}
