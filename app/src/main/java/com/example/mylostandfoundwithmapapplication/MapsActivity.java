package com.example.mylostandfoundwithmapapplication;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.mylostandfoundwithmapapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap myGoogleMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.myGoogleMap = googleMap;

        ArrayList<Item> lostAndFoundList = MainActivity.databaseHelper.getAllItems();
        for (int i = 0; i < lostAndFoundList.size(); i++){
            LatLng location = new LatLng(lostAndFoundList.get(i).getLatitude(), lostAndFoundList.get(i).getLongitude());
            if (i == 0) this.myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            this.myGoogleMap.addMarker(new MarkerOptions().position(location).title(lostAndFoundList.get(i).getName()));
        }
    }
}
