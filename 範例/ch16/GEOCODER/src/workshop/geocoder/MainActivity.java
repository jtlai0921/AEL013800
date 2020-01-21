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
	private MyLocationOverlay locationOverlay = null;	//�w��ϼh
	private MyItemizedOverlay itemizedOverlay = null;	//�a�йϼh
	private Geocoder geocoder = null;
	private List<Overlay> overlays = null;		//MapView�����|�ϼh��C
	private Button btnQuery = null;
	private EditText edWhere = null; 			
	private List<Address> addresslist = null;; 	//�x�sGeocoder�j�M���G
	private Drawable icon = null;				//�a�йϥ�

    private class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {	//�a�йϼh
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
		mapview.setBuiltInZoomControls(true);		//�ҥΤ����Y�񱱨�
		mapController = mapview.getController();
    	GeoPoint ursa = new GeoPoint((int)(23.937589*1E6),(int)(120.700813*1E6));
		mapController.setCenter(ursa);				//�a�Ϫ�l��m�����I		
		mapController.setZoom(17);					//�a�Ϥj�p����
		overlays  = mapview.getOverlays();			//���oMapView�����|�ϼh��C
		locationOverlay = new MyLocationOverlay(this, mapview);	
		overlays.add(locationOverlay);				//�N�w��ϼh�[��MapView
		
        icon = getResources().getDrawable(R.drawable.ic_itemized_m);
        icon.setAlpha(128);							//�]�w�a�йϥܳz����
        int W = icon.getMinimumWidth();
        int H = icon.getMinimumHeight();
        icon.setBounds(-W/2, -H, W/2, 0);			//�]�w�a�йϥܪ�����
        
		geocoder = new Geocoder(MainActivity.this);
		btnQuery.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				querymap();
			}});
    }

    protected void querymap() {						//�̾�editPlace���󤺮e�d�ߦa��
		// TODO Auto-generated method stub
		addresslist = null;							//�M�Ŭd�ߵ��G
		try {
			addresslist = geocoder.getFromLocationName(
					edWhere.getText().toString(),	//����r���e
					3,								//�̦h�Ǧ^����
					21.851302, 119.36014,			//�d�߰ϰ쥪�U���a�z�y��
					25.306787, 122.097725);			//�d�߰ϰ�k�W���a�z�y��
			if (addresslist.isEmpty()) {
				Toast.makeText(MainActivity.this, "�䤣��ŦX��}", Toast.LENGTH_SHORT).show();
			}
			else {
				updateOverlay();					//�a�Ϲϼh��s���
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
		overlays.clear();							//�M��MapView�����|�ϼh��C
		overlays.add(locationOverlay);				//���s�N�w��ϼh�[��MapView
		itemizedOverlay = new MyItemizedOverlay(icon);
		for (Address addr:addresslist) {			//�v�����X��}��ƫإߦa��
			double geolatitude = addr.getLatitude();	//�n�׭�
			double geolongitude = addr.getLongitude();	//�g�׭�			
			String place = addr.getFeatureName();		//�a�W
			gp = new GeoPoint((int)(geolatitude*1E6), (int)(geolongitude*1E6));
		   	overlayItem = new OverlayItem(gp, place, "");
		   	itemizedOverlay.addOverlayItem(overlayItem);	//�N�a�Х[�J�a�йϼh
			Toast.makeText(MainActivity.this, place, Toast.LENGTH_SHORT).show();
		}
	    overlays.add(itemizedOverlay);				//�N�a�йϼh�[��MapView
	    mapController.animateTo(gp);				//��s�a�Ϥ��ߦ�m
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
		locationOverlay.disableCompass();							//���ιq�lù�L
		locationOverlay.disableMyLocation();						//���Ωw���s
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        locationOverlay.enableCompass();							//�ҥιq�lù�L
		locationOverlay.enableMyLocation();							//�ҥΩw���s
	}
}
