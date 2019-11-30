package com.example.pruebas;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.CharArrayReader;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private ArrayList<Marker> tmpRealTimeMarkers = new ArrayList<>();
    private ArrayList<Marker> realTimeMarkers = new ArrayList<>();
    private LatLngBounds CU = new LatLngBounds(new LatLng(25.721929, -100.317038), new LatLng(25.730917, -100.306138));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //countDownTimer();
    }

    private void countDownTimer(){
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.v("seconds remaining: ", "" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(MapsActivity.this, "Puntos actualizados", Toast.LENGTH_SHORT).show();
                onMapReady(mMap);
            }
        }.start();
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
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mDatabase.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(Marker marker:realTimeMarkers){
                    marker.remove();
                }
                //Se obtienen todos los datos de "usuarios"
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){

                    MapaPojo mp = snapshot.getValue(MapaPojo.class);
                    double latitud = mp.getLatitud();
                    double longitud = mp.getLongitud();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(latitud,longitud));
                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));
                }

                realTimeMarkers.clear();
                realTimeMarkers.addAll(tmpRealTimeMarkers);
                //countDownTimer();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        LatLng mty = new LatLng(25.726511, -100.311955);
        //mMap.addMarker(new MarkerOptions().position(cu).title("Ciudad Universitaria"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mty));
        //float zoomlevel = 15;
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mty, zoomlevel));
        mMap.setMinZoomPreference(15.5f);
        mMap.setLatLngBoundsForCameraTarget(CU);
    }
}
