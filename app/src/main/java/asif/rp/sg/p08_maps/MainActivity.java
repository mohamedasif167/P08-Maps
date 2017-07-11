package asif.rp.sg.p08_maps;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView txtName,txtBody;
    private GoogleMap map;
    Spinner Spinner;
    ArrayList<String> spinner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        txtName = (TextView) findViewById(R.id.txtName);
        txtBody = (TextView) findViewById(R.id.txtDesc);

        Spinner = (Spinner) findViewById(R.id.btnSpn);

        spinner.add("All");
        spinner.add("North");
        spinner.add("East");
        spinner.add("Central");

        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.get(position).equalsIgnoreCase("All")){
                    // With this, then it would show a certain place on the map instead of the large overview
                    LatLng singapore = new LatLng(1.352083, 103.819836);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                            15));

                }else if (spinner.get(position).equalsIgnoreCase("East")){
                    // With this, then it would show a certain place on the map instead of the large overview
                    LatLng east = new LatLng(1.367149, 103.928021);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                            15));
                }else if(spinner.get(position).equalsIgnoreCase("North")){
                    // With this, then it would show a certain place on the map instead of the large overview
                    LatLng poi_CausewayPoint = new LatLng(1.441073, 103.772070);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                            15));
                }else{
                    // With this, then it would show a certain place on the map instead of the large overview
                    LatLng central = new LatLng(1.297802, 103.847441);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                            15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        //This codes reference to the google Map, The object would only load when it's fully loaded.
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                // With this, then it would show a certain place on the map instead of the large overview
                LatLng singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        15));

                LatLng north = new LatLng(1.441073, 103.772070);
                Marker north_marker = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cloud_black_24dp)));



                LatLng central = new LatLng(1.297802, 103.847441);
                Marker central_marker = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("HQ-Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng east = new LatLng(1.367149, 103.928021);
                Marker east_marker = map.addMarker(new
                        MarkerOptions()
                        .position(east)
                        .title("HQ-East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                //Compass
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);

                // Zoom In and Zoom Out Buttons
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(MainActivity.this, marker.getTitle().toString(), Toast.LENGTH_LONG).show();
                        marker.showInfoWindow();
                        return true;
                    }
                });
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // With this, then it would show a certain place on the map instead of the large overview
                LatLng poi_CausewayPoint = new LatLng(1.441073, 103.772070);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                        15));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // With this, then it would show a certain place on the map instead of the large overview
                LatLng central = new LatLng(1.297802, 103.847441);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                        15));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // With this, then it would show a certain place on the map instead of the large overview
                LatLng east = new LatLng(1.367149, 103.928021);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                        15));
            }
        });
        

        
    }
}
