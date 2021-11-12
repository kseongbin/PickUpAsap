package com.seongbinsoft.pickupasap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

public class GoogleMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        //double a=37.5593;   //latitude
        //double b=127.0235;  //longitude

        Intent intent = getIntent();
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");
        double a = Double.parseDouble(latitude);
        double b = Double.parseDouble(longitude);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.frag_map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
                LatLng kukdong = new LatLng(a, b);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kukdong, 17));
                MarkerOptions marker = new MarkerOptions();
                marker.position(kukdong);
                //marker.title("극동아파트");
                //marker.snippet("개발자의 집입니다.");
                //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_flag_36));
                googleMap.addMarker(marker);
            }
        });
    }
}