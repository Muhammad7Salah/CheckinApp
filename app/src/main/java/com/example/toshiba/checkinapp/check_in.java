package com.example.toshiba.checkinapp;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class check_in extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener{

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_check_in);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setOnMapLongClickListener(this);
        googleMap.setOnMyLocationChangeListener(myLocationChangeListener);
        googleMap.setMyLocationEnabled(true);
        googleMap.setInfoWindowAdapter(Info_window_adapter.class);
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            if(map != null){
                //map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));

               Marker mark=map.addMarker(new MarkerOptions()
                        .title("Egypt")
                        .snippet("Welcome")
                        .position(loc));
                mark.showInfoWindow();
            }
        }
    };

    @Override
    public void onMapLongClick(LatLng point) {
        Marker marker=map.addMarker(new MarkerOptions()
                .title("Egypt")
                .snippet("Welcome")
                .position(point));
        marker.showInfoWindow();
    }

}