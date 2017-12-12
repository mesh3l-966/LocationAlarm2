package com.example.asus_pc.locationalarm2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import android.os.Bundle;
import java.lang.String;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import android.content.Intent;


public class MapsActivity extends FragmentActivity implements OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback, OnMapLongClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Riyadh and move the camera
        LatLng Riyadh = new LatLng(24.774265, 46.738586);
        mMap.addMarker(new MarkerOptions().position(Riyadh).title("Marker in Riyadh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Riyadh));

        // add zoom control
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            mMap.setOnMapLongClickListener(this);

        } else {
            // Show rationale and request permission.
            Toast.makeText(this, "We have no permission to access your location", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onMapLongClick(LatLng point) {
        Intent i;
        String location = point.toString();

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String note = extras.getString("note");
        String theType = extras.getString("theType");
        if(theType.equals("edit")) {
          i = new Intent(MapsActivity.this, EditAlarm.class);
          String theId = extras.getString("theId");
          i.putExtra("theId", theId.toString());
        }
        else {
          i = new Intent(MapsActivity.this, AddAlarm.class);
        }
        i.putExtra("STRING_I_NEED", location);
        i.putExtra("name", name.toString());
        i.putExtra("note", note.toString());

         startActivity(i);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

}
