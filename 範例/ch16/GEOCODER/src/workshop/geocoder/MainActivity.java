package workshop.geocoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
public class MainActivity extends MapActivity {
	private MapView mapview = null;
	private MapController mapController = null;
	private MyLocationOverlay locationOverlay = null;	//定位圖層
	private MyItemizedOverlay itemizedOverlay = null;	//地標圖層
	private Geocoder geocoder = null;
	private List<Overlay> overlays = null;		//MapView之覆疊圖層串列
	private Button btnQuery = null;
	private EditText edWhere = null; 			
	private List<Address> addresslist = null;; 	//儲存Geocoder搜尋結果
	private Drawable icon = null;				//地標圖示

    private class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {	//地標圖層
		private ArrayList<OverlayItem> mOverlayItems = new ArrayList<OverlayItem> ();
		public MyItemizedOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return mOverlayItems.get(i);
		}

		@Override
		protected boolean onTap(int index) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, mOverlayItems.get(index).getTitle(), Toast.LENGTH_SHORT).show();
			return super.onTap(index);
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

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
		mapview.setBuiltInZoomControls(true);		//啟用內建縮放控制
		mapController = mapview.getController();
    	GeoPoint ursa = new GeoPoint((int)(23.937589*1E6),(int)(120.700813*1E6));
		mapController.setCenter(ursa);				//地圖初始位置中心點		
		mapController.setZoom(17);					//地圖大小等級
		overlays  = mapview.getOverlays();			//取得MapView的覆疊圖層串列
		locationOverlay = new MyLocationOverlay(this, mapview);	
		overlays.add(locationOverlay);				//將定位圖層加到MapView
		
        icon = getResources().getDrawable(R.drawable.ic_itemized_m);
        icon.setAlpha(128);							//設定地標圖示透明度
        int W = icon.getMinimumWidth();
        int H = icon.getMinimumHeight();
        icon.setBounds(-W/2, -H, W/2, 0);			//設定地標圖示的限界
        
		geocoder = new Geocoder(MainActivity.this);
		btnQuery.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				querymap();
			}});
    }

    protected void querymap() {						//依據editPlace元件內容查詢地圖
		// TODO Auto-generated method stub
		addresslist = null;							//清空查詢結果
		try {
			addresslist = geocoder.getFromLocationName(
					edWhere.getText().toString(),	//關鍵字內容
					3,								//最多傳回筆數
					21.851302, 119.36014,			//查詢區域左下角地理座標
					25.306787, 122.097725);			//查詢區域右上角地理座標
			if (addresslist.isEmpty()) {
				Toast.makeText(MainActivity.this, "找不到符合位址", Toast.LENGTH_SHORT).show();
			}
			else {
				updateOverlay();					//地圖圖層更新資料
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void updateOverlay() {
		// TODO Auto-generated method stub
    	OverlayItem overlayItem;
    	GeoPoint gp = null;
		overlays.clear();							//清除MapView之覆疊圖層串列
		overlays.add(locationOverlay);				//重新將定位圖層加到MapView
		itemizedOverlay = new MyItemizedOverlay(icon);
		for (Address addr:addresslist) {			//逐筆取出位址資料建立地標
			double geolatitude = addr.getLatitude();	//緯度值
			double geolongitude = addr.getLongitude();	//經度值			
			String place = addr.getFeatureName();		//地名
			gp = new GeoPoint((int)(geolatitude*1E6), (int)(geolongitude*1E6));
		   	overlayItem = new OverlayItem(gp, place, "");
		   	itemizedOverlay.addOverlayItem(overlayItem);	//將地標加入地標圖層
			Toast.makeText(MainActivity.this, place, Toast.LENGTH_SHORT).show();
		}
	    overlays.add(itemizedOverlay);				//將地標圖層加到MapView
	    mapController.animateTo(gp);				//更新地圖中心位置
	}

    
	private void getViews() {
		// TODO Auto-generated method stub
		mapview = (MapView) findViewById(R.id.mapView);
		btnQuery = (Button) findViewById(R.id.btnSearch);
		edWhere = (EditText) findViewById(R.id.editPlace);
	}

	@Override
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
