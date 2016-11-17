package com.fernandospr.mapsapplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeprecatedApiActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationChangeListener {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    private Button mStartButton;
    private Button mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deprecated_api);

        mStartButton = (Button) findViewById(R.id.startButton);
        mStopButton = (Button) findViewById(R.id.stopButton);
        mStartButton.setVisibility(View.GONE);
        mStopButton.setVisibility(View.GONE);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationUpdates();
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopLocationUpdates();
            }
        });

        loadMap();
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "startLocationUpdates");
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(this);
        }
    }

    private void stopLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "stopLocationUpdates");
            mMap.setOnMyLocationChangeListener(null);
            mMap.setMyLocationEnabled(false);
        }
    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        mStartButton.setVisibility(View.VISIBLE);
        mStopButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.toString());

        mMap.clear();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
