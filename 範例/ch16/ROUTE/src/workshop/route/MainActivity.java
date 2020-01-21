package workshop.route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
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
	private Geocoder geocoder = null;
	private Button btnQuery = null;
	private EditText edWhere = null; 			
	private String from = "";							//���|�d�߰_�I�r��

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
		mapview.setBuiltInZoomControls(true);		//�ҥΤ����Y�񱱨�
		mapController = mapview.getController();
		mapController.setZoom(17);					//�a�Ϥj�p����
		locationOverlay = new MyLocationOverlay(this, mapview);	
		locationOverlay.runOnFirstFix(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				GeoPoint startgp = locationOverlay.getMyLocation();
				mapController.animateTo(startgp);
				from = String.valueOf(startgp.getLatitudeE6()/1E6) + "," + 
						String.valueOf(startgp.getLongitudeE6()/1E6);
			}});
		mapview.getOverlays().add(locationOverlay);	//�N�w��ϼh�[��MapView	
		geocoder = new Geocoder(MainActivity.this);
		btnQuery.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				planroute();
			}});
    }

    protected void planroute() {					//�HeditPlace���e�����I�d�ߥ�q���|
		// TODO Auto-generated method stub
    	List<Address> addresslist = null;			//�x�sGeocoder�j�M���G
		try {
			addresslist = geocoder.getFromLocationName(
					edWhere.getText().toString(),	//����r���e
					1);								//�u��1�����
			if (addresslist.isEmpty()) {
				Toast.makeText(MainActivity.this, "�䤣��ŦX��}", Toast.LENGTH_SHORT).show();
			}
			else {									//�ϥ�Intent�}��Google Maps���|
				Address addr = addresslist.get(0);
				String to = String.valueOf(addr.getLatitude()) + "," + 
						String.valueOf(addr.getLongitude());
				String uri = "http://maps.google.com/maps?f=d&saddr=" + from + "&daddr=" + to + "&hl=tw";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));  
				startActivity(intent);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
