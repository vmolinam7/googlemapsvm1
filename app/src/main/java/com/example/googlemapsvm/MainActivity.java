package com.example.googlemapsvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    List<LatLng> lista;
    GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lista = new ArrayList<>();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa=googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

        LatLng madrid = new LatLng(40.68932128173221, -74.0445223343323);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(19)
                .bearing(45) //noreste arriba
                .tilt(70) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
        mapa.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        /*LatLng punto = new LatLng(latLng.latitude, latLng.longitude);
        MarkerOptions marcador = new MarkerOptions();
        marcador.position(latLng);
        marcador.title("punto");
        mapa.addMarker(marcador);*/
        LatLng punto = new LatLng(latLng.latitude,
                latLng.longitude);
        mapa.addMarker(new
                MarkerOptions().position(punto)
                .title("Estatua"));

        lista.add(latLng);
        if(lista.size()==6){
            PolylineOptions lineas = new
                    PolylineOptions()
                    .add(new LatLng(lista.get(0).latitude, lista.get(0).longitude))
                    .add(new LatLng(lista.get(1).latitude, lista.get(1).longitude))
                    .add(new LatLng(lista.get(2).latitude, lista.get(2).longitude))
                    .add(new LatLng(lista.get(3).latitude, lista.get(3).longitude))
                    .add(new LatLng(lista.get(4).latitude, lista.get(4).longitude))
                    .add(new LatLng(lista.get(5).latitude, lista.get(5).longitude))
                    .add(new LatLng(lista.get(0).latitude, lista.get(0).longitude));
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            lista.clear();
        }

    }
}