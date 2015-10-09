package com.example.toshiba.checkinapp;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
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
public class check_in extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener,GoogleMap.OnMarkerClickListener {
    private static final long min_distance=10;
    private static final long min_time=1000*60*1;
        ArrayList <LatLng> place_latlng=new ArrayList<LatLng>();
        ArrayList <String> place_name=new ArrayList<String>();

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_check_in);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        place_name.add("momen");

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
        map.setOnMarkerClickListener(this);
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            if (map != null) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
                Marker mark = map.addMarker(new MarkerOptions()
                        .title("HEY")
                        .snippet("This is my location")
                        .position(loc));

            }
        }
    };

    @Override
    public void onMapLongClick(final LatLng point) {

        for (int i=0;i<place_name.size();i++){
            Marker mark = map.addMarker(new MarkerOptions()
                    .title(place_name.get(i))
                    .snippet("Hosiptal")
                    .position(point));
        }
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

        final EditText name=(EditText)dialog.findViewById(R.id.editText);
        final String save_name=name.getText().toString();
        Button b2 = (Button) dialog.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveplace(save_name, pointer);
            dialog.dismiss();
            }
        });

        dialog.show();

        return false;
    }
        public void saveplace(String name,LatLng latude){{
            place_name.add(name);
            place_latlng.add(latude);
        }}
}