package com.example.toshiba.checkinapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

// issued on 9/10
public class check_in extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener,GoogleMap.OnMarkerClickListener,LocationListener {
    private static final long min_distance=1; // 1 meters
    private static final long min_time=1000*60*1; // 1 minute
        ArrayList <LatLng> place_latlng=new ArrayList<LatLng>();
        ArrayList <String> place_name=new ArrayList<String>();
    LocationManager locationManager ;
    String provider;



    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_check_in);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        place_name.add("momen");
        // Getting LocationManager object
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        if(provider!=null && !provider.equals("")) {

            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, min_time, min_distance, this);

            if (location != null) {
                onLocationChanged(location);
            } else {
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();

            }
        }
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
        googleMap.setMyLocationEnabled(true);
        map.setOnMarkerClickListener(this);
    }

    @Override
    public void onMapLongClick(final LatLng point) {
        final Dialog dialoog = new Dialog(this);
        dialoog.setContentView(R.layout.dialog_save);
        final EditText name=(EditText)dialoog.findViewById(R.id.editText);
        final String save_name=name.getText().toString();
        Button b2 = (Button) dialoog.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveplace(save_name, point);
                dialoog.dismiss();

            }
        });

        dialoog.show();
            Marker mark = map.addMarker(new MarkerOptions()
                    .title("nice")
                    .snippet("Hosiptal")
                    .position(place_latlng.get(0)));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final LatLng pointer=marker.getPosition();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_place_details);

        RatingBar ratebar = (RatingBar)dialog.findViewById(R.id.rating);
        ratebar.setEnabled(false);

        Button b1 = (Button) dialog.findViewById(R.id.ratingBtn);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent i = new Intent(check_in.this, Question.class);
                                      startActivity(i);

                                  }
                              }
        );

        dialog.show();

        return false;
    }
        public void saveplace(String name,LatLng latude){{
            place_name.add(name);
            place_latlng.add(latude);
        }}

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}